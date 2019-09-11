package br.com.devmedia.editora.dao;

import br.com.devmedia.editora.entity.Endereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class EnderecoDAO extends JdbcDaoSupport {

    @Autowired
    public EnderecoDAO(DataSource dataSource) {

        setDataSource(dataSource);
    }

    public List<Endereco> findAll() {

        String sql = "SELECT * FROM ENDERECOS";

        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper<Endereco>(Endereco.class));
    }
}
