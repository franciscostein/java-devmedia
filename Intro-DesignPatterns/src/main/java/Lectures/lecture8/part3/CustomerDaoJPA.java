package Lectures.lecture8.part3;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class CustomerDaoJPA implements CustomerDAO {

    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("design_pattern");

    private EntityManager entityManager;

    public CustomerDaoJPA() {
        entityManager = factory.createEntityManager();
    }

    @Override
    public void persist(Customer customer) {
        try {
            open();
            entityManager.getTransaction().begin();
            entityManager.persist(customer);
            entityManager.getTransaction().commit();
        } finally {
            close();
        }
    }

    @Override
    public void update(Customer customer) {
        try {
            open();
            entityManager.getTransaction().begin();
            entityManager.merge(customer);
            entityManager.getTransaction().commit();
        } finally {
            close();
        }
    }

    @Override
    public void remove(Customer customer) {

    }

    @Override
    public List<Customer> findAll(String name) {
        return null;
    }

    @Override
    public List<Customer> findAll() {
        return null;
    }

    private void open() {
        if (!factory.isOpen()) {
            factory = Persistence.createEntityManagerFactory("design_pattern");
        }
        if (!entityManager.isOpen()) {
            entityManager = factory.createEntityManager();
        }
    }

    private void close() {
        if (entityManager.isOpen()) {
            entityManager.close();
        }
        if (factory.isOpen()) {
            factory.close();
        }
    }
}
