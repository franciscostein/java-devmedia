package Lectures.lecture5.part3;

public class ClientWithoutFacade {

    public static void main(String[] args) {
        Database database = new Database();

        Customer customer = new Customer(123, "Demeter");
        database.addCustomer(customer);
        ShoppingCart cart = new ShoppingCart();
        customer.setCart(cart);

        Product product = database.selectProduct(18);
        Product product1 = database.selectProduct(25);
        customer.getCart().addProduct(product);
        customer.getCart().addProduct(product1);

        double amount = customer.getCart().getAmount();
        database.processPayment(customer, amount);
    }
}
