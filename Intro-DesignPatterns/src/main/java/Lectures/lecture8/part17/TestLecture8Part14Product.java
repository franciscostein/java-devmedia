package Lectures.lecture8.part17;

import Lectures.lecture8.part17.entity.Product;
import Lectures.lecture8.part17.integration.Factory;
import Lectures.lecture8.part17.integration.JpaFactory;
import Lectures.lecture8.part17.integration.ProductDAO;

import java.util.ArrayList;
import java.util.List;

public class TestLecture8Part14Product {

    private Factory factory = new JpaFactory();
    private ProductDAO dao = factory.createProductDAO();
    private static List<Product> products = new ArrayList<>();

    public TestLecture8Part14Product() {
        Product product = new Product("Blue and white t-shirt", 19.98);
        Product product1 = new Product("Black Sabbath 13 CD", 39.90);
        Product product2 = new Product("Fender Stratoscaster guitar", 199.99);
        Product product3 = new Product("Coffee maker", 28.79);
        products.add(product);
        products.add(product1);
        products.add(product2);
        products.add(product3);
    }

    public static void main(String[] args) {
        TestLecture8Part14Product test = new TestLecture8Part14Product();
        test.persist();

        products = test.listProducts();
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase("Coffee maker")) {
                product.setName("Beautiful yellow box");
                product.setPrice(50);
                test.updateProduct(product);
            }
            if (product.getName().equalsIgnoreCase("Fender Stratoscaster guitar")) {
                test.removeProduct(product);
            }
        }
        products = test.listProducts();
        products.forEach(System.out::println);
    }

    private void removeProduct(Product product) {
        dao.remove(product);
    }

    private void updateProduct(Product product) {
        dao.update(product);
    }

    private List<Product> listProducts() {
        return dao.findAll();
    }

    private void persist() {
        for (Product product : products) {
            dao.persist(product);
        }
    }
}