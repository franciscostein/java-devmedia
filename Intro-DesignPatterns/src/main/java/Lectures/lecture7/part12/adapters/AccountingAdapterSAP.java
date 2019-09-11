package Lectures.lecture7.part12.adapters;

import ConcreteFactory.AccountingSystem.AccountingSystem;

public class AccountingAdapterSAP extends AccountingAdapter {

    public AccountingAdapterSAP() {
        accountingSystem = new AccountingSystem("SAP");
    }
}
