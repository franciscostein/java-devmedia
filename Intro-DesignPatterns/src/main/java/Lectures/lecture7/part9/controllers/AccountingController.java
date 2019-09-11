package Lectures.lecture7.part9.controllers;

import Lectures.lecture7.part9.adapters.AccountingAdapter;
import Lectures.lecture7.part9.adapters.AdapterFactory;

public class AccountingController {

    private AccountingAdapter accountingAdapter;
    private AdapterFactory factory;

    public AccountingController() {
        System.out.println("Accounting Controller created");
        factory = AdapterFactory.getInstance();
    }

    public void createAccountingAdapter(String name) {
        accountingAdapter = factory.createAccountingAdapter(name);
    }

    public void calculateTax() {
        accountingAdapter.registerTax();
    }
}
