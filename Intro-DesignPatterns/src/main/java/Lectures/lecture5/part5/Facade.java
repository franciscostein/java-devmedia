package Lectures.lecture5.part5;

public class Facade {

    private Database database;
    private Customer customer;
    private Product product;

    public Facade() {
        database = new Database();
    }

    public void register(String nome, int id) {
        customer = new Customer(id, nome);
        database.addCustomer(customer);
    }

    public void shopping(int productId, int customerId) {
        customer = database.selectCustomer(customerId);
        product = database.selectProduct(productId);
        customer.getCart().addProduct(product);
    }

    public void finishShopping(int customerId) {
        customer = database.selectCustomer(customerId);
        double amount = customer.calculateValue();  //Principio do conhecimento minimo
        database.processPayment(customer, amount);
    }
}
