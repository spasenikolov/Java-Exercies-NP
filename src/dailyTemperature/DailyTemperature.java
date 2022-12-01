package dailyTemperature;

import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

public class DailyTemperature {
    String day;
    List<Temperature> temperatures;
    Character type;

    public DailyTemperature(String line) {
        this.day = line.split("\\s+")[0];
        this.temperatures = crateTemperaturesFromLine(line);


    }


    private List<Temperature> crateTemperaturesFromLine(String line) {
        String[] parts = line.split("\\s+");

        return Arrays.stream(parts)
                .skip(1)
                .map(oneTemperature
                        -> new Temperature(Double.parseDouble(oneTemperature.substring(0, oneTemperature.length() - 1))
                        , oneTemperature.charAt(oneTemperature.length()-1)))
                .collect(Collectors.toList());
    }



    @Override
    public String toString() {
       return String.format("%3s: Count: %2d Min: %6.2f%s Max: %6.2f%s Avg: %6.2f%s"
               , this.day
               , this.temperatures.size()
               , getStatistics().getMin()
               , this.type
               , getStatistics().getMax()
               , this.type
               ,getStatistics().getAverage()
               ,this.type);

    }

    private DoubleSummaryStatistics getStatistics(){
        return temperatures.stream().mapToDouble(t -> t.temperature).summaryStatistics();
    }

    public void convertTemperaturesToCelsius(){
        this.temperatures.forEach(Temperature::convertFromFahrenheitToCelsius);
        this.type = 'C';
    }
    public void convertTemperatureToFahrenheit(){
        this.temperatures.forEach(Temperature::convertFromCelsiusToFahrenheit);
        this.type = 'F';
    }


}
