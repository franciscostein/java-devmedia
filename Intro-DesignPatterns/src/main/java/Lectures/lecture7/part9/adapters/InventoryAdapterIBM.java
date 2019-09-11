package Lectures.lecture7.part9.adapters;

import ConcreteFactory.InventorySystem.InventorySystem;

public class InventoryAdapterIBM extends InventoryAdapter {

    public InventoryAdapterIBM() {
        inventorySystem = new InventorySystem("IBM");
    }
}
