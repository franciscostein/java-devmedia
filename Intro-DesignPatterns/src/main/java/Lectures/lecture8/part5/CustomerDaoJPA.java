package Lectures.lecture8.part5;

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
    public void persist(Lectures.lecture8.part5.Customer customer) {
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
    public void update(Lectures.lecture8.part5.Customer customer) {
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
    public void remove(Lectures.lecture8.part5.Customer customer) {
        Lectures.lecture8.part5.Customer customerReturned = null;
        try {
            open();
            entityManager.getTransaction().begin();
            customerReturned = entityManager.merge(customer);
            entityManager.remove(customerReturned);
            entityManager.getTransaction().commit();
        } finally {
            close();
        }
    }

    @Override
    public List<Lectures.lecture8.part5.Customer> findAll(String name) {
        List<Lectures.lecture8.part5.Customer> customers = null;
        try {
            open();
            customers = entityManager.createQuery("SELECT c FROM Customer c WHERE c.name = :name")
                    .setParameter("name", name)
                    .getResultList();
        } finally {
            close();
        }
        return customers;
    }

    @Override
    public List<Lectures.lecture8.part5.Customer> findAll() {
        List<Lectures.lecture8.part5.Customer> customers = null;
        try {
            open();
            customers = entityManager.createQuery("SELECT c FROM Customer c")
                    .getResultList();
        } finally {
            close();
        }
        return customers;
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
