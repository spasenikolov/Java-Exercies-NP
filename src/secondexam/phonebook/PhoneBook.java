package secondexam.phonebook;

import java.util.*;
import java.util.stream.Collectors;

public class PhoneBook {
    private Map<String, Set<Contact>> contactsByName;
    private Map<String, Contact> contactByNumber;

    public PhoneBook() {
        this.contactsByName = new HashMap<>();
        this.contactByNumber = new HashMap<>();
    }

    public void addContact(String name, String number) throws DuplicateNumberException {
        //if exists in contactByNumber throw exception
        Contact contact = new Contact(name, number);
        if (contactByNumber.containsKey(number)) {
            throw new DuplicateNumberException(number);
        }
        contactByNumber.put(number, contact);
        contactsByName.putIfAbsent(name, new HashSet<>());
        contactsByName.get(name).add(contact);

    }

    public void contactsByNumber(String number) {

        long count = contactByNumber.entrySet()
                .stream()
                .filter(stringContactEntry -> stringContactEntry.getKey().contains(number))
                .map(Map.Entry::getValue)
                .sorted(Comparator.comparing(Contact::getName).thenComparing(Contact::getNumber))
                .peek(System.out::println)
                .count();
        if (count == 0) System.out.println("NOT FOUND");
    }

    public void contactsByName(String part) {
        if (!contactsByName.containsKey(part)) {
            System.out.println("NOT FOUND");
        } else {
            contactsByName.get(part)
                    .stream()
                    .sorted(Comparator.comparing(Contact::getName).thenComparing(Contact::getNumber))
                    .forEach(System.out::println);
        }
    }
}
