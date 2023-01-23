package secondexam.findanagrams;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

public class Anagrams {
    static Map<String, Set<Character>> charactersByWord = new LinkedHashMap<>();
    public static void main(String[] args) {
        findAll(System.in);
    }

    public static void findAll(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        bufferedReader.lines().forEach(Anagrams::addToMap);
        printAnagrams(charactersByWord);

    }
    private static void printAnagrams(Map<String, Set<Character>> charactersByWord){
        Map<String, String> newMap = convertValueFromSetToString(charactersByWord);
        Map<String, List<String>> finalMap = revertMap(newMap);

        StringBuilder sb = new StringBuilder();
        finalMap.values().stream().filter(value -> value.size() > 4)
                .forEach(list->{
                    list.forEach(word -> {
                        sb.append(word);
                        if(!list.get(list.size()-1).equals(word)) sb.append(" ");

                    });
                    sb.append("\n");
                } );
        System.out.println(sb);

    }
    private static void addToMap(String word){
        charactersByWord.putIfAbsent(word,new TreeSet<>());
        IntStream.range(0, word.length()).forEach(i -> {
            charactersByWord.get(word).add(word.charAt(i));
        });
    }
    private static Map<String, String> convertValueFromSetToString(Map<String, Set<Character>> toConvert) {
        Map<String, String> convertedMap = new LinkedHashMap<>();
        toConvert.forEach((key, value) -> {
            convertedMap.put(key, fromSetToString(value));
        });
        return convertedMap;
    }
    private static String fromSetToString(Set<Character> characters){
        StringBuilder sb = new StringBuilder();
        characters.forEach(sb::append);
        return sb.toString();
    }
    private static Map<String, List<String>> revertMap(Map<String, String> theMap){
        Map<String, List<String>> newMap = new LinkedHashMap<>();
        theMap.forEach((key,value)->{
            newMap.putIfAbsent(value, new ArrayList<>());
            newMap.get(value).add(key);
        });
        return newMap;
    }
}
