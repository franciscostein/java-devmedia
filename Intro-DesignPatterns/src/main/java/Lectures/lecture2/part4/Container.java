package Lectures.lecture2.part4;

public abstract class Container {

    public abstract void addComponent(Component component);

    public abstract void removeComponent(Component component);

    public abstract void doLayout();

    public void dispose() {
        System.out.println("Closing container");
    }
}
