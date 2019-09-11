package Lectures.lecture7.part8.controllers;

import Lectures.lecture7.part8.adapters.*;

public class Register {

    private InventoryAdapter inventoryAdapter;
    private AccountingAdapter accountingAdapter;
    private AdapterFactory factory;

    public Register() {
        System.out.println("Register Controller created\n");
        factory = new AdapterFactory();
    }

    public void createAccountingAdapter(String name) {
        accountingAdapter = factory.createAccountingAdapter(name);
    }

    public void createInventoryAdapter(String name) {
        inventoryAdapter = factory.createInventoryAdapter(name);
    }

    public void decreaseItemQuantity() {
        inventoryAdapter.decreaseItemQuantity();
    }

    public void registerSaleAtAccountingSystem() {
        accountingAdapter.finishSale();
    }
}
