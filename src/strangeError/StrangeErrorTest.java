package strangeError;

import java.util.Locale;
import java.util.Scanner;

public class StrangeErrorTest {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        //working with .
        //not working with ,
        float fb = Float.parseFloat(scanner.next());

        //working with ,
        //not working with .

        float fc = scanner.nextFloat();

    }

}
