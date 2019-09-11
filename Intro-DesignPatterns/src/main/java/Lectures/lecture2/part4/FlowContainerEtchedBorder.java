package Lectures.lecture2.part4;

import java.util.ArrayList;
import java.util.List;

public class FlowContainerEtchedBorder extends Container {

    private List<Component> elements;

    public FlowContainerEtchedBorder() {
        elements = new ArrayList<>();
    }

    @Override
    public void addComponent(Component component) {
        elements.add(component);
    }

    @Override
    public void removeComponent(Component component) {
        elements.remove(component);
    }

    @Override
    public void doLayout() {
        System.out.println("The container being used is FlowContainer");
        System.out.println("This cointainer also contains an Etched Border around it");
        System.out.println("That's the elements in this container:");
        System.out.println(elements);
        System.out.println("Use of inheritance to close the container");
        this.dispose();
        System.out.println("");
    }
}
