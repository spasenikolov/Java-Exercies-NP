package TripleTest;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TripleTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        Triple<Integer> tInt = new Triple<Integer>(a, b, c);
        System.out.printf("%.2f\n", tInt.max());
        System.out.printf("%.2f\n", tInt.average());
        tInt.sort();
        System.out.println(tInt);


        float fa = Float.parseFloat(String.join(".",scanner.next().split("\\.")));
        float fb = Float.parseFloat(String.join(".",scanner.next().split("\\.")));
        float fc = Float.parseFloat(String.join(".",scanner.next().split("\\.")));
        Triple<Float> tFloat = new Triple<Float>(fa, fb, fc);
        System.out.printf("%.2f\n", tFloat.max());
        System.out.printf("%.2f\n", tFloat.average());
        tFloat.sort();
        System.out.println(tFloat);
        double da = Double.parseDouble(String.join(".", scanner.next().split("\\.")));
        double db = Double.parseDouble(String.join(".", scanner.next().split("\\.")));
        double dc = Double.parseDouble(String.join(".", scanner.next().split("\\.")));
        Triple<Double> tDouble = new Triple<Double>(da, db, dc);
        System.out.printf("%.2f\n", tDouble.max());
        System.out.printf("%.2f\n", tDouble.average());
        tDouble.sort();
        System.out.println(tDouble);
    }
}
// vasiot kod ovde
// class Triple



