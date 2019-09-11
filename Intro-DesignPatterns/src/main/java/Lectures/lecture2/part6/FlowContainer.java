package Lectures.lecture2.part6;

import java.util.ArrayList;
import java.util.List;

public class FlowContainer extends Container {

    protected List<Component> elements;

    public FlowContainer() {
        elements = new ArrayList<>();
    }

    public FlowContainer(Border border) {
        super(border);
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
        System.out.println("The container being used is the FlowContainer");
        if (border != null) {
            border.generateBorder();
        }
        System.out.println("Elements in this container:");
        System.out.println(elements);
        System.out.println("Using of inheritance to close container");
        this.dispose();
        System.out.println("");
    }
}
