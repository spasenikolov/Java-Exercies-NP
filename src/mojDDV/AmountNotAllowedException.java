package mojDDV;

public class AmountNotAllowedException extends RuntimeException {
    public AmountNotAllowedException(Integer sum) {
        super(String.format("Receipt with amount %d is not allowed to be scanned", sum));
    }
}
