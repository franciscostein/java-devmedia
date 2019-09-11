package Lectures.lecture8.part6;

import java.util.List;

public interface CustomerDAO {

    void persist(Lectures.lecture8.part6.Customer customer);

    void update(Lectures.lecture8.part6.Customer customer);

    void remove(Lectures.lecture8.part6.Customer customer);

    List<Lectures.lecture8.part6.Customer> findAll(String name);

    List<Customer> findAll();
}
