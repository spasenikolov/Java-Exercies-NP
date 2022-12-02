package lab.complexNumbers;

import java.util.Comparator;

public class ComplexNumber<T extends Number, U extends Number> implements Comparable<ComplexNumber>{
    T real;
    U imaginary;

    public ComplexNumber(T real, U imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public T getReal() {
        return real;
    }

    public U getImaginary() {
        return imaginary;
    }
    public double modul(){
        return Math.sqrt(Math.pow(real.doubleValue(),2)+Math.pow(imaginary.doubleValue(),2));
    }

    @Override
    public int compareTo(ComplexNumber o) {
        return Double.compare(this.modul(),o.modul());
    }

    @Override
    public String toString() {
        boolean hasPlusSign = Double.compare(this.imaginary.doubleValue(),0.0)>=0;

        return String.format("%.2f%s%.2fi",this.real.doubleValue(), hasPlusSign?"+":"",this.imaginary.doubleValue());
    }
}
