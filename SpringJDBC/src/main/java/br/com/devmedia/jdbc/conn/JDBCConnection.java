package br.com.devmedia.jdbc.conn;

import java.sql.*;
import java.util.ResourceBundle;

public class JDBCConnection {

    private static ResourceBundle resource = ResourceBundle.getBundle("jdbc");

    private static final String JDBC_URL = resource.getString("jdbc.url");
    private static final String JDBC_USER = resource.getString("jdbc.username");
    private static final String JDBC_PASS = resource.getString("jdbc.password");
    private static final String JDBC_DRIVER = resource.getString("jdbc.driver");

    public static Connection getConnection() {

        Connection connection = null;

        try {

            Class.forName(JDBC_DRIVER);

            connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);

        } catch (ClassNotFoundException e) {

            e.printStackTrace();

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return connection;
    }

    public static void close(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {

        try {

            if (connection != null) {

                connection.close();
            }

            if (preparedStatement != null) {

                preparedStatement.close();
            }

            if (resultSet != null) {

                resultSet.close();
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;

        try {

            preparedStatement = connection.prepareStatement("SELECT * FROM PESSOAS");
            preparedStatement.execute();

            System.out.print("Conexão OK");

        } catch (SQLException e) {

            e.printStackTrace();

        } finally {

            close(connection, preparedStatement, null);
        }
    }
}
