package Lectures.lecture8.part11;

public abstract class Factory {

    public abstract CustomerDAO createCustomerDAO();

    public abstract ProductDAO createProductDAO();
}
