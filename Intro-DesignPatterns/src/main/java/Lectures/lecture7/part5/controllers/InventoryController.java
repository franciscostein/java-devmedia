package Lectures.lecture7.part5.controllers;

import Lectures.lecture7.part5.adapters.IInventoryAdapter;
import Lectures.lecture7.part5.adapters.InventoryAdapterIBM;
import Lectures.lecture7.part5.adapters.InventoryAdapterItautec;

public class InventoryController {

    private IInventoryAdapter inventoryAdapter;

    public InventoryController() {
        System.out.println("Inventory Controller created\n");
    }

    public void createInvetoryAdapter(String name) {
        if (name.equals("IBM")) {
            inventoryAdapter = new InventoryAdapterIBM();
        } else if (name.equals("Itautec")) {
            inventoryAdapter = new InventoryAdapterItautec();
        }
    }

    public void updateInventory() {
        inventoryAdapter.updateInventoryQuantity();
    }
}
