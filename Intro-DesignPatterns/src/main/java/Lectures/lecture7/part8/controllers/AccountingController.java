package Lectures.lecture7.part8.controllers;

import Lectures.lecture7.part8.adapters.*;

public class AccountingController {

    private AccountingAdapter accountingAdapter;
    private AdapterFactory factory;

    public AccountingController() {
        System.out.println("Accounting Controller created");
        factory = new AdapterFactory();
    }

    public void createAccountingAdapter(String name) {
        accountingAdapter = factory.createAccountingAdapter(name);
    }

    public void calculateTax() {
        accountingAdapter.registerTax();
    }
}
