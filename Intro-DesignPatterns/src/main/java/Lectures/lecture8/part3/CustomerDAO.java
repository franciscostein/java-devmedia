package Lectures.lecture8.part3;

import java.util.List;

public interface CustomerDAO {

    void persist(Customer customer);

    void update(Customer customer);

    void remove(Customer customer);

    List<Customer> findAll(String name);

    List<Customer> findAll();
}
