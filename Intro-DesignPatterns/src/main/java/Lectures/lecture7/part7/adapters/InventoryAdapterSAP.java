package Lectures.lecture7.part7.adapters;

import ConcreteFactory.InventorySystem.InventorySystem;

public class InventoryAdapterSAP implements IInventoryAdapter {

    private InventorySystem inventorySystem;

    public InventoryAdapterSAP() {
        inventorySystem = new InventorySystem("SAP");
    }

    @Override
    public void decreaseItemQuantity() {
        inventorySystem.decrease();
    }

    @Override
    public void updateInventoryQuantity() {
        inventorySystem.update();
    }
}
