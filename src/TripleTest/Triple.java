package TripleTest;

import java.util.ArrayList;
import java.util.List;

//wierd parsing
//ask someone

//not working with this code
//supposed to do the same thing
/*public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int a = scanner.nextInt();
		int b = scanner.nextInt();
		int c = scanner.nextInt();
		Triple<Integer> tInt = new Triple<Integer>(a, b, c);
		System.out.printf("%.2f\n", tInt.max());
		System.out.printf("%.2f\n", tInt.avarage());
		tInt.sort();
		System.out.println(tInt);
		float fa = scanner.nextFloat();
		float fb = scanner.nextFloat();
		float fc = scanner.nextFloat();
		Triple<Float> tFloat = new Triple<Float>(fa, fb, fc);
		System.out.printf("%.2f\n", tFloat.max());
		System.out.printf("%.2f\n", tFloat.avarage());
		tFloat.sort();
		System.out.println(tFloat);
		double da = scanner.nextDouble();
		double db = scanner.nextDouble();
		double dc = scanner.nextDouble();
		Triple<Double> tDouble = new Triple<Double>(da, db, dc);
		System.out.printf("%.2f\n", tDouble.max());
		System.out.printf("%.2f\n", tDouble.avarage());
		tDouble.sort();
		System.out.println(tDouble);
	}

 */
public class Triple<T extends Number> {
    T firstNumber;
    T secondNumber;
    T thirdNumber;

    public Triple(T firstNumber, T secondNumber, T thirdNumber) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        this.thirdNumber = thirdNumber;
    }

    public double max() {
        return Math.max(Math.max(firstNumber.doubleValue(), secondNumber.doubleValue()), thirdNumber.doubleValue());
    }

    public double average() {
        List<Double> list = getAsDoubleList();
        return list.stream().mapToDouble(number -> number).average().getAsDouble();
    }

    void sort() {
        if (firstNumber.doubleValue() > secondNumber.doubleValue()) {
            T temp = secondNumber;
            secondNumber = firstNumber;
            firstNumber = temp;
        }
        if (firstNumber.doubleValue() > thirdNumber.doubleValue()) {
            T temp = thirdNumber;
            thirdNumber = firstNumber;
            firstNumber = temp;
        }
        if (secondNumber.doubleValue() > thirdNumber.doubleValue()) {
            T temp = thirdNumber;
            thirdNumber = secondNumber;
            secondNumber = temp;
        }

    }

    @Override
    public String toString() {
        return String.format("%.2f %.2f %.2f", firstNumber.doubleValue(), secondNumber.doubleValue(), thirdNumber.doubleValue());
    }

    private List<Double> getAsDoubleList() {
        List<Double> list = new ArrayList<>();
        list.add(firstNumber.doubleValue());
        list.add(secondNumber.doubleValue());
        list.add(thirdNumber.doubleValue());
        return list;
    }

}
