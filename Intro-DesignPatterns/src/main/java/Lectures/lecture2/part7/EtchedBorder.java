package Lectures.lecture2.part7;

public class EtchedBorder extends Border {

    public EtchedBorder(Container container) {
        super(container);
    }

    @Override
    public void generateBorder() {
        getContainer().doLayout();
        System.out.println("Etched Border");
        getContainer().dispose();
    }
}
