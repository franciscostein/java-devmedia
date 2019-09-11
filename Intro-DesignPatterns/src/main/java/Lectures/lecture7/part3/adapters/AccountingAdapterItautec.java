package Lectures.lecture7.part3.adapters;

import ConcreteFactory.AccountingSystem.AccountingSystem;

public class AccountingAdapterItautec implements IAccountingAdapter {

    private AccountingSystem accountingSystem;

    public AccountingAdapterItautec() {
        accountingSystem = new AccountingSystem("Itautec");
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
