package Lectures.lecture7.part7.adapters;

import ConcreteFactory.InventorySystem.InventorySystem;

public class InventoryAdapterIBM implements IInventoryAdapter {

    private InventorySystem inventorySystem;

    public InventoryAdapterIBM() {
        inventorySystem = new InventorySystem("IBM");
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
