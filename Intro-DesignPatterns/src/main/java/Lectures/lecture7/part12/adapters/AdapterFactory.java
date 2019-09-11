package Lectures.lecture7.part12.adapters;

//  Fabrica concreta, ou fabrica simples
public class AdapterFactory {

    private AccountingAdapter accountingAdapter;
    private InventoryAdapter inventoryAdapter;
    private static AdapterFactory instance = new AdapterFactory();

    private AdapterFactory() {
    }

    public static AdapterFactory getInstance() {
        return instance;
    }

    public AccountingAdapter createAccountingAdapter() {
        try {
            //  Professor usou uma config do Netbeans que informa a classe
            String className = System.getProperty("accountingProperty");    // ler de um arquivo

            accountingAdapter = (AccountingAdapter) Class.forName(className).newInstance();

        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return accountingAdapter;
    }

    public InventoryAdapter createInventoryAdapter() {
        try {
            String className = System.getProperty("inventoryProperty");    // ler de um arquivo

            inventoryAdapter = (InventoryAdapter) Class.forName(className).newInstance();

        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return inventoryAdapter;
    }
}
