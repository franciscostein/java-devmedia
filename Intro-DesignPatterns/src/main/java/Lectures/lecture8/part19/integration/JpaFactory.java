package Lectures.lecture8.part19.integration;

public class JpaFactory extends Factory {

    @Override
    public CustomerDAO createCustomerDAO() {
        return new CustomerDaoJPA();
    }

    @Override
    public ProductDAO createProductDAO() {
        return new ProductDaoJPA();
    }
}
