package Lectures.lecture7.part7.adapters;

import ConcreteFactory.AccountingSystem.AccountingSystem;

public class AccountingAdapterItautec extends AccountingAdapter {

    public AccountingAdapterItautec() {
        accountingSystem = new AccountingSystem("Itautec");
    }
}
