package lab;

public class EmailContact extends Contact{
    private final String email;
    @Override
    public String getType() {
        return "Email";
    }

    public EmailContact(String date, String email) {
        super(date);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
