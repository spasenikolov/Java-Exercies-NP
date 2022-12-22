package maps;

import java.util.*;

public class MapTest {

    private static Map<String, List<Integer>> revertIndexOnMap (Map<Integer,String> mapToRevert){
        Map<String , List<Integer>> toReturn = new HashMap<>();
        mapToRevert.forEach((key, value) -> {
            if(!toReturn.containsKey(value)) toReturn.put(value,new ArrayList<Integer>());
            toReturn.get(value).add(key);
        });
        return toReturn;
    }

    public static void main(String[] args) {
        Map<Integer, String> daysOfWeekByNumber = new HashMap<>();
        daysOfWeekByNumber.put(1,"Monday");
        daysOfWeekByNumber.put(2,"Tuesday");
        daysOfWeekByNumber.put(3,"Wednesday");
        daysOfWeekByNumber.put(4,"Thursday");
        daysOfWeekByNumber.put(5,"Friday");
        daysOfWeekByNumber.put(6,"Saturday");
        daysOfWeekByNumber.put(7,"Sunday");
        daysOfWeekByNumber.put(8,"Monday");
        daysOfWeekByNumber.put(9,"Tuesday");

        System.out.println(daysOfWeekByNumber);
        Map<String, List<Integer>> newMap = revertIndexOnMap(daysOfWeekByNumber);
        System.out.println(newMap);


    }
}
