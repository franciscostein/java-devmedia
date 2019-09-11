package br.com.devmedia.editora.dao.mapper;

import br.com.devmedia.editora.entity.Editora;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EditoraMapper implements RowMapper<Editora> {

    @Override
    public Editora mapRow(ResultSet resultSet, int i) throws SQLException {

        Editora editora = new Editora();

        editora.setId(resultSet.getInt("ID_EDITORA"));
        editora.setRazaoSocial(resultSet.getString("RAZAO_SOCIAL"));
        editora.setCidade(resultSet.getString("CIDADE"));
        editora.setEmail(resultSet.getString("EMAIL"));

        return editora;
    }

    //Classe interna porque já implementamos RowMapper na classe EditoraMapper
    public class CidadeAndEmailMapper implements RowMapper<Editora> {

        @Override
        public Editora mapRow(ResultSet resultSet, int i) throws SQLException {

            Editora editora = new Editora();

            editora.setCidade(resultSet.getString("CIDADE"));
            editora.setEmail(resultSet.getString("EMAIL"));

            return editora;
        }
    }
}
