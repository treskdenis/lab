import java.util.*;
import java.io.*;

import parcs.*;

public class Main implements AM {
    static int numberOfThreads;
    static int n;

    private static void GenerateInputData(int size, int minValue) {
        n = size;
        MatrixGenerator generator = new MatrixGenerator();
        generator.GenerateInputData(size, size, minValue);
        generator.GenerateInputData(size, 1, minValue);
    }

    public double getDelta(MyVector xPrev, MyVector xNext) {
        double sum = 0;
        for (int i = 0; i < xPrev.getLength(); ++i) {
            sum += xNext.getItem(i) - xPrev.getItem(i);
        }
        return Math.abs(sum);
    }

    public void run(AMInfo info) {
        String matrixFileName = "matrix" + n + ".txt",
                vectorFileName = "vector" + n + ".txt",
                resultFileName = "result" + n + ".txt";
        double epsilon = 1e-4;
        int blocks = n / numberOfThreads;
        MyMatrix a = new MyMatrix(n, n);
        MyVector b = new MyVector(n);
        MyVector xPrev = new MyVector(n);
        MyVector xNext = new MyVector(n);
        MyVector temp1 = new MyVector(0);
        a.readFromFile(matrixFileName);
        b.readFromFile(vectorFileName);
        point[] p = new point[numberOfThreads];
        channel[] c = new channel[numberOfThreads];
        double time = System.currentTimeMillis();
        double precision = 0;
        System.out.println("\nComputing...");
        System.out.println("Threads: " + numberOfThreads + ", " + blocks + " rows in each");
        do {
            for (int i = 0; i < numberOfThreads; i++) {
                p[i] = info.createPoint();
                c[i] = p[i].createChannel();
                p[i].execute("JacobiThread");
            }
            xPrev.CopyVector(xNext);
            int start = 0, end = 0;
            for (int i = 0; i < numberOfThreads; ++i) {
                end = start + blocks;
                c[i].write(a);
                c[i].write(b);
                c[i].write(xPrev);
                c[i].write(start);
                c[i].write(end);
                double[] temp2 = (double[]) c[i].readObject();
                xNext.CopyItems(temp2, start, blocks);
                start = end - 1;
            }
            precision = getDelta(xPrev, xNext);
        } while (precision > epsilon);
        time = (System.currentTimeMillis() - time) / 1000;
        System.out.println("\nResult found! Saving to file " + resultFileName);
        System.out.println("\nTime: " + time);

        xNext.writeToFile(resultFileName);
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose option 1 - generate, 2 - compute");
        int option = sc.nextInt();
        if (option == 1) {
            System.out.println("Enter size, minValue");
            int size = sc.nextInt();
            int minValue = sc.nextInt();
            GenerateInputData(size, minValue);
            return;
        }
        System.out.println("Enter size of the matrix and number of threads");
        n = sc.nextInt();
        numberOfThreads = sc.nextInt();
        if (n % numberOfThreads != 0) {
            System.out.println("Number of rows must be divisible by the number of threads");
            return;
        }
        task curtask = new task();
        curtask.addJarFile("JacobiThread.jar");
        (new Main()).run(new AMInfo(curtask, (channel) null));
        curtask.end();
    }
}