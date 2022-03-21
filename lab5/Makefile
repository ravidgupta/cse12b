#------------------------------------------------------------------------------
# Makefile for any ADT and its test client
#------------------------------------------------------------------------------

ADT_NAME  = Dictionary 
SOURCES   = Dictionary.c DictionaryClient.c
FILES     = $(SOURCES) $(HEADERS) DictionaryTest.c README Makefile
OBJECTS   = Dictionary.o DictionaryClient.o
HEADERS   = Dictionary.h
EXEBIN    = DictionaryClient
FLAGS     = -std=c99 -Wall
SUBMIT    = submit cmps012b-pt.s19 lab5


all: $(EXEBIN)

$(EXEBIN) : $(OBJECTS) $(HEADERS)
	gcc -o $(EXEBIN) $(OBJECTS)

$(OBJECTS) : $(SOURCES) $(HEADERS)
	gcc -c $(FLAGS) $(SOURCES)

clean :
	rm -f $(EXEBIN) $(OBJECTS)

check:
	valgrind --leak-check=full $(EXEBIN) 

submit: $(FILES)
	$(SUBMIT) $(FILES)

checker:
	ls /afs/cats.ucsc.edu/class/cmps012b-pt.s19/lab5/radgupta

