package weatherStation;

import java.util.Calendar;
import java.util.Date;

public class Measurments {
    Float temperature;
    Float wind;
    Float humidity;
    Float visibility;
    Date date;

    public Measurments(Float temperature, Float wind, Float humidity, Float visibility, Date date) {
        this.temperature = temperature;
        this.wind = wind;
        this.humidity = humidity;
        this.visibility = visibility;
        this.date = date;
    }

    @Override
    public String toString() {
        return temperature + " " + wind + " km/h " + humidity + "% " + visibility + "km " + date;
    }
}
