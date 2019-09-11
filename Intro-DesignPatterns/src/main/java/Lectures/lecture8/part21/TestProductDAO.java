package Lectures.lecture8.part21;

import Lectures.lecture8.part21.integration.Factory;
import Lectures.lecture8.part21.integration.FactoryChoice;
import Lectures.lecture8.part21.integration.ProductDAO;

public class TestProductDAO {

    public static void main(String[] args) {
        Factory factory = Factory.createFactory(FactoryChoice.JPA);
        ProductDAO dao = factory.createProductDAO();
    }
}
