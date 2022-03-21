# Ravi Gupta
# radgupta
# CMPS 12B/M
# 4/19/19
# Makefile
# This is a makefile for FileReverse
JAVASRC   = FileReverse.java
SOURCES   = README Makefile $(JAVASRC)
MAINCLASS = FileReverse
CLASSES   = FileReverse.class
JARFILE   = FileReverse
SUBMIT    = submit cmps012b-pt.s19 lab2

all: $(JARFILE)

$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(CLASSES)
	rm Manifest
	chmod +x $(JARFILE)

$(CLASSES): $(JAVASRC)
	javac -Xlint $(JAVASRC)

clean:
	rm $(CLASSES) $(JARFILE)

submit: $(SOURCES)
	$(SUBMIT) $(SOURCES)
check:
	ls /afs/cats.ucsc.edu/class/cmps012b-pt.s19/lab2/radgupta
