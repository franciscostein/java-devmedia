package Lectures.lecture8.part17.integration;

import Lectures.lecture8.part17.entity.Customer;

import java.util.List;

public interface CustomerDAO {

    void persist(Customer customer);

    void update(Customer customer);

    void remove(Customer customer);

    List<Customer> findAll(String name);

    List<Customer> findAll();
}
