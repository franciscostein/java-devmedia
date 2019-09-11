package Lectures.lecture8.part17.integration;

import Lectures.lecture8.part17.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ProductDaoJPA implements ProductDAO {
    
    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("design_pattern");

    private EntityManager entityManager;

    public ProductDaoJPA() {
        entityManager = factory.createEntityManager();
    }

    @Override
    public void persist(Product product) {
        try {
            open();
            entityManager.getTransaction().begin();
            entityManager.persist(product);
            entityManager.getTransaction().commit();
        } finally {
            close();
        }
    }

    @Override
    public void update(Product product) {
        try {
            open();
            entityManager.getTransaction().begin();
            entityManager.merge(product);
            entityManager.getTransaction().commit();
        } finally {
            close();
        }
    }

    @Override
    public void remove(Product product) {
        Product productReturned = null;
        try {
            open();
            entityManager.getTransaction().begin();
            productReturned = entityManager.merge(product);
            entityManager.remove(productReturned);
            entityManager.getTransaction().commit();
        } finally {
            close();
        }
    }

    @Override
    public List<Product> findAll(String name) {
        List<Product> products = null;
        try {
            open();
            products = entityManager.createQuery("SELECT p FROM Product p WHERE c.name = :name")
                    .setParameter("name", name)
                    .getResultList();
        } finally {
            close();
        }
        return products;
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = null;
        try {
            open();
            products = entityManager.createQuery("SELECT p FROM Product p")
                    .getResultList();
        } finally {
            close();
        }
        return products;
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
