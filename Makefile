Includes := -I /usr/include -I$(JAVA_HOME)/include -I$(JAVA_HOME)/include/linux
LIBS := -L /usr/lib -lgmp -L. -ljcl
CFLAGS := -Wall -shared -static -Wl,-rpath,`pwd`
JAVA_FLAGS := -Xlint

all: jar

java: jni *.java libgmpjava jni
	javac $(JAVA_FLAGS) *.java	

jni:GMP.java
	javac $(JAVA_FLAGS) GMP.java
	javah -jni GMP	

libgmpjava:GMP.c libjcl
	gcc $(Includes) GMP.c -o libnativegmp.so $(LIBS) $(CFLAGS)

libjcl:jcl.c
	gcc  $(Includes) -L /usr/lib/  jcl.c -o libjcl.so $(CFLAGS)

jar: java
	jar cvf GMP.jar GMP.class Pointer*.class 

clean: 
	rm -rf *.class *.so *~ *.jar