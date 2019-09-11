package br.com.devmedia.editora.dao.mapper;

import br.com.devmedia.editora.entity.Livro;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LivroMapper implements RowMapper<Livro> {

    @Override
    public Livro mapRow(ResultSet resultSet, int i) throws SQLException {

        Livro livro = new Livro();

        livro.setId(resultSet.getInt("ID_LIVRO"));
        livro.setTitulo(resultSet.getString("TITULO"));
        livro.setEdicao(resultSet.getInt("EDICAO"));
        livro.setPaginas(resultSet.getInt("PAGINAS"));

        return livro;
    }
}
