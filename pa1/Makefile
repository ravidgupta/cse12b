# Ravi Gupta
# radgupta
# CMPS12B/M
# 4/14/2019
# Makefile for the Subset class
# Makefile
# -----------------------------------------------------------------------------
# Yet another Makefile with macros
# -----------------------------------------------------------------------------
#
JAVASRC   = Subset.java
SOURCES   = README Makefile $(JAVASRC)
MAINCLASS = Subset
CLASSES   = Subset.class
JARFILE   = Subset
SUBMIT    = submit cmps012b-pt.s19 pa1

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
	ls /afs/cats.ucsc.edu/class/cmps012b-pt.s19/pa1/radgupta

