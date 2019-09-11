package Lectures.lecture5.part3;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private List<Product> products;

    public ShoppingCart() {
        products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public double getAmount() {
        double amount = 0.0;    //Varíaveis locais precisam ser instãnciadas
        for (Product product : products) {
            amount += product.getPrice();
        }
        return amount;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "products=" + products +
                '}';
    }
}
