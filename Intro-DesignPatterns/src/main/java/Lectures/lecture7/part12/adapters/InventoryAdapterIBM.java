package Lectures.lecture7.part12.adapters;

import ConcreteFactory.InventorySystem.InventorySystem;

public class InventoryAdapterIBM extends InventoryAdapter {

    public InventoryAdapterIBM() {
        inventorySystem = new InventorySystem("IBM");
    }
}
