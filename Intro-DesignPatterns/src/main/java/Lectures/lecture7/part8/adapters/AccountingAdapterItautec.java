package Lectures.lecture7.part8.adapters;

import ConcreteFactory.AccountingSystem.AccountingSystem;

public class AccountingAdapterItautec extends AccountingAdapter {

    public AccountingAdapterItautec() {
        accountingSystem = new AccountingSystem("Itautec");
    }
}
