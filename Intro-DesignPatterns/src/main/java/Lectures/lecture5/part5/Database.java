package Lectures.lecture5.part5;

import java.util.ArrayList;
import java.util.List;

public class Database {

    private List<Customer> customers;
    private List<Product> products;

    public Database() {
        customers = new ArrayList<>();
        products = new ArrayList<>();
        products.add(new Product(18, "Shampoo", 5.40));
        products.add(new Product(25, "Ashtray", 2.30));
    }

    public Customer selectCustomer(int id) {
        for (Customer customer : customers) {
            if (customer.getId() == id){
                return customer;
            }
        }
        return null;
    }

    public Product selectProduct(int id) {
        for (Product product : products) {
            if (product.getId() == id){
                return product;
            }
        }
        return null;
    }

    public void processPayment(Customer customer, double amount) {
        System.out.println("Processing payment of customer: " + customer);
        System.out.println("Successfull Payment! Total: $" + amount);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void addProduct(Product product) {
        products.add(product);
    }
}
