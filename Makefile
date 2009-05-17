Includes := -I /usr/include
LIBS := -L /usr/lib -lgmp -L. -ljcl
CFLAGS := -shared -static -Wl,-rpath,`pwd`

java: *.java libgmpjava jni
	javac *.java	

jni:GMP.java
	javac GMP.java
	javah -jni GMP	


libgmpjava:GMP.c libjcl
	gcc $(Includes) GMP.c -o libnativegmp.so $(LIBS) $(CFLAGS)

libjcl:jcl.c
	gcc  $(Includes) -L /usr/lib/  jcl.c -o libjcl.so $(CFLAGS)

jar: java
	jar cvf GMPInteger.jar *.class

clean: 
	rm -rf *.class *.so *~ *.jar