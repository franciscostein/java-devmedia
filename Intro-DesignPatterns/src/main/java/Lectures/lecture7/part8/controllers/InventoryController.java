package Lectures.lecture7.part8.controllers;

import Lectures.lecture7.part8.adapters.*;

public class InventoryController {

    private InventoryAdapter inventoryAdapter;
    private AdapterFactory factory;

    public InventoryController() {
        System.out.println("Inventory Controller created\n");
        factory = new AdapterFactory();
    }

    public void createInventoryAdapter(String name) {
        inventoryAdapter = factory.createInventoryAdapter(name);
    }

    public void updateInventory() {
        inventoryAdapter.updateInventoryQuantity();
    }
}
