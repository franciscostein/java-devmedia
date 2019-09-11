package Lectures.lecture8.part21.integration;

public class JdbcFactory extends Factory {

    @Override
    public CustomerDAO createCustomerDAO() {
        return new CustomerDaoJDBC();
    }

    @Override
    public ProductDAO createProductDAO() {
        return new ProductDaoJDBC();
    }
}
