package Lectures.lecture2.part2;

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
    }
}
