//-----------------------------------------------------------------------------
//  Ravi Gupta
//  radgupta
//  CMPS 12B/M
//  5/18/2019
//  Dictionary.c
//  Implementation file for Dictionary ADT
//-----------------------------------------------------------------------------

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>
#include"Dictionary.h"


// private types --------------------------------------------------------------

// NodeObj
typedef struct NodeObj{
   char* key;
   char* value; 
   struct NodeObj* next;
} NodeObj;

// Node
typedef NodeObj* Node;

// newNode()
// constructor of the Node type
Node newNode(char* x, char* y) {
   Node N = malloc(sizeof(NodeObj));
   assert(N!=NULL);
   N->key = x;
   N->value = y;
   N->next = NULL;
   return(N);
}

// freeNode()
// destructor for the Node type
void freeNode(Node* pN){
   if( pN!=NULL && *pN!=NULL ){
      free(*pN);
      *pN = NULL;
   }
}
// DictionaryObj
typedef struct DictionaryObj{
   Node head;
   int numPairs;
} DictionaryObj;

// newDictionary()
// constructor for the Dictionary type
Dictionary newDictionary(void){
   Dictionary D = malloc(sizeof(DictionaryObj));
   assert(D!=NULL);
   D->head = NULL;
   D->numPairs = 0;
   return D;
}

// freeDictionary()
// destructor for the Dictionary type
void freeDictionary(Dictionary* pD){
   if( pD!=NULL && *pD!=NULL ){
      makeEmpty(*pD);
      free(*pD);
      *pD = NULL;
   }
}

// findKey()
// returns the Node containing key k or returns
// NULL if no such Node exists
Node findKey(Dictionary D, char* key){
   Node N = D->head;
   while( N != NULL) {
      if ( strcmp(key, N->key) == 0 ){ 
         return N;
      }
      N = N->next;
   }
   return NULL;
}

// isEmpty()
// returns 1 (true) if D is empty, 0 (false) otherwise
// pre: none
int isEmpty(Dictionary D){
   if( D==NULL ){
      fprintf(stderr, 
         "Dictionary Error: calling isEmpty() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
   if( D->numPairs==0 ) return 1;
   return 0;
}

// size()
// returns the number of (key, value) pairs in D
// pre: none
int size(Dictionary D){
   if( D==NULL ){
      fprintf(stderr, 
         "Dictionary Error: calling size() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
   return(D->numPairs);
}

// lookup()
// returns the value v such that (k, v) is in D, or returns NULL if no 
// such value v exists.
// pre: none
char* lookup(Dictionary D, char* k){
   Node N;
   if( D==NULL ){
      fprintf(stderr, 
         "Dictionary Error: calling lookup() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
   N = findKey(D, k);
   if ( N==NULL) return NULL;
   return N->value;
}

// insert()
// inserts new (key,value) pair into D
// pre: lookup(D, k)==NULL
void insert(Dictionary D, char* k, char* v){
   Node N, R;
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
   if( D->head == NULL){
      D->head = N;
   }
   else{
      R = D->head;
      while( R->next != NULL ){
         R = R->next;
      }
      R->next = N;
   }
   D->numPairs++;
}

// delete()
// deletes pair with the key k
// pre: lookup(D, k)!=NULL  
void delete(Dictionary D, char* k){
   Node N, R;
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
   N = findKey(D, k);
   if(N == D->head){
      D->head = N->next;
      freeNode(&N);
   } 
   else{
      R = D->head;
      while( R->next != NULL ){
          if( strcmp(N->key, R->next->key)==0 ){
              break;
          }
          R = R->next;
      }
      R->next = N->next;
      freeNode(&N);
   }
   D->numPairs--; 
}

// makeEmpty()
// re-sets D to the empty state.
// pre: none
void makeEmpty(Dictionary D){
   Node R, N = D->head;
   while( N != NULL ){
      R = N;
      N = N->next;
      freeNode(&R);
      D->numPairs--;
   }
}
// printDictionary()
// pre: none
// prints a text representation of D to the file pointed to by out
void printDictionary(FILE* out, Dictionary D){
   if( D==NULL ){
      fprintf(stderr, 
         "Dictionary Error: calling printDictionary() on NULL"
         " Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
   Node N = D->head;
   while( N != NULL ){
      fprintf(out, "%s %s\n", N->key, N->value);      
      N = N->next;
   } 
}

