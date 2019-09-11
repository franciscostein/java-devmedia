package Lectures.lecture7.part6.controllers;

import Lectures.lecture7.part6.adapters.IInventoryAdapter;
import Lectures.lecture7.part6.adapters.InventoryAdapterIBM;
import Lectures.lecture7.part6.adapters.InventoryAdapterItautec;
import Lectures.lecture7.part6.adapters.InventoryAdapterSAP;

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
