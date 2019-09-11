package ConcreteFactory.AccountingSystem;

public class AccountingSystem {

    private String name;

    public AccountingSystem(String name) {
        this.name = name;
    }

    public void registerSale() {
        System.out.println("Sale registered at " + name + " on the Accounting System\n");
    }

    public void calculateTax() {
        System.out.println("Taxes calculated at " + name + " on the Accounting System\n");
    }
}
