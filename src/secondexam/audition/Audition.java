package secondexam.audition;

import java.util.*;
import java.util.stream.Collectors;

public class Audition {

    private Map<String, Map<String,Participant>> participantsByCity;

    public Audition() {
        this.participantsByCity = new TreeMap<>();
    }

    public void addParticipant(String city, String code, String name, int age) {
    participantsByCity.putIfAbsent(city, new TreeMap<>());
    Participant toAdd = new Participant(code, name, age);
    participantsByCity.get(city).putIfAbsent(toAdd.getCode(), toAdd);
    }

    public void listByCity(String city) {
        new ArrayList<>(participantsByCity.get(city).values())
                .stream()
                .sorted(Comparator.comparing(Participant::getName)
                        .thenComparing(Participant::getAge))

                .forEach(System.out::println);
    }

}
