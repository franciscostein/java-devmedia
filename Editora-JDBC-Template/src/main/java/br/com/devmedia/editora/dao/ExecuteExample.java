package br.com.devmedia.editora.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ExecuteExample {

    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS LIVROS("
            + "ID_LIVRO INT(11) PRIMARY KEY, "
            + "TITULO VARCHAR(45) NOT NULL, "
            + "AUTOR VARCHAR(45) NOT NULL)";
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ExecuteExample(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
        this.jdbcTemplate.execute(CREATE_TABLE);
    }

    public void insert() {

        String sql = "INSERT INTO LIVROS (ID_LIVRO, TITULO, AUTOR) VALUES (3, 'Inferno', 'Dan Brown')";

        jdbcTemplate.execute(sql);
    }

    public void delete() {

        String sql = "DELETE FROM LIVROS WHERE ID_LIVRO = 3";

        jdbcTemplate.execute(sql);
    }

    public void find() {

        String sql =  "SELECT * FROM LIVROS WHERE ID_LIVRO = 1";

        List<Object> list = jdbcTemplate.execute(sql, new PreparedStatementCallback<List<Object>>() {
            @Override
            public List<Object> doInPreparedStatement(PreparedStatement preparedStatement) throws SQLException, DataAccessException {

                List<Object> list = new ArrayList<>();

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {

                    list.add(resultSet.getInt(1));
                    list.add(resultSet.getString(2));
                    list.add(resultSet.getString(3));
                }

                return list;
            }
        });

        System.out.println(list);
    }

    public void dropTable() {

        String sql = "DROP TABLE LIVROS";

        jdbcTemplate.execute(sql);
    }
}
