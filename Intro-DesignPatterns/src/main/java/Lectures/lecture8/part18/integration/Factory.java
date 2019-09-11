package Lectures.lecture8.part18.integration;

public abstract class Factory {

    public abstract CustomerDAO createCustomerDAO();

    public abstract ProductDAO createProductDAO();
}
