# Ravi Gupta
# radgupta
# CMPS 12B/M
# 5/9/19
# Makefile
# This is a makefile for charType.c
#
FLAGS = -std=c99 -Wall
SOURCES = charType.c
FILES = $(SOURCES) README Makefile
OBJECTS = charType.o
EXEBIN = charType
SUBMIT = submit cmps012b-pt.s19 lab4
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
	valgrind --leak-check=full charType infile outfile
checker:
	ls /afs/cats.ucsc.edu/class/cmps012b-pt.s19/lab4/radgupta
#                                         
