package f1race;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class F1Test {

    public static void main(String[] args) {
        F1Race f1Race = new F1Race();
        f1Race.readResults(System.in);
        f1Race.printSorted(System.out);
    }

}

class F1Race {
    // vashiot kod ovde
    List<Driver> drivers;


    public F1Race() {
        drivers = new ArrayList<>();
    }

    public void readResults(InputStream in) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
        bufferedReader.lines()
                .forEach(line -> drivers.add(Driver.createDriver(line)));

    }

    public void printSorted(OutputStream out) {
        PrintWriter printWriter = new PrintWriter(out);
        List<Driver> toPrint = drivers.stream()
                .sorted(Comparator.comparing(driver -> Driver.convertToMilliSeconds(driver.bestLap)))
                .collect(Collectors.toList());
        int i = 0;
        for (Driver driver : toPrint) {
            printWriter.format("%d. %-10s %10s", ++i, driver.name, driver.bestLap);
            printWriter.println();
        }

        printWriter.flush();
    }

}

class Driver {
    String name;
    List<String> laps;
    String bestLap;

    @Override
    public String toString() {
        return "Driver{" +
                "name='" + name + '\'' +
                ", bestLap='" + bestLap + '\'' +
                '}';
    }

    public Driver(String name, List<String> laps) {
        this.name = name;
        this.laps = laps;
        this.bestLap = getBestLap();
    }

    public Driver() {
    }

    public static Driver createDriver(String line) {
        String[] toAdd = line.split("\\s+");
        String name = toAdd[0];
        List<String> laps = Arrays.stream(toAdd).skip(1).collect(Collectors.toList());
        return new Driver(name, laps);
    }

    public static Integer convertToMilliSeconds(String format) {
        String[] parts = format.split(":");
        Integer minutes = Integer.parseInt(parts[0]) * 60000;
        Integer seconds = Integer.parseInt(parts[1]) * 1000;
        Integer milliSeconds = Integer.parseInt(parts[2]);

        return minutes + seconds + milliSeconds;
    }

    private String getBestLap() {
        return laps.stream().min(Comparator.comparingInt(Driver::convertToMilliSeconds)).get();
    }

}