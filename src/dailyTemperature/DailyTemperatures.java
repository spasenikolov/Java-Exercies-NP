package dailyTemperature;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DailyTemperatures {
    List<DailyTemperature> dailyTemperatures;

    public DailyTemperatures() {
        dailyTemperatures = new ArrayList<>();
    }

    public void readTemperatures(InputStream in) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
        bufferedReader.lines()
                .map(DailyTemperature::new)
                .forEach(dailyTemperature -> this.dailyTemperatures.add(dailyTemperature));

    }

    public void writeDailyStats(OutputStream out, char c) {
        PrintWriter printWriter = new PrintWriter(out);

        for(DailyTemperature t: dailyTemperatures){
            if(c == 'C') t.convertTemperaturesToCelsius();
            else t.convertTemperatureToFahrenheit();
        }
        this.dailyTemperatures = this.dailyTemperatures.stream()
                .sorted(Comparator.comparingInt(temperature->Integer.parseInt(temperature.day)))
                .collect(Collectors.toList());
        dailyTemperatures.forEach(printWriter::println);
        printWriter.flush();

    }


}
