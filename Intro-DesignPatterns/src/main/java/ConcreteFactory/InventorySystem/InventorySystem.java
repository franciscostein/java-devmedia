package ConcreteFactory.InventorySystem;

public class InventorySystem {

    private String name;

    public InventorySystem(String name) {
        this.name = name;
    }

    public void decrease() {
        System.out.println("New item quantity registered at " + name + " on the Inventoy System\n");
    }

    public void update() {
        System.out.println("New item updated at " + name + " on the Inventoy System\n");
    }
}
