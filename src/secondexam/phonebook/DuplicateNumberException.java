package secondexam.phonebook;

public class DuplicateNumberException extends RuntimeException{
    public DuplicateNumberException(String number) {
        super(String.format("Duplicate number: %s",number));
    }
}
