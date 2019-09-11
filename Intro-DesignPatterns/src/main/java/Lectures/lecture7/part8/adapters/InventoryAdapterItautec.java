package Lectures.lecture7.part8.adapters;

import ConcreteFactory.InventorySystem.InventorySystem;

public class InventoryAdapterItautec extends InventoryAdapter {

    public InventoryAdapterItautec() {
        inventorySystem = new InventorySystem("Itautec");
    }
}
