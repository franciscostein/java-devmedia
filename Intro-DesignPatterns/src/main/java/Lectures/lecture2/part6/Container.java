package Lectures.lecture2.part6;

public abstract class Container {

    protected Border border;

    public Container() {
    }

    public Container(Border border) {
        this.border = border;
    }

    public abstract void addComponent(Component component);

    public abstract void removeComponent(Component component);

    public abstract void doLayout();

    public void dispose() {
        System.out.println("Closing container");
    }
}
