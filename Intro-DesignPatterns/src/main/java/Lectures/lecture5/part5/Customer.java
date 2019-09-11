package Lectures.lecture5.part5;

public class Customer {

    private int id;
    private String name;
    private ShoppingCart cart;

    public Customer(int id, String name) {
        this.id = id;
        this.name = name;
        this.cart = new ShoppingCart();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cart=" + cart +
                '}';
    }

    public double calculateValue() {
        return cart.getAmount();
    }
}
