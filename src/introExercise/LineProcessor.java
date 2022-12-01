package introExercise;

import java.io.*;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collector;


public class LineProcessor {
    public void readLines(InputStream in, OutputStream out, char a) throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
        PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(out));
        Map<String, Integer> frequencyByLines = new TreeMap<>();
        bufferedReader.lines()
                .forEach(line->{
                    for (char c : line.toCharArray()) {
                        if(c == a)
                            frequencyByLines.merge(line, 1, Integer::sum);
                    }
                });
        Integer max = frequencyByLines.values().stream()
                .max(Integer::compareTo)
                .get();
        String lineToPrint = reverseMap(frequencyByLines).get(max);
        printWriter.println(lineToPrint);
        printWriter.flush();
    }
    private static Map<Integer, String> reverseMap(Map<String,Integer> mapToReverse){
        Map<Integer, String> toReturn = new TreeMap<>();
        mapToReverse.forEach((key, value) -> toReturn.putIfAbsent(value, key));
        return toReturn;
    }

}
