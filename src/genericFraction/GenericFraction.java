package genericFraction;

public class GenericFraction<T extends Number, U extends Number> {
    T numerator;
    U denominator;

    public GenericFraction(T numerator, U denominator) throws ZeroDenominatorException{
        if (denominator.doubleValue() == 0.0) throw new ZeroDenominatorException();
        this.numerator = numerator;
        this.denominator = denominator;
    }
    GenericFraction<Double, Double> add(GenericFraction<? extends Number, ? extends Number> gf) {

        Double denominatorToReturn = this.denominator.doubleValue() * gf.denominator.doubleValue();
        Double numeratorToReturn = 1.0;
        if(this.denominator.doubleValue() != gf.denominator.doubleValue()){
            numeratorToReturn =
                    (this.numerator.doubleValue()
                            * (gf.denominator.doubleValue()))
                            + gf.numerator.doubleValue() * this.denominator.doubleValue();
        }

        else {
            denominatorToReturn = this.denominator.doubleValue();
            numeratorToReturn = this.numerator.doubleValue() + gf.numerator.doubleValue();
        }

        Double devider = numeratorToReturn;
        while (devider >0){
            if(denominatorToReturn%devider == 0 && numeratorToReturn % devider == 0){
                denominatorToReturn /= devider;
                numeratorToReturn /= devider;
            }
            devider --;

        }


        return new GenericFraction<Double, Double>(numeratorToReturn, denominatorToReturn);

    }

    double toDouble(){
        return this.numerator.doubleValue() / this.denominator.doubleValue();
    }

    @Override
    public String toString() {
        return String.format("%.2f / %.2f", this.numerator.doubleValue(), this.denominator.doubleValue());
    }
}
