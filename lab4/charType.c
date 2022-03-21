//-----------------------------------------------------------------------------
// Ravi Gupta
// radgupta
// CMPS 12B/M
// 5/9/2019
// charType.c
// extracts alphabetic, numeric, punctuation, and whitespace characters from each 
// line of the input file and places them in the output file.
//-----------------------------------------------------------------------------

#include<stdio.h>
#include<stdlib.h>
#include<ctype.h>
#include<assert.h>

#define MAX_STRING_LENGTH 100

// function prototype 
void extract_chars(char* s, char* a, char* d, char* p, char* w);
int strlength(char *f);

// function main which takes command line arguments 
int main(int argc, char* argv[]){
   FILE* in;        // handle for input file                  
   FILE* out;       // handle for output file                 
   char* line;      // string holding input line              
   char* alpha;     // string holding all alphabetic chars 
   char* digit;     // string holding all numeric chars
   char* punct;     // string holding all punctuation chars
   char* white;     // string holding all white chars
   int count = 1; 

   // check command line for correct number of arguments 
   if( argc != 3 ){
      printf("Usage: %s input-file output-file\n", argv[0]);
      exit(EXIT_FAILURE);
   }

   // open input file for reading 
   if( (in=fopen(argv[1], "r"))==NULL ){
      printf("Unable to read from file %s\n", argv[1]);
      exit(EXIT_FAILURE);
   }
   // open output file for writing 
   if( (out=fopen(argv[2], "w"))==NULL ){
      printf("Unable to write to file %s\n", argv[2]);
      exit(EXIT_FAILURE);
   }

   // allocate strings on the heap 
   line = calloc(MAX_STRING_LENGTH+1, sizeof(char) );
   alpha = calloc(MAX_STRING_LENGTH+1, sizeof(char) );
   digit = calloc(MAX_STRING_LENGTH+1, sizeof(char) );
   punct = calloc(MAX_STRING_LENGTH+1, sizeof(char) );
   white = calloc(MAX_STRING_LENGTH+1, sizeof(char) );
   assert( line!=NULL && alpha!=NULL && digit!=NULL && punct!=NULL && white!=NULL  );

   // read each line in input file, print characters where they should be 
   while( fgets(line, MAX_STRING_LENGTH, in) != NULL ){
      extract_chars(line, alpha, digit, punct, white);
      fprintf(out, "line %d contains: \n", count);
      if( strlength(alpha) == 1) fprintf(out, "%d alphabetic character: %s\n", strlength(alpha), alpha);
      else fprintf(out, "%d alphabetic characters: %s\n", strlength(alpha), alpha);
      if( strlength(digit) == 1) fprintf(out, "%d numeric character: %s\n", strlength(digit), digit);
      else fprintf(out, "%d numeric characters: %s\n", strlength(digit), digit);
      if( strlength(punct) == 1) fprintf(out, "%d punctuation character: %s\n", strlength(punct), punct);
      else fprintf(out, "%d punctuation characters: %s\n", strlength(punct), punct);
      if( strlength(white) == 1) fprintf(out, "%d whitespace character: %s\n", strlength(white), white);
      else fprintf(out, "%d whitespace characters: %s\n", strlength(white), white);
      count++;
   }

   // free heap memory 
   free(line);
   free(alpha);
   free(digit);
   free(punct);
   free(white);

   // close input and output files 
   fclose(in);
   fclose(out);

   return EXIT_SUCCESS;
}

// function definition 
void extract_chars(char* s, char* a, char* d, char* p, char* w){
   int i=0, j=0, k=0, l=0, n=0;
   while(s[i]!='\0' && i<MAX_STRING_LENGTH){
      if( isalpha( (int) s[i]) ) a[j++] = s[i];
      else if( isdigit( (int) s[i]) ) d[k++] = s[i];
      else if( ispunct( (int) s[i]) ) p[l++] = s[i];
      else if( isspace( (int) s[i]) ) w[n++] = s[i];
      i++;
   }
   a[j] = '\0';
   d[k] = '\0';
   p[l] = '\0';
   w[n] = '\0';
}

int strlength(char* f){
   int i=1;                                                      
   while(f[i] != '\0'){                                      
      i++;                                                                
   }
   return i;
}    
