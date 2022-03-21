//-----------------------------------------------------------------------------
// Dictionary.c
// Binary Search Tree implementation of the Dictionary ADT
//-----------------------------------------------------------------------------/

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>
#include"Dictionary.h"

// private types and functions ------------------------------------------------

#define tableSize 101
// Node
typedef struct NodeObj{
   char* key;
   char* value;
   struct NodeObj* next;
} NodeObj;

typedef NodeObj* Node;

Node newNode( char* k, char* v ){
   Node N = malloc(sizeof(NodeObj));
   assert(N!=NULL);
   N->key = k;
   N->value = v;
   N->next = NULL;
   return(N);
}

void freeNode(Node* pN){
   if( pN!=NULL && *pN!=NULL){
      free(*pN);
      *pN = NULL;
   }
}

typedef struct DictionaryObj{
   Node* table;
   int numPairs;
}DictionaryObj;

unsigned int rotate_left(unsigned int value, int shift) {
   int sizeInBits = 8*sizeof(unsigned int);
   shift = shift & (sizeInBits - 1);
   if ( shift == 0 )
      return value;
   return (value << shift) | (value >> (sizeInBits - shift));
}

unsigned int pre_hash(char* input) {
   unsigned int result = 0xBAE86554;
   while (*input) {
      result ^= *input++;
      result = rotate_left(result, 5);
   }
   return result;
}

int hash(char* key){
   return pre_hash(key)%tableSize;
}

Dictionary newDictionary(){
   Dictionary D = malloc(sizeof(DictionaryObj));
   assert(D!=NULL); 
   D->table = calloc(tableSize, sizeof(NodeObj)); 
   D->numPairs = 0;
   return D;
}

void freeDictionary(Dictionary* pD){
   if( pD!=NULL && *pD!=NULL ){
      makeEmpty(*pD);
      free((*pD)->table);
      free(*pD);
      *pD = NULL;
   }
}


int isEmpty(Dictionary D){
   if( D==NULL ){
      fprintf(stderr, 
         "Dictionary Error: calling isEmpty() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
   return(D->numPairs==0);
}

int size(Dictionary D){
   if( D==NULL ){
      fprintf(stderr, 
         "Dictionary Error: calling size() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
   return(D->numPairs);
}

Node findKey(Dictionary D, char* k){
   int i = hash(k);
   Node N = D->table[i];
   while(N != NULL){
      if(strcmp(N->key, k) == 0){
         return N;
      } 
      N = N->next;
   }
   return NULL;
}

char* lookup(Dictionary D, char* k){
   if( D==NULL ){
      fprintf(stderr, 
         "Dictionary Error: calling lookup() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
   Node N = findKey(D, k);
   return ( N==NULL ? NULL : N->value );
}

void insert(Dictionary D, char* k, char* v){
   Node N; 
   if( D==NULL ){
      fprintf(stderr, 
         "Dictionary Error: calling insert() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
   if( lookup(D, k)!=NULL ){
      fprintf(stderr, 
         "Dictionary Error: cannot insert() duplicate key: \"%s\"\n", k);
      exit(EXIT_FAILURE);
   }
   N = newNode(k, v);
   int i = hash(k);
   N->next = D->table[i];
   D->table[i] = N;
   D->numPairs++;   		               	
}

void delete(Dictionary D, char* k){
   Node N, P;
   if( D==NULL ){
      fprintf(stderr, 
         "Dictionary Error: calling delete() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
   if( lookup(D, k)==NULL ){
      fprintf(stderr, 
         "Dictionary Error: cannot delete() non-existent key: \"%s\"\n", k);
      exit(EXIT_FAILURE);
   }
   int i = hash(k);
   N = D->table[i];
   P = N;
   while( N!=NULL ){
      if( strcmp(N->key, k) == 0){
         D->table[i] = N->next;
         P->next = N->next;
         break;
      }  
      P = N;
      N = N->next;
   }
   D->numPairs--;
   freeNode(&N);
}

void makeEmpty(Dictionary D){
   int i;
   Node N, P;
   for(i = 0;i < tableSize; i++){
      N = D->table[i];
      while( N!= NULL ){ 
         P = N;
         N = N->next;
         freeNode(&P);
         D->numPairs--;
      }
   }
}

void printDictionary(FILE* out, Dictionary D){
   if( D==NULL ){
      fprintf(stderr, 
         "Dictionary Error: calling printDictionary() on NULL"
         " Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
   int i;
   Node N;
   for(i = 0;i < tableSize; i++){
      N = D->table[i];
      while( N!= NULL ){
        fprintf(out, "%s %s\n", N->key, N->value);
        N = N->next;
      }
   } 
}
