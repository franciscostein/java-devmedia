package Lectures.lecture7.part9.controllers;

import Lectures.lecture7.part9.adapters.AdapterFactory;
import Lectures.lecture7.part9.adapters.InventoryAdapter;

public class InventoryController {

    private InventoryAdapter inventoryAdapter;
    private AdapterFactory factory;

    public InventoryController() {
        System.out.println("Inventory Controller created\n");
        factory = AdapterFactory.getInstance();
    }

    public void createInventoryAdapter(String name) {
        inventoryAdapter = factory.createInventoryAdapter(name);
    }

    public void updateInventory() {
        inventoryAdapter.updateInventoryQuantity();
    }
}
