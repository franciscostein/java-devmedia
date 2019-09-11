package Lectures.lecture7.part9.adapters;

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

    public AccountingAdapter createAccountingAdapter(String name) {
        if (name.equals("IBM")) {
            accountingAdapter = new AccountingAdapterIBM();
        } else if (name.equals("Itautec")) {
            accountingAdapter = new AccountingAdapterItautec();
        } else if (name.equals("SAP")) {
            accountingAdapter = new AccountingAdapterSAP();
        }
        return accountingAdapter;
    }

    public InventoryAdapter createInventoryAdapter(String name) {
        if (name.equals("IBM")) {
            inventoryAdapter = new InventoryAdapterIBM();
        } else if (name.equals("Itautec")) {
            inventoryAdapter = new InventoryAdapterItautec();
        } else if (name.equals("SAP")) {
            inventoryAdapter = new InventoryAdapterSAP();
        }
        return inventoryAdapter;
    }
}
