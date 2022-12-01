package mojDDV;

public class CalculateTaxB implements TaxStrategy{
    public CalculateTaxB() {
    }

    @Override
    public double calculateTax(Integer price) {
        return 0.05 * price;
    }
}
