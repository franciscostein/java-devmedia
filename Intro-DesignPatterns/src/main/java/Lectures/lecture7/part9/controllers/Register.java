package Lectures.lecture7.part9.controllers;

import Lectures.lecture7.part9.adapters.AccountingAdapter;
import Lectures.lecture7.part9.adapters.AdapterFactory;
import Lectures.lecture7.part9.adapters.InventoryAdapter;

public class Register {

    private InventoryAdapter inventoryAdapter;
    private AccountingAdapter accountingAdapter;
    private AdapterFactory factory;

    public Register() {
        System.out.println("Register Controller created\n");
        factory = AdapterFactory.getInstance();
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
