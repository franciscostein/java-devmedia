package Lectures.lecture7.part12.controllers;

import Lectures.lecture7.part12.adapters.AdapterFactory;
import Lectures.lecture7.part12.adapters.InventoryAdapter;

public class InventoryController {

    private InventoryAdapter inventoryAdapter;
    private AdapterFactory factory;

    public InventoryController() {
        System.out.println("Inventory Controller created\n");
        factory = AdapterFactory.getInstance();
    }

    public void createInventoryAdapter(String name) {
        inventoryAdapter = factory.createInventoryAdapter();
    }

    public void updateInventory() {
        inventoryAdapter.updateInventoryQuantity();
    }
}
