package Lectures.lecture8.part8;

import java.util.ArrayList;
import java.util.List;

public class TestLecture8Version4 {

    private ProductDAO dao = new ProductDaoJPA();
    private static List<Product> products = new ArrayList<>();

    public TestLecture8Version4() {
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
        TestLecture8Version4 test = new TestLecture8Version4();
        test.persist();
    }

    private void persist() {
        for (Product product : products) {
            dao.persist(product);
        }
    }
}