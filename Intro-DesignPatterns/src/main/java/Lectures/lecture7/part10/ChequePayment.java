package Lectures.lecture7.part10;

public class ChequePayment extends Payment {

    private String bank;

    public ChequePayment(Double amount, String bank) {
        super(amount);
        this.bank = bank;
    }

    public String getBank() {
        return bank;
    }

    @Override
    public String toString() {
        return "ChequePayment{" +
                "bank='" + bank + '\'' +
                ", amount=" + amount +
                '}';
    }
}
