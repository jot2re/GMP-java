Includes := -I /usr/include -I$(JAVA_HOME)/include -I$(JAVA_HOME)/include/darwin -I include -I /opt/homebrew/Cellar/openjdk/19.0.2/include/ -I /opt/homebrew/Cellar/gmp/6.2.1_1/include/
LIBS := -L /usr/lib -lgmp -L. -ljcl
CFLAGS := -Wall -shared -fPIC -Wl,-rpath,`pwd`
JAVA_FLAGS := -Xlint

all: jar

java: jni org/dfdeshom/math/*.java libgmpjava jni
	javac $(JAVA_FLAGS) org/dfdeshom/math/GMP.java org/dfdeshom/math/Pointer32.java org/dfdeshom/math/Pointer64.java

jni:
	javac $(JAVA_FLAGS) -h . org/dfdeshom/math/GMP.java

libgmpjava:GMP.c libjcl
	gcc $(Includes)  -L /opt/homebrew/Cellar/gmp/6.2.1_1/lib/ -L . GMP.c -o libnativegmp.dylib $(LIBS) $(CFLAGS)


libjcl:jcl.c
	gcc  $(Includes)  -L /usr/lib/ -L /opt/homebrew/Cellar/gmp/6.2.1_1/lib/ -L . jcl.c -o libjcl.dylib $(CFLAGS)

jar: java
	mkdir -p target
	jar cvf target/GMP.jar org/dfdeshom/math/GMP.class org/dfdeshom/math/Pointer*.class

clean:
	rm -rf org/dfdeshom/math/*.class *.so *~ target/*.jar include
	rm -rf org/dfdeshom/math/*.class

example: jar
	javac -cp target/*.jar example.java
	java  -Djava.library.path=.   example