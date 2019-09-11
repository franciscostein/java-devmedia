package Lectures.lecture2.part7;

public abstract class Border {

    private Container container;

    public Border(Container container) {
        this.container = container;
    }

    public Container getContainer() {
        return container;
    }

    public abstract void generateBorder();
}
