package timeTable;

import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TimeTable {
    List<Time> times;

    public TimeTable() {
        this.times = new ArrayList<>();
    }

    void readTimes(InputStream inputStream){
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        this.times = bufferedReader.lines()
                .flatMap(line -> Arrays.stream(line.split("\\s+")))
                .map(time->{
                    try {
                        return new Time(time);
                    }
                    catch (UnsupportedFormatException | InvalidTimeException e){
                        System.out.println(e.getMessage());
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
    public void writeTimes(OutputStream outputStream, TimeFormat format){
        PrintWriter printWriter = new PrintWriter(outputStream);
        if(format.equals(TimeFormat.FORMAT_24)){
            this.times.stream()
                    .sorted(Comparator.comparingInt(Time::getHours).thenComparing(Time::getMinutes))
                    .forEach(printWriter::println);
        }
        else if(format.equals(TimeFormat.FORMAT_AMPM)){
            this.times.stream()
                    .sorted(Comparator.comparingInt(Time::getHours).thenComparing(Time::getMinutes))
                    .map(Time::convertFrom24ToAMPM)
                    .forEach(printWriter::println);
        }
        printWriter.flush();
    }

}
