package br.com.devmedia.jdbc.dao;

import br.com.devmedia.jdbc.conn.JDBCConnection;
import br.com.devmedia.jdbc.entity.Pessoa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PessoaDAO {

    private static final String SQL_INSERT = "INSERT INTO pessoas (NOME, PROFISSAO, DATA_NASCIMENTO) values (?, ?, ?)";

    private static final String SQL_FIND_BY_ID = "SELECT * FROM pessoas WHERE ID_PESSOA = ?";

    private static final String SQL_FIND_BY_NOME = "SELECT * FROM pessoas WHERE NOME like ?";

    private static final String SQL_FIND_ALL = "SELECT * FROM pessoas";

    private static final String SQL_FIND_BY_PROFISSAO = "SELECT * FROM pessoas WHERE PROFISSAO LIKE = ?";

    private static final String SQL_UPDATE = "UPDATE pessoas SET nome = ?, profissao = ?, data_nascimento = ? WHERE id_pessoa = ?";

    private static final String SQL_DELETE = "DELETE FROM pessoas WHERE ID_PESSOA = ?";

    public int save(Pessoa pessoa) {

        Connection connection = JDBCConnection.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            preparedStatement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, pessoa.getNome());
            preparedStatement.setString(2, pessoa.getProfissao());
            preparedStatement.setDate(3, new Date(pessoa.getDtNascimento().getTime()));

            preparedStatement.execute();

            resultSet = preparedStatement.getGeneratedKeys();

            while (resultSet.next()) {

                return resultSet.getInt(1);
            }

        } catch (SQLException e) {

            e.printStackTrace();

        } finally {

            JDBCConnection.close(connection, preparedStatement, null);
        }

        return 0;
    }

    public int delete(Pessoa pessoa) {

        Connection connection = JDBCConnection.getConnection();
        PreparedStatement preparedStatement = null;

        try {

            preparedStatement = connection.prepareStatement(SQL_DELETE);

            preparedStatement.setInt(1, pessoa.getId());

            return preparedStatement.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();

        } finally {

            JDBCConnection.close(connection, preparedStatement, null);
        }

        return 0;
    }

    public int update(Pessoa pessoa) {

        Connection connection = JDBCConnection.getConnection();
        PreparedStatement preparedStatement = null;

        try {

            preparedStatement = connection.prepareStatement(SQL_UPDATE);

            preparedStatement.setString(1, pessoa.getNome());
            preparedStatement.setString(2, pessoa.getProfissao());
            preparedStatement.setDate(3, new Date(pessoa.getDtNascimento().getTime()));
            preparedStatement.setInt(4, pessoa.getId());

            return preparedStatement.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();

        } finally {

            JDBCConnection.close(connection, preparedStatement, null);
        }

        return 0;
    }

    public List<Pessoa> findAll() {

        Connection connection = JDBCConnection.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Pessoa> pessoas = new ArrayList<Pessoa>();

        try {

            preparedStatement = connection.prepareStatement(SQL_FIND_ALL);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Pessoa pessoa = new Pessoa();

                pessoa.setId(resultSet.getInt(1));
                pessoa.setNome(resultSet.getString(2));
                pessoa.setProfissao(resultSet.getString(3));
                pessoa.setDtNascimento(resultSet.getDate(4));

                pessoas.add(pessoa);
            }

        } catch (SQLException e) {

            e.printStackTrace();

        } finally {

            JDBCConnection.close(connection, preparedStatement, resultSet);
        }

        return pessoas;
    }

    public List<Pessoa> findByProfissao(String profissao) {

        Connection connection = JDBCConnection.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Pessoa> pessoas = new ArrayList<Pessoa>();

        try {

            preparedStatement = connection.prepareStatement(SQL_FIND_BY_PROFISSAO);

            preparedStatement.setString(1, profissao);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Pessoa pessoa = new Pessoa();

                pessoa.setId(resultSet.getInt(1));
                pessoa.setNome(resultSet.getString(2));
                pessoa.setProfissao(resultSet.getString(3));
                pessoa.setDtNascimento(resultSet.getDate(4));

                pessoas.add(pessoa);
            }

        } catch (SQLException e) {

            e.printStackTrace();

        } finally {

            JDBCConnection.close(connection, preparedStatement, resultSet);
        }

        return pessoas;
    }

    public Pessoa findById(int id) {

        Connection connection = JDBCConnection.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Pessoa pessoa = null;

        try {

            preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID);

            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                pessoa = new Pessoa();

                pessoa.setId(resultSet.getInt(1));
                pessoa.setNome(resultSet.getString(2));
                pessoa.setProfissao(resultSet.getString(3));
                pessoa.setDtNascimento(resultSet.getDate(4));
            }

        } catch (SQLException e) {

            e.printStackTrace();

        } finally {

            JDBCConnection.close(connection, preparedStatement, resultSet);
        }

        return pessoa;
    }

    public Pessoa findByNome(String nome) {

        Connection connection = JDBCConnection.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Pessoa pessoa = null;

        try {

            preparedStatement = connection.prepareStatement(SQL_FIND_BY_NOME);

            preparedStatement.setString(1, nome);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                pessoa = new Pessoa();

                pessoa.setId(resultSet.getInt(1));
                pessoa.setNome(resultSet.getString(2));
                pessoa.setProfissao(resultSet.getString(3));
                pessoa.setDtNascimento(resultSet.getDate(4));
            }

        } catch (SQLException e) {

            e.printStackTrace();

        } finally {

            JDBCConnection.close(connection, preparedStatement, resultSet);
        }

        return pessoa;
    }
}
