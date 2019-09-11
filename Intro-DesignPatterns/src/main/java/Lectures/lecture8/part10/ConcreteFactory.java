package Lectures.lecture8.part10;

public class ConcreteFactory {

    public static CustomerDAO createCustomerDAO() {
        return new CustomerDaoJPA();
    }

    public static ProductDAO createProductDAO() {
        return new ProductDaoJPA();
    }
}
