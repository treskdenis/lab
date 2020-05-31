all: run
clean:
        rm -f out/Main.jar out/JacobiThread.jar
out/Main.jar: out/parcs.jar src/Main.java src/MyMatrix.java src/MyVector.java src/MatrixGenerator.java
        @javac -cp out/parcs.jar src/Main.java src/MyMatrix.java src/MyVector.java src/MatrixGenerator.java
        @jar cf out/Main.jar -C src Main.class -C src MyMatrix.class -C src MyVector.class -C src MatrixGenerator.class
        @rm -f src/Main.class src/MyVector.class src/MyVector.class src/MatrixGenerator.class
out/JacobiThread.jar: out/parcs.jar src/JacobiThread.java src/MyMatrix.java src/MyVector.java
        @javac -cp out/parcs.jar src/JacobiThread.java src/MyMatrix.java src/MyVector.java
        @jar cf out/JacobiThread.jar -C src JacobiThread.class -C src MyMatrix.class -C src MyVector.class
        @rm -f src/JacobiThread.class src/MyMatrix.class src/MyVector.class
build: out/Main.jar out/JacobiThread.jar
run: out/Main.jar out/JacobiThread.jar
        @cd out && java -cp 'parcs.jar:Main.jar' Main