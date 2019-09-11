package Lectures.lecture8.part21.integration;

import Lectures.lecture8.part21.entity.Product;

import java.util.List;

public interface ProductDAO {

    void persist(Product product);

    void update(Product product);

    void remove(Product product);

    List<Product> findAll(String name);

    List<Product> findAll();
}
