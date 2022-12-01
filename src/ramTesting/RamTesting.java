package ramTesting;

import java.util.Arrays;

public class RamTesting {
    public static void main(String[] args) {
        String time1 = "12-21";
        String time2 = "12-21";

        String[] parts1 = time1.split("[:.]");
        String[] parts2 = time2.split("[:.]");

        System.out.println(Arrays.toString(parts1));
        System.out.println(Arrays.toString(parts2));
    }
}
