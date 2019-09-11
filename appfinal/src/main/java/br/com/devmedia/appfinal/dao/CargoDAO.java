package br.com.devmedia.appfinal.dao;

import br.com.devmedia.appfinal.entity.Cargo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CargoDAO extends GenericDAO<Cargo> {

    private DepartamentoDAO departamentoDAO;

    @Autowired
    public CargoDAO(DataSource dataSource, DepartamentoDAO departamentoDAO) {

        super(dataSource, Cargo.class);
        this.departamentoDAO = departamentoDAO;
    }

    @Override
    protected SqlParameterSource parameterSource(Cargo cargo) {

        return new MapSqlParameterSource()
                .addValue("cargo", cargo.getCargo())
                .addValue("idDepartamento", cargo.getDepartamento().getIdDepartamento())
                .addValue("idCargo", cargo.getIdCargo());
    }

    @Override
    protected RowMapper<Cargo> rowMapper() {

        return new RowMapper<Cargo>() {
            @Override
            public Cargo mapRow(ResultSet resultSet, int i) throws SQLException {

                Cargo cargo = new Cargo();

                cargo.setIdCargo(resultSet.getInt("ID_CARGO"));
                cargo.setCargo(resultSet.getString("CARGO"));
                cargo.setDepartamento(departamentoDAO.findById(resultSet.getInt("ID_DEPARTAMENTO")));

                return cargo;
            }
        };
    }

    public Cargo save(Cargo cargo) {

        Number key = super.save("CARGOS", "ID_CARGO", parameterSource(cargo));

        cargo.setIdCargo(key.intValue());

        return cargo;
    }

    public int update(Cargo cargo) {

        String sql = "UPDATE cargos SET cargo = :cargo, id_departamento = :idDepartamento " +
                "WHERE id_cargo = :idCargo";

        return super.update(sql, parameterSource(cargo));
    }

    public int delete(Integer id) {

        String sql = "DELETE FROM cargos WHERE id_cargo = ?";

        return super.delete(sql, id);
    }

    public Cargo findById(Integer id) {

        String sql = "SELECT * FROM cargos WHERE id_cargo = ?";

        return super.findById(sql, id, rowMapper());
    }

    public List<Cargo> findAll() {

        String sql = "SELECT * FROM cargos";

        return  super.findAll(sql, rowMapper());
    }

    public List<Cargo> findByPage(int page, int size) {

        return namedQuery().query("SELECT * FROM cargos limit :page, :size",
                new MapSqlParameterSource("page", page).addValue("size", size), rowMapper());
    }
}
