package Lectures.lecture8.part16.integration;

import Lectures.lecture8.part16.entity.Customer;

import java.sql.*;
import java.util.List;

public class CustomerDaoJDBC implements CustomerDAO {

    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    public static final String DATABASE_URL = "jdbc:mysql://localhost:3306/design_patterns";
    public static final String USER = "root";
    public static final String PASSWORD = "";
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
        try {   //A transação tem que ser atomica, as duas inserções tem que ter sucesso ou nenhuma
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement("insert into CREDENTIAL(login, password) values(?, ?)");
            preparedStatement.setString(1, customer.getCredential().getLogin());
            preparedStatement.setString(2, customer.getCredential().getPassword());
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement("select id from CREDENTIAL order by id desc limit 1");
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int id = resultSet.getInt("id");

            preparedStatement = connection.prepareStatement("insert into CUSTOMER(name, city, credential_id) values (?, ?, ?)");
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getCity());
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(Customer customer) {
        try {
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement("update CUSTOMER set name = ?, city = ? where id = ?");
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getCity());
            preparedStatement.setInt(3, customer.getId());
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement("update CREDENTIAL set login = ?, password = ? where id = ?");
            preparedStatement.setString(1, customer.getCredential().getLogin());
            preparedStatement.setString(2, customer.getCredential().getPassword());
            preparedStatement.setInt(3, customer.getCredential().getId());
            preparedStatement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
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
