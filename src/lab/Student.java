package lab;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Student {
    private String firstName;
    private String lastName;
    private String city;
    private int age;
    private long index;
    private Contact [] contacts;

    public Student(String firstName, String lastName, String city, int age, long index) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.age = age;
        this.index = index;
        contacts = new Contact[100];
    }
    void addEmailContact(String date, String email){
        for(Contact c: contacts){
            if(c.getType().equals("Email")) Arrays.asList(contacts).add(new EmailContact(date, email));
        }
    }

    void addPhoneContact(String date, String phone){
        for(Contact c: contacts){
            if(c.getType().equals("Phone")) Arrays.asList(contacts).add(new PhoneContact(date, phone));
        }
    }

    public Contact[] getEmailContacts(){
        Contact[] toReturn = new Contact[100];
        for (Contact c: contacts)
            if(c.getType().equals("Email")) Arrays.asList(toReturn).add(c);

        return toReturn;
    }
    public Contact[] getPhoneContacts(){
        Contact[] toReturn = new Contact[100];
        for (Contact c: contacts)
            if(c.getType().equals("Phone")) Arrays.asList(toReturn).add(c);

        return toReturn;
    }

    public String getCity() {
        return city;
    }

    public String getFullName(){
        return this.firstName.toUpperCase() + " " + this.lastName.toUpperCase();
    }

    public long getIndex() {
        return index;
    }
    public Contact getLatestContact(){
        Contact contact = contacts[0];
        for(Contact c: contacts){
            if(c.isNewerThan(contact)) contact=c;
        }
        return contact;
    }

    @Override
    public String toString() {
        Contact [] phoneContacts = new Contact[100];
        Contact [] emailContacts = new Contact[100];
        for (Contact c: contacts){
            if(c.getType().equals("Phone")) Arrays.asList(phoneContacts).add(c);
            else Arrays.asList(emailContacts).add(c);
        }
        return "{" +
                "\"ime\":" + "\"" + firstName + "\"" +
                ", \"lastName\":" + "\""+lastName + "\"" +
                ", \"city\":" +"\"" + city + "\"" +
                ", \"age\":" +"\"" + age + "\"" +
                ", \"index\":" +"\"" + index + "\"" +
                ", \"telefonskiKontakti\": " + Arrays.toString(phoneContacts) +
                '}';
    }
}
