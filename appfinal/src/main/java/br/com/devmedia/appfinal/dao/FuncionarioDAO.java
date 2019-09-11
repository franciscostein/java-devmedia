package br.com.devmedia.appfinal.dao;

import br.com.devmedia.appfinal.entity.Funcionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class FuncionarioDAO extends GenericDAO<Funcionario> {

    private CargoDAO cargoDAO;

    private EnderecoDAO enderecoDAO;

    @Autowired
    public FuncionarioDAO(DataSource dataSource, CargoDAO cargoDAO, EnderecoDAO enderecoDAO) {

        super(dataSource, Funcionario.class);
        this.cargoDAO = cargoDAO;
        this.enderecoDAO = enderecoDAO;
    }

    @Override
    protected SqlParameterSource parameterSource(Funcionario funcionario) {

        MapSqlParameterSource source = new MapSqlParameterSource();

        source.addValue("nome", funcionario.getNome());
        source.addValue("salario", funcionario.getSalario());
        source.addValue("dataEntrada", Date.valueOf(funcionario.getDataEntrada()));
        source.addValue("idFuncionario", funcionario.getIdFuncionario());
        source.addValue("idCargo", funcionario.getCargo().getIdCargo());
        source.addValue("idEndereco", funcionario.getEndereco().getIdEndereco());

        if (funcionario.getDataSaida() != null) {

            source.addValue("dataSaida", Date.valueOf(funcionario.getDataSaida()));
        }

        return source;
    }

    @Override
    protected RowMapper<Funcionario> rowMapper() {

        return new RowMapper<Funcionario>() {
            @Override
            public Funcionario mapRow(ResultSet resultSet, int i) throws SQLException {

                Funcionario funcionario = new Funcionario();

                funcionario.setIdFuncionario(resultSet.getInt("ID_FUNCIONARIO"));
                funcionario.setNome(resultSet.getString("NOME"));
                funcionario.setSalario(resultSet.getDouble("SALARIO"));
                funcionario.setEndereco(enderecoDAO.findById(resultSet.getInt("ID_ENDERECO")));
                funcionario.setCargo(cargoDAO.findById(resultSet.getInt("ID_CARGO")));
                funcionario.setDataEntrada(resultSet.getDate("DATA_ENTRADA").toLocalDate());

                if (resultSet.getDate("DATA_SAIDA") != null) {

                    funcionario.setDataSaida(resultSet.getDate("DATA_SAIDA").toLocalDate());
                }

                return funcionario;
            }
        };
    }

    public Funcionario save(Funcionario funcionario) {

        Number key = super.save("FUNCIONARIOS", "ID_FUNCIONARIO", parameterSource(funcionario));

        funcionario.setIdFuncionario(key.intValue());

        return funcionario;
    }

    public int update(Funcionario funcionario) {

        String dataSaida = "";

        if (funcionario.getDataSaida() != null) {

            dataSaida = "data_saida = :dataSaida, ";
        }

        String sql = "UPDATE funcionarios SET " + dataSaida + "nome = :nome, salario = :salario, id_cargo = :idCargo, " +
                "id_endereco = :idEndereco, data_entrada = :dataEntrada " +
                "WHERE id_funcionario = :idFuncionario";

        return super.update(sql, parameterSource(funcionario));
    }

    public int delete(Integer id) {

        String sql = "DELETE FROM funcionarios WHERE id_funcionario = ?";

        return super.delete(sql, id);
    }

    public Funcionario findById(Integer id) {

        String sql = "SELECT * FROM funcionarios WHERE id_funcionario = ?";

        return super.findById(sql, id, rowMapper());
    }

    public List<Funcionario> findAll() {

        String sql = "SELECT * FROM funcionarios";

        return super.findAll(sql, rowMapper());
    }

    public List<Funcionario> findByCargo(Integer idCargo) {

        String sql = "SELECT * FROM funcionarios WHERE id_cargo = :idCargo";

        return namedQuery().query(sql, new MapSqlParameterSource("idCargo", idCargo), rowMapper());
    }

    public List<Funcionario> findByNome(String nome) {

        String sql = "SELECT * FROM funcionarios WHERE nome LIKE :nome";

        return namedQuery().query(sql, new MapSqlParameterSource("nome", "%" + nome + "%"), rowMapper());
    }
}
