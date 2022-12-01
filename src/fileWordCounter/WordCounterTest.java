package fileWordCounter;

import java.io.*;
import java.util.*;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class WordCounterTest {

    private static final List<String> wordsToCheck = Arrays.asList("dva", "tri", "tekst", "ova", "predmet");

    private static Integer countWords(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        long count = bufferedReader.lines()
                .flatMap(line -> Arrays.stream(line.split("\\s+")))
                .mapToInt(words -> 1).count();

        return Long.valueOf(count).intValue();
    }

    private static boolean checkIfContainsWords(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        return bufferedReader.lines()
                .flatMap(line -> Arrays.stream(line.split("\\s+")))
                .anyMatch(wordsToCheck::contains);

    }

    private static Map<String, Integer> findMostUsedWord(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        Map<String, Integer> wordsByNumberOfTimesUsed = new TreeMap<>();

        bufferedReader.lines()
                .flatMap(line -> Arrays.stream(line.split("\\s+")))
                .forEach(word -> addToMap(wordsByNumberOfTimesUsed, word));

        return wordsByNumberOfTimesUsed;

    }
    private static String mostUsedWordFromStaticArray(InputStream inputStream){
        Map<String, Integer> numberOfUsagesByWords = new TreeMap<>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        bufferedReader.lines()
                .flatMap(line -> Arrays.stream(line.split("\\s+")))
                .filter(wordsToCheck::contains)
                .forEach(word -> addToMap(numberOfUsagesByWords,word));

        Integer mostUsedWordByFrequency = numberOfUsagesByWords.values().stream()
                .mapToInt(i -> i)
                .max()
                .orElse(0);

        Map<Integer, String> reversedMap = reverseIndexOnMap(numberOfUsagesByWords);
        return reversedMap.get(mostUsedWordByFrequency);
    }
    private static Map<String, Long> numberOfTimesUsedByWords (InputStream inputStream){
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
               return bufferedReader.lines()
                .flatMap(line -> Arrays.stream(line.split("\\s+")))
                        .collect(groupingBy(String::valueOf, counting() ));
    }

    private static Map<Integer, String> reverseIndexOnMap(Map<String, Integer> mapToReverse) {
        Map<Integer, String> toReturn = new TreeMap<>();
        mapToReverse.forEach((key, value) -> toReturn.putIfAbsent(value, key));
        return toReturn;
    }

    private static void addToMap(Map<String, Integer> wordsByNumberOfTimesUsed, String word) {

        if (wordsByNumberOfTimesUsed.containsKey(word)) {
            wordsByNumberOfTimesUsed.put(word, wordsByNumberOfTimesUsed.get(word) + 1);
        }
        else {
            wordsByNumberOfTimesUsed.putIfAbsent(word, 1);
        }
    }

    public List<String> getWordsToCheck() {
        return wordsToCheck;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        StringBuilder stringBuilder = new StringBuilder();
        while (scanner.hasNextLine()) {
            stringBuilder.append(scanner.nextLine());
            stringBuilder.append(" ");
        }


        InputStream targetStream = new ByteArrayInputStream(stringBuilder.toString().getBytes());
        InputStream targetStream2 = new ByteArrayInputStream(stringBuilder.toString().getBytes());
        InputStream targetStream3 = new ByteArrayInputStream(stringBuilder.toString().getBytes());
        InputStream targetStream4 = new ByteArrayInputStream(stringBuilder.toString().getBytes());
        InputStream targetStream5 = new ByteArrayInputStream(stringBuilder.toString().getBytes());
        System.out.println(countWords(targetStream));
        System.out.println(checkIfContainsWords(targetStream2));
        Map<String, Integer> testMap = findMostUsedWord(targetStream3);

//        Map<String, Integer> testMap = new HashMap<>();
//        testMap.put("eden", 1);
//        testMap.put("dva", 2);
//        testMap.put("tri", 3);
//        testMap.put("cet", 4);
//        testMap.put("pet", 5);
//
//        System.out.println("Pred revert index method: \n" + testMap.toString());
//        System.out.println("Posle revert index method: \n" + reverseIndexOnMap(testMap).toString());
        Map<Integer, String> mapToWorkWith = reverseIndexOnMap(testMap);
        String no_element_containing = mapToWorkWith.keySet().stream()
                .max(Integer::compareTo)
                .map(mapToWorkWith::get)
                .orElse("No element containing");

        System.out.println(no_element_containing);

        System.out.println("Testin most used word from static array:");
        System.out.println(mostUsedWordFromStaticArray(targetStream4));

        System.out.println("Testing new method for grouping:");
        System.out.println(new TreeMap<>(numberOfTimesUsedByWords(targetStream5)));


    }
}
