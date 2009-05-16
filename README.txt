GMP Java is a general-purpose JNI wrapper to the GMP library. It's
heavily based on the wrapper written in GNU Classpath for Big
Integers (GNU Classpath uses GMP for BigInteger objects). This wrapper
does nothing more than make it easier to use GMP from Java. 

The original copyright notices have been left intact. It should be
noted that this work is copyright (C) 2009 didier
deshommes<dfdeshom@gmail.com> and released under the GPL 2 or later. 


Building and running
--------------------

You'll need a recent JVM, the GMP library and its sources to build
this module. To build it, simply type `make`. When the sources finish
building, type:

$ java GMP
Java_GMP_natInitializeLibrary(GMP.c:99) -- Loading GMP-based
BigInteger native library
Java_GMP_natInitializeLibrary(GMP.c:103) -- Loaded GMP-based
BigInteger native library
Java_GMP_natInitialize(GMP.c:117) -- begin
Java_GMP_natInitialize(GMP.c:125) -- end
Initializing a GMP number with value 9...
Java_GMP_natFromLong(GMP.c:168) -- begin
Java_GMP_natFromLong(GMP.c:195) -- end
done
Java_GMP_natToString(GMP.c:372) -- begin
Java_GMP_natToString(GMP.c:377) -- end
Value: 9
$

TODO
----
 * Finish exposing all GMP functions to java

