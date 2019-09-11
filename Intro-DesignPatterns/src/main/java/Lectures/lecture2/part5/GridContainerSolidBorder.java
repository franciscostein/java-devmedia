package Lectures.lecture2.part5;

import java.util.Arrays;

public class GridContainerSolidBorder extends GridContainer {

    public GridContainerSolidBorder(int width, int height) {
        super(width, height);
        elementArray = new Component[width][height];
    }

    @Override
    public void doLayout() {
        System.out.println("The container being used is a GridContainer");
        System.out.println("The container also contains a Solid Border around it");
        System.out.println("That's the elements in this container:");
        System.out.println(Arrays.deepToString(elementArray));
        System.out.println("Using inheritance to close the cotainer");
        this.dispose();
        System.out.println("");
    }
}
