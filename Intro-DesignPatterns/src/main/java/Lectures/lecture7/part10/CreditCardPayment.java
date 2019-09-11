package Lectures.lecture7.part10;

public class CreditCardPayment extends Payment {

    private String company;

    public CreditCardPayment(double amount, String company) {
        super(amount);
        this.company = company;
    }

    public String getCompany() {
        return company;
    }

    @Override
    public String toString() {
        return "CreditCardPayment{" +
                "company='" + company + '\'' +
                ", amount=" + amount +
                '}';
    }
}
