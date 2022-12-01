package dailyTemperature;

import java.util.DoubleSummaryStatistics;

public class Temperature {
    Double temperature;
    Character type;

    public Temperature(Double temperature, Character type) {
        this.temperature = temperature;
        this.type = type;
    }
    public void convertFromCelsiusToFahrenheit(){
        if(type.equals('C')){
            this.temperature = ((this.temperature * 9) / 5) + 32;
            this.type = 'F';
        }
    }
    public void convertFromFahrenheitToCelsius(){
        if(type.equals('F')){
            this.temperature = ((this.temperature - 32) * 5) / 9;
            this.type = 'C';
        }
    }

}
