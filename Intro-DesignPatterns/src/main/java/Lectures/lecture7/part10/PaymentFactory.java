package Lectures.lecture7.part10;

public class PaymentFactory {

    private static Payment payment;

    public static Payment createPayment(String type, String bank, String company, double amount) {
        if (type.equalsIgnoreCase("Cheque")) {
            payment = new ChequePayment(amount, bank);
        } else {
            payment = new CreditCardPayment(amount, company);
        }
        return payment;
    }
}
