package Lectures.lecture8.part16.integration;

import Lectures.lecture8.part16.entity.Product;

import java.util.List;

public interface ProductDAO {

    void persist(Product product);

    void update(Product product);

    void remove(Product product);

    List<Product> findAll(String name);

    List<Product> findAll();
}
