# Ravi Gupta
# radgupta
# CMPS 12B/M
# 4/24/19
# Makefile
# This is a makefile for Queens.java
JAVASRC   = Queens.java
SOURCES   = Makefile $(JAVASRC)
MAINCLASS = Queens
CLASSES   = Queens.class
JARFILE   = Queens
SUBMIT    = submit cmps012b-pt.s19 pa2

all: $(JARFILE)

$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(CLASSES)
	rm Manifest
	chmod +x $(JARFILE)

$(CLASSES): $(JAVASRC)
	javac -Xlint $(JAVASRC)

clean:
	rm: $(CLASSES) $(JARFILE)

submit: $(SOURCES)
	$(SUBMIT) $(SOURCES)

check:
	ls /afs/cats.ucsc.edu/class/cmps012b-pt.s19/pa2/radgupta
                                                                
