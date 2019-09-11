package Lectures.lecture2.part4;

import java.util.Arrays;

public class GridContainer extends Container {

    private Component[][] elementArray;

    private int lineCounter;
    private int columnCounter;
    private int width;
    private int height;

    public GridContainer(int width, int height) {
        this.width = width;
        this.height = height;
        elementArray = new Component[width][height];
    }

    @Override
    public void addComponent(Component component) {
        if (lineCounter == height && columnCounter == width) {  //to na última linha
            System.out.println("It's impossible to add a new element");
        } else {
            elementArray[lineCounter][columnCounter] = component;
            columnCounter++;
            if (columnCounter == width) {
                lineCounter++;
                if (lineCounter < height) {
                    columnCounter = 0;
                }
            }
        }
    }

    @Override
    public void removeComponent(Component component) {
        for (int i = 0; i < elementArray.length; i++) {
            for (int j = 0; j < elementArray[i].length; j++) {
                if (elementArray[i][j] == component) {
                    elementArray[i][j] = null;
                }
            }
        }
    }

    @Override
    public void doLayout() {
        System.out.println("The container being used is a GridContainer");
        System.out.println("That's the elements in this container:");
        System.out.println(Arrays.deepToString(elementArray));
        System.out.println("Using inheritance to close the cotainer");
        this.dispose();
        System.out.println("");
    }
}
