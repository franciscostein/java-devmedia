package Lectures.lecture7.part8.adapters;

import ConcreteFactory.InventorySystem.InventorySystem;

public class InventoryAdapterSAP extends InventoryAdapter {

    public InventoryAdapterSAP() {
        inventorySystem = new InventorySystem("SAP");
    }
}
