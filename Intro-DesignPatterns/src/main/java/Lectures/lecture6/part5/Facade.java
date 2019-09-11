package Lectures.lecture6.part5;

public class Facade {

    private Database database;
    private Customer customer;
    private ShoppingCart cart;
    private Product product;
    private static Facade instance = new Facade();

    private Facade() {
        database = new Database();
    }

    public static Facade getInstance() {
        return instance;
    }

    public void register(String nome, int id) {
        customer = new Customer(id, nome);
        cart = new ShoppingCart();
        customer.setCart(cart);
        database.addCustomer(customer);
    }

    public void shopping(int productId, int customerId) {
        customer = database.selectCustomer(customerId);
        product = database.selectProduct(productId);
        customer.getCart().addProduct(product);
    }

    public void finishShopping(int customerId) {
        customer = database.selectCustomer(customerId);
        double amount = customer.getCart().getAmount();
        database.processPayment(customer, amount);
    }
}
