# Ravi Gupta
# radgupta
# CMPS 12B/M
# 4/28/19
# Makefile
# This is a makefile for FileReverse
 
FLAGS = -std=c99 -Wall
SOURCES = FileReverse.c
FILES = $(SOURCES) README Makefile
OBJECTS = FileReverse.o
EXEBIN = FileReverse
SUBMIT = submit cmps012b-pt.s19 lab3
all: $(EXEBIN)
$(EXEBIN) : $(OBJECTS)
	gcc -o $(EXEBIN) $(OBJECTS)
$(OBJECTS) : $(SOURCES)
	gcc -c $(FLAGS) $(SOURCES)
clean :
	rm -f $(EXEBIN) $(OBJECTS)
submit: $(FILES)
	$(SUBMIT) $(FILES)
check:
	ls /afs/cats.ucsc.edu/class/cmps012b-pt.s19/lab3/radgupta

