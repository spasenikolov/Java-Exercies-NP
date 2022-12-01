package mojDDV;

public class CalculateTaxA implements TaxStrategy{

    public CalculateTaxA() {
    }

    @Override
    public double calculateTax(Integer price) {
        return 0.18 * price;
    }
}
