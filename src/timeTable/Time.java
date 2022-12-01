package timeTable;

import java.util.Objects;

public class Time {
    String time;
    Integer hours;
    Integer minutes;
    TimeFormat timeFormat;

    String timeExtension;

    public Time(String time) throws UnsupportedFormatException, InvalidTimeException{
        this.time = time;
        Integer[] integers = convertFromStringToTime();
        this.hours = integers[0];
        this.minutes = integers[1];
        this.timeFormat = TimeFormat.FORMAT_24;
        this.timeExtension = "";
    }
    private Integer[] convertFromStringToTime() throws UnsupportedFormatException, InvalidTimeException{
        String [] parts = this.time.split("[:.]");
        if (parts.length!=2) throw new UnsupportedFormatException(time);
        Integer [] integers = new Integer[2];
        integers[0] = Integer.parseInt(parts[0]);
        integers[1] = Integer.parseInt(parts[1]);
        if(integers[0]<0 || integers[0]>23 || integers[1]<0 || integers[1] >59) throw new InvalidTimeException(time);
        return integers;
    }

    public String getTime() {
        return time;
    }

    public Integer getHours() {
        return hours;
    }

    public Integer getMinutes() {
        return minutes;
    }
    public Time convertFrom24ToAMPM(){
        if(this.hours == 0){
            this.timeExtension = "AM";
            this.hours += 12;
            this.timeFormat = TimeFormat.FORMAT_AMPM;

        }
        else if(this.hours > 0 && this.hours < 12){
            this.timeFormat = TimeFormat.FORMAT_AMPM;
            this.timeExtension = "AM";
        }
        else if(this.hours == 12){
            this.timeFormat = TimeFormat.FORMAT_AMPM;
            this.timeExtension = "PM";
        }
        else if(this.hours > 12 && this.hours < 24){
            this.hours -=12;
            this.timeFormat = TimeFormat.FORMAT_AMPM;
            this.timeExtension = "PM";
        }
        return this;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("%2s",this.hours))
                .append(":")
                .append(minutes<10?String.format("0%s",this.minutes):this.minutes);
        if(!Objects.equals(this.timeExtension, "")){
            stringBuilder.append(" ").append(this.timeExtension);
        }
        return stringBuilder.toString();
    }
}
