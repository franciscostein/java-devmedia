package Lectures.lecture8.part13.integration;

import Lectures.lecture8.part13.entity.Customer;

import java.sql.*;
import java.util.List;

public class CustomerDaoJDBC implements CustomerDAO {

    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    public static final String DATABASE_URL = "jdbc:mysql://localhost:3306/design_patterns";
    public static final String USER = "root";
    public static final String PASSWORD = "masterkey";
    private static Connection connection;
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;

    //Inicia antes da instanciação da classe
    static {
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void persist(Customer customer) {
        try {
            preparedStatement = connection.prepareStatement("insert into CREDENTIAL (login, password) values(?, ?)");
            preparedStatement.setString(1, customer.getCredential().getLogin());
            preparedStatement.setString(2, customer.getCredential().getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
