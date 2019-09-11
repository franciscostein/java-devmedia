package Lectures.lecture9.part4;

public enum MovieType {

    NORMAL(2), RELEASES(3), CHILDREN(1.5);

    private double price;

    MovieType(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}
