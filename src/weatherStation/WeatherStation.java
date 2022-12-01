package weatherStation;

import java.time.LocalDate;
import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class WeatherStation {
    private List<Measurments> measurmentsList;
    private Integer daysToSave;

    public WeatherStation(int days) {
        measurmentsList = new ArrayList<>();
        this.daysToSave = days;
    }

    private Measurments createMeasurments(float temperature, float wind, float humidity, float visibility, Date date){
        return new Measurments(temperature,wind,humidity,visibility,date);
    }
    public void addMeasurment(float temperature, float wind, float humidity, float visibility, Date date){
        Date dateBefore = subtractFromDate(date,daysToSave);
        List<Measurments> collect = measurmentsList.stream()
                .filter(measurments -> measurments.date.after(dateBefore))
                .collect(Collectors.toList());
        measurmentsList = collect;

        boolean toAdd = true;

        for(Measurments measurments: measurmentsList){
            if(isDateTooClose(measurments.date, date)) toAdd=false;
        }
        if(toAdd)
             measurmentsList.add(createMeasurments(temperature, wind, humidity, visibility, date));
    }
    public int total(){
        return measurmentsList.size();
    }
    private boolean isDateTooClose(Date date, Date otherDate){
        if(date.getYear()==otherDate.getYear()
            && date.getMonth() == otherDate.getMonth()
            && date.getDay() == otherDate.getDay()
            && date.getHours() == otherDate.getHours()
            &&date.getDate() == otherDate.getDate()){
            return Math.abs(date.getMinutes() - otherDate.getMinutes())<2.5;
        }
        return false;
    }
    public void status(Date from, Date to) throws RuntimeException{
        List<Measurments> collect = measurmentsList.stream()
                .filter(measurments -> measurments.date.getTime()>=from.getTime() && measurments.date.getTime()<=to.getTime())
                .sorted(Comparator.comparing(o -> o.date))
                .collect(Collectors.toList());
        if (collect.isEmpty()) throw new RuntimeException();
        collect.forEach(System.out::println);


        double averageTemperature = collect.stream()
                .mapToDouble(measurment -> measurment.temperature)
                .average().getAsDouble();

        System.out.format("Average temperature: %.2f \n", averageTemperature);


    }
   private boolean checkIfBetweenDates(Date from, Date to){
        return false;
   }
    private Date subtractFromDate(Date date, int toSubtract){
        Date subtractedDate = new Date();
        subtractedDate.setYear(date.getYear());
        subtractedDate.setMonth(date.getMonth());
        subtractedDate.setDate(date.getDate()-toSubtract);
        subtractedDate.setHours(date.getHours());
        subtractedDate.setMinutes(date.getMinutes());
        subtractedDate.setSeconds(date.getSeconds());
        return subtractedDate;

    }
}
