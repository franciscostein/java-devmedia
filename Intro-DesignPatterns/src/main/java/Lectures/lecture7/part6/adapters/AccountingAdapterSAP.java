package Lectures.lecture7.part6.adapters;

import ConcreteFactory.AccountingSystem.AccountingSystem;

public class AccountingAdapterSAP implements IAccountingAdapter {

    private AccountingSystem accountingSystem;

    public AccountingAdapterSAP() {
        accountingSystem = new AccountingSystem("SAP");
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
