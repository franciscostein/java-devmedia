package Lectures.lecture2.part7;

/*
*       Favorecendo composição sobre herança, com inversão de dependecia
* */
public class TestInheritance {

    public static void main(String[] args) {
        //Creating GridContainer without border and adding elements to it
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

        System.out.println("");

        //Creating FlowContainer without border and adding elements to it
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
        Container container2 = new FlowContainer();
        Border border = new EtchedBorder(container2);

        Component label = new Component("Label");

        container2.addComponent(button);
        container2.addComponent(textArea);
        container2.addComponent(textField);
        container2.addComponent(checkBox);
        container2.addComponent(label);

        border.generateBorder();

        //Creating FlowContainer with SolidBorder and adding elements to it
        Container container3 = new FlowContainer();
        Border border1 = new SolidBorder(container3);

        Component table = new Component("Table");

        container3.addComponent(button);
        container3.addComponent(textArea);
        container3.addComponent(textField);
        container3.addComponent(checkBox);
        container3.addComponent(table);

        border1.generateBorder();

        //Creating GridContainer with EtchedBorder and adding elements to it
        Container container4 = new GridContainer(2, 2);
        Border border2 = new EtchedBorder(container4);

        Component list = new Component("List");

        container4.addComponent(button);
        container4.addComponent(textArea);
        container4.addComponent(textField);
        container4.addComponent(checkBox);
        container4.addComponent(list);

        border2.generateBorder();

        //Creating GridContainer with SolidBorder and adding elements to it
        Container container5 = new GridContainer(2, 2);
        Border border3 = new SolidBorder(container5);

        Component menuList = new Component("MenuList");

        container5.addComponent(button);
        container5.addComponent(textArea);
        container5.addComponent(textField);
        container5.addComponent(checkBox);
        container5.addComponent(menuList);

        border3.generateBorder();

        System.out.println("Adding a new border to the requirements\n");

        Container container6 = new FlowContainer();
        Border border4 = new ColorfulBorder(container6);

        container6.addComponent(button);
        container6.addComponent(textArea);
        container6.addComponent(textField);
        container6.addComponent(checkBox);
        container6.addComponent(menuList);

        border4.generateBorder();

        Container container7 = new GridContainer(2, 2);
        Border border5 = new ColorfulBorder(container7);

        container7.addComponent(button);
        container7.addComponent(textArea);
        container7.addComponent(textField);
        container7.addComponent(checkBox);
        container7.addComponent(menuList);

        border5.generateBorder();
    }
}
