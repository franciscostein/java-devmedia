package Lectures.lecture7.part8.adapters;

import ConcreteFactory.AccountingSystem.AccountingSystem;

public abstract class AccountingAdapter {

    protected AccountingSystem accountingSystem;

    public void finishSale() {
        accountingSystem.registerSale();
    }

    public void registerTax() {
        accountingSystem.calculateTax();
    }
}
