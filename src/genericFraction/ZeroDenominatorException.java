package genericFraction;

public class ZeroDenominatorException extends RuntimeException {
    public ZeroDenominatorException() {
        super("Denominator cannot be zero");
    }
}
