package Lectures.lecture8.part17.integration;

import Lectures.lecture8.part17.entity.Customer;

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
        Customer customerReturned = null;
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
    public List<Customer> findAll(String name) {
        List<Customer> customers = null;
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
    public List<Customer> findAll() {
        List<Customer> customers = null;
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
