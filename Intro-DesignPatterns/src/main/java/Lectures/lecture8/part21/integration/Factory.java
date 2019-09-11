package Lectures.lecture8.part21.integration;

public abstract class Factory {

    public abstract CustomerDAO createCustomerDAO();

    public abstract ProductDAO createProductDAO();

    public static Factory createFactory(FactoryChoice choice) {
        Factory factory = null;
        if (choice.equals(FactoryChoice.JPA)) {
            factory = new JpaFactory();
        } else {
            factory = new JdbcFactory();
        }
        return factory;
    }
}
