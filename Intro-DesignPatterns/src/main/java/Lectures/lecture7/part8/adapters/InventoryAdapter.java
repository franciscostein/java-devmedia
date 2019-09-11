package Lectures.lecture7.part8.adapters;

import ConcreteFactory.InventorySystem.InventorySystem;

public abstract class InventoryAdapter {

    protected InventorySystem inventorySystem;

    public void decreaseItemQuantity() {
        inventorySystem.decrease();
    }

    public void updateInventoryQuantity() {
        inventorySystem.update();
    }
}
