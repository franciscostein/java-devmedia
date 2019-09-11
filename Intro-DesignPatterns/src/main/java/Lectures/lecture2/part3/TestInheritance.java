package Lectures.lecture2.part3;

public class TestInheritance {

    public static void main(String[] args) {
        //Creating GridContainer and adding elements to it
        Container container = new GridContainer(2, 2);

        Component button = new Component("Button");
        Component textArea = new Component("TextArea");
        Component textField = new Component("TextField");
        Component checkBox = new Component("CheckBox");

        container.addComponent(button);
        container.addComponent(textArea);
        container.addComponent(textField);
        container.addComponent(checkBox);

        container.doLayout();

        //Creating FlowContainer and adding elements to it
        Container container1 = new FlowContainer();

        Component radioButton = new Component("RadioButton");

        container1.addComponent(button);
        container1.addComponent(textArea);
        container1.addComponent(textField);
        container1.addComponent(checkBox);
        container1.addComponent(radioButton);

        container1.doLayout();

        System.out.println("Below the new requirements of the client\n");

        //Creating FlowContainer with EtchedBorder and adding elements to it
        Container container2 = new FlowContainerEtchedBorder();

        Component label = new Component("Label");

        container2.addComponent(button);
        container2.addComponent(textArea);
        container2.addComponent(textField);
        container2.addComponent(checkBox);
        container2.addComponent(label);

        container2.doLayout();

        //Creating FlowContainer with SolidBorder and adding elements to it
        Container container3 = new FlowContainerSolidBorder();

        Component table = new Component("Table");

        container3.addComponent(button);
        container3.addComponent(textArea);
        container3.addComponent(textField);
        container3.addComponent(checkBox);
        container3.addComponent(table);

        container3.doLayout();
    }
}
