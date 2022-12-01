package lab;

public class PhoneContact extends Contact{
    private final String phone;
    private Operator operator;
    @Override
    public String getType() {
        return "Phone";
    }

    public PhoneContact(String date, String phone) {
        super(date);
        this.phone = phone;
    }

    public Operator getOperator(){
        String firstThreeDigits = phone.split("/")[0];
        if(firstThreeDigits.equals("070") || firstThreeDigits.equals("071") || firstThreeDigits.equals("072"))
            return Operator.TMOBILE;
        else if(firstThreeDigits.equals("075") || firstThreeDigits.equals("076"))
            return Operator.ONE;
        else
            return Operator.VIP;
    }

    public String getPhone() {
        return phone;
    }
}
