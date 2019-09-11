package Lectures.lecture7.part2.adapters;

import ConcreteFactory.AccountingSystem.AccountingSystem;

public class AccountingAdapterIBM implements IAccountingAdapter {

    private AccountingSystem accountingSystem;

    @Override
    public void finishSale() {
        accountingSystem.registerSale();
    }

    @Override
    public void registerTax() {
        accountingSystem.calculateTax();
    }
}
