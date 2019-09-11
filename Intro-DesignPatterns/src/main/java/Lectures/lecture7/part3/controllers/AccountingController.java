package Lectures.lecture7.part3.controllers;

import Lectures.lecture7.part3.adapters.AccountingAdapterIBM;
import Lectures.lecture7.part3.adapters.AccountingAdapterItautec;
import Lectures.lecture7.part3.adapters.IAccountingAdapter;

public class AccountingController {

    private IAccountingAdapter accountingAdapter;

    public AccountingController() {
        System.out.println("Accounting Controller created");
    }

    public void createAccountingAdapter(String name) {
        if (name.equals("IBM")) {
            accountingAdapter = new AccountingAdapterIBM();
        } else if (name.equals("Itautec")) {
            accountingAdapter = new AccountingAdapterItautec();
        }
    }

    public void calculateTax() {
        accountingAdapter.registerTax();
    }
}
