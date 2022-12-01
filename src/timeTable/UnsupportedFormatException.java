package timeTable;

public class UnsupportedFormatException extends RuntimeException {
    public UnsupportedFormatException(String time) {
        super(String.format("UnsupportedFormatException: %s",time));
    }
}
