package Lectures.lecture7.part12.controllers;

import Lectures.lecture7.part12.adapters.AccountingAdapter;
import Lectures.lecture7.part12.adapters.AdapterFactory;

public class AccountingController {

    private AccountingAdapter accountingAdapter;
    private AdapterFactory factory;

    public AccountingController() {
        System.out.println("Accounting Controller created");
        factory = AdapterFactory.getInstance();
    }

    public void createAccountingAdapter(String name) {
        accountingAdapter = factory.createAccountingAdapter();
    }

    public void calculateTax() {
        accountingAdapter.registerTax();
    }
}
