package Lectures.lecture7.part6.controllers;

import Lectures.lecture7.part6.adapters.AccountingAdapterIBM;
import Lectures.lecture7.part6.adapters.AccountingAdapterItautec;
import Lectures.lecture7.part6.adapters.AccountingAdapterSAP;
import Lectures.lecture7.part6.adapters.IAccountingAdapter;

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
        } else if (name.equals("SAP")) {
            accountingAdapter = new AccountingAdapterSAP();
        }
    }

    public void calculateTax() {
        accountingAdapter.registerTax();
    }
}
