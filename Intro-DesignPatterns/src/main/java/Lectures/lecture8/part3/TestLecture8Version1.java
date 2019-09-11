package Lectures.lecture8.part3;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TestLecture8Version1 {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("design_pattern");
        EntityManager entityManager = factory.createEntityManager();

        Customer customer = new Customer();
        customer.setName("Leandro");
        customer.setCity("Campinas");

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(customer);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
            factory.close();
        }
    }
}
