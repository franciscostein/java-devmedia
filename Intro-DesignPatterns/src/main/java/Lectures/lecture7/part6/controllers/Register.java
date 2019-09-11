package Lectures.lecture7.part6.controllers;

import Lectures.lecture7.part6.adapters.*;

public class Register {

    private IInventoryAdapter inventoryAdapter;
    private IAccountingAdapter accountingAdapter;

    public Register() {
        System.out.println("Register Controller created\n");
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

    public void createInventoryAdapter(String name) {
        if (name.equals("IBM")) {
            inventoryAdapter = new InventoryAdapterIBM();
        } else if (name.equals("Itautec")) {
            inventoryAdapter = new InventoryAdapterItautec();
        } else if (name.equals("SAP")) {
            inventoryAdapter = new InventoryAdapterSAP();
        }
    }

    public void decreaseItemQuantity() {
        inventoryAdapter.decreaseItemQuantity();
    }

    public void registerSaleAtAccountingSystem() {
        accountingAdapter.finishSale();
    }
}
