package Lectures.lecture7.part7.controllers;

import Lectures.lecture7.part7.adapters.IInventoryAdapter;
import Lectures.lecture7.part7.adapters.InventoryAdapterIBM;
import Lectures.lecture7.part7.adapters.InventoryAdapterItautec;
import Lectures.lecture7.part7.adapters.InventoryAdapterSAP;

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
        } else if (name.equals("SAP")) {
            inventoryAdapter = new InventoryAdapterSAP();
        }
    }

    public void updateInventory() {
        inventoryAdapter.updateInventoryQuantity();
    }
}
