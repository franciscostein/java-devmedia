package Lectures.lecture2.part5;

public class FlowContainerEtchedBorder extends FlowContainer {

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
