#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include"Dictionary.h"

#define MAX_LEN 180

int main(int argc, char* argv[]){
   Dictionary A = newDictionary();
   char* k;
   char* v;
   char* word1[] = {"one","two","three","four","five","six","seven"};
   char* word2[] = {"foo","bar","blah","galumph","happy","sad","blue"};
   int i;
   for(i=0; i<7; i++){
      k = word1[i];
      v = word2[i];
      printf("key=\"%s\" %s\n", k,  v);
   }
   for(i=0; i<7; i++){
      insert(A, word1[i], word2[i]);
   }
   printDictionary(stdout, A);
   delete(A, "one");
   printf("love\n");
   delete(A, "seven");
   printDictionary(stdout, A);
   for(i=0; i<7; i++){
      k = word1[i];
      v = lookup(A, k);
      printf("key=\"%s\" %s\"%s\"\n", k, (v==NULL?"not found ":"value="), v);
   }
   printf("%s\n", (isEmpty(A)?"true":"false"));
   printf("%d\n", size(A));
   makeEmpty(A);
   printf("?\n");
   printf("%s\n", (isEmpty(A)?"true":"false"));

   printf("workin\n");
   freeDictionary(&A);
   printf("nah\n");
   return(EXIT_SUCCESS);
}
   

