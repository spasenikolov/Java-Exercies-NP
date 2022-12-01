package lab;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Contact{
    private String date;


    public Contact(String date) {
        this.date = date;
    }

    public boolean isNewerThan(Contact c){
       String thisContact = String.join("", this.date.split("-"));
       String otherContact = String.join("", c.date.split("-"));
       int thisContactInteger = Integer.parseInt(thisContact);
       int otherContactInteger = Integer.parseInt(otherContact);
       return thisContactInteger>otherContactInteger;
    }

    public abstract String getType();


}
