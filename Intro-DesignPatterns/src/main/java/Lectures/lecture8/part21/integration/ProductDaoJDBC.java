package Lectures.lecture8.part21.integration;

import Lectures.lecture8.part21.entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoJDBC implements ProductDAO {

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
    public void persist(Product product) {
        try {
            preparedStatement = connection.prepareStatement("insert into PRODUCT(name, price) values(?, ?)");
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Product product) {
        try {
            preparedStatement = connection.prepareStatement("update PRODUCT set name = ?, price = ? where id = ?");
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Product product) {
        try {
            preparedStatement = connection.prepareStatement("delete from PRODUCT where id = ?");
            preparedStatement.setInt(1, product.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> findAll(String name) {
        List<Product> products = new ArrayList<>();
        Product product = null;
        try {
            preparedStatement = connection.prepareStatement("select * from PRODUCT where name like ?");
            preparedStatement.setString(1, "%" + name + "%");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                product = new Product();
                product.setId(resultSet.getInt("ID"));
                product.setName(resultSet.getString("NAME"));
                product.setPrice(resultSet.getDouble("PRICE"));

                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        Product product = null;
        try {
            preparedStatement = connection.prepareStatement("select * from PRODUCT");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                product = new Product();
                product.setId(resultSet.getInt("ID"));
                product.setName(resultSet.getString("NAME"));
                product.setPrice(resultSet.getDouble("PRICE"));

                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}
