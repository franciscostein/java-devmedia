package Lectures.lecture7.part7.controllers;

import Lectures.lecture7.part7.adapters.AccountingAdapterIBM;
import Lectures.lecture7.part7.adapters.AccountingAdapterItautec;
import Lectures.lecture7.part7.adapters.AccountingAdapterSAP;
import Lectures.lecture7.part7.adapters.AccountingAdapter;

public class AccountingController {

    private AccountingAdapter accountingAdapter;

    public AccountingController() {
        System.out.println("Accounting Controller created");
    }

    public void createAccountingAdapter(String name) {
        if (name.equals("IBM")) {
            accountingAdapter = new AccountingAdapterIBM();
        } else if (name.equals("Itautec")) {
            accountingAdapter = new AccountingAdapterItautec();
        } else if (name.equals("SAP")) {
            accountingAdapter = new AccountingAdapterSAP();
        }
    }

    public void calculateTax() {
        accountingAdapter.registerTax();
    }
}
