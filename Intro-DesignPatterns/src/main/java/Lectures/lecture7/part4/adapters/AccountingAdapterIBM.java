package Lectures.lecture7.part4.adapters;

import ConcreteFactory.AccountingSystem.AccountingSystem;

public class AccountingAdapterIBM implements IAccountingAdapter {

    private AccountingSystem accountingSystem;

    public AccountingAdapterIBM() {
        accountingSystem = new AccountingSystem("IBM");
    }

    @Override
    public void finishSale() {
        accountingSystem.registerSale();
    }

    @Override
    public void registerTax() {
        accountingSystem.calculateTax();
    }
}
