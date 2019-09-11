package Lectures.lecture2.part7;

public class SolidBorder extends Border {

    public SolidBorder(Container container) {
        super(container);
    }

    @Override
    public void generateBorder() {
        getContainer().doLayout();
        System.out.println("Solid Border");
        getContainer().dispose();
    }
}
