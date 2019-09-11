package Lectures.lecture8.part12.integration;

import Lectures.lecture8.part12.entity.Customer;
import com.mysql.jdbc.PreparedStatement;

import java.sql.ResultSet;
import java.util.List;

public class CustomerDaoJDBC implements CustomerDAO {

    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    public static final String DATABASE_URL = "jdbc:mysql://localhost:3306/design_patterns";
    public static final String USER = "root";
    public static final String PASSWORD = "masterkey";
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;

    @Override
    public void persist(Customer customer) {

    }

    @Override
    public void update(Customer customer) {

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
}
