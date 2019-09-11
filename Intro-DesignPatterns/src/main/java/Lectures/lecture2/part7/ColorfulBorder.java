package Lectures.lecture2.part7;

public class ColorfulBorder extends Border {

    public ColorfulBorder(Container container) {
        super(container);
    }

    @Override
    public void generateBorder() {
        getContainer().doLayout();
        System.out.println("Colorful Border");
        getContainer().dispose();
    }
}
