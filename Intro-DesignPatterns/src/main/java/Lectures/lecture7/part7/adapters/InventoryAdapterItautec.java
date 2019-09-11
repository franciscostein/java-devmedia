package Lectures.lecture7.part7.adapters;

import ConcreteFactory.InventorySystem.InventorySystem;

public class InventoryAdapterItautec implements IInventoryAdapter {

    private InventorySystem inventorySystem;

    public InventoryAdapterItautec() {
        inventorySystem = new InventorySystem("Itautec");
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
