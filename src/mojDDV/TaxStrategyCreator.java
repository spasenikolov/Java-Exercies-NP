package mojDDV;

public class TaxStrategyCreator {
    TaxStrategy taxStrategy;

    public TaxStrategyCreator(TaxStrategy taxStrategy) {
        this.taxStrategy = taxStrategy;
    }
    public static TaxStrategy createTaxStrategy(String type){
        if(type.equals("A")) return new CalculateTaxA();
        else if(type.equals("B")) return new CalculateTaxB();
        else return new CalculateTaxV();
    }
}
