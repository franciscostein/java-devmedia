package Lectures.lecture8.part18.integration;

import Lectures.lecture8.part18.entity.Credential;
import Lectures.lecture8.part18.entity.Customer;

import java.sql.*;
import java.util.ArrayList;
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
        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement("delete from CUSTOMER where id = ?");
            preparedStatement.setInt(1, customer.getId());
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement("delete from CREDENTIAL where id = ?");
            preparedStatement.setInt(1, customer.getCredential().getId());
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
    public List<Customer> findAll(String name) {
        List<Customer> customers = new ArrayList<>();
        Customer customer = null;
        Credential credential = null;
        final String query = "select CR.ID CR_ID, CR.NAME, CR.CITY, CR.CREDENTIAL_ID, CL.ID CL_ID, " +
                "CL.LOGIN, CL.PASSWORD from CUSTOMER CR, CREDENTIAL CL where CR.NAME like ? AND CR.CREDENTIAL_ID = CL.ID";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                customer = new Customer();
                customer.setId(resultSet.getInt("CR_ID"));
                customer.setName(resultSet.getString("NAME"));
                customer.setCity(resultSet.getString("CITY"));

                credential = new Credential();
                credential.setId(resultSet.getInt("CL_ID"));
                credential.setLogin(resultSet.getString("LOGIN"));
                credential.setPassword(resultSet.getString("PASSWORD"));

                customer.setCredential(credential);
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();
        Customer customer = null;
        Credential credential = null;
        final String query = "select CR.ID CR_ID, CR.NAME, CR.CITY, CR.CREDENTIAL_ID, CL.ID CL_ID, " +
                "CL.LOGIN, CL.PASSWORD from CUSTOMER CR, CREDENTIAL CL where CR.CREDENTIAL_ID = CL.ID";
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                customer = new Customer();
                customer.setId(resultSet.getInt("CR_ID"));
                customer.setName(resultSet.getString("NAME"));
                customer.setCity(resultSet.getString("CITY"));

                credential = new Credential();
                credential.setId(resultSet.getInt("CL_ID"));
                credential.setLogin(resultSet.getString("LOGIN"));
                credential.setPassword(resultSet.getString("PASSWORD"));

                customer.setCredential(credential);
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }
}
