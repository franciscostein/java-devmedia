package br.com.devmedia.editora.dao.mapper;

import br.com.devmedia.editora.dao.EditoraDAO;
import br.com.devmedia.editora.entity.Autor;
import br.com.devmedia.editora.entity.Editora;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AutorMapper implements RowMapper<Autor> {

    private EditoraDAO editoraDAO;

    public AutorMapper() {
        super();
    }

    public AutorMapper(EditoraDAO editoraDAO) {
        this.editoraDAO = editoraDAO;
    }

    @Override
    public Autor mapRow(ResultSet resultSet, int i) throws SQLException {

        Autor autor = new Autor();

        autor.setId(resultSet.getInt("ID_AUTOR"));
        autor.setNome(resultSet.getString("NOME"));
        autor.setEmail(resultSet.getString("EMAIL"));

        Integer idEditora = resultSet.getInt("ID_EDITORA");

        Editora editora = editoraDAO.findById(idEditora);

        autor.setEditora(editora);

        return autor;
    }

    public class AutorWithEditoraMapper implements RowMapper<Autor> {

        @Override
        public Autor mapRow(ResultSet resultSet, int i) throws SQLException {

            Autor autor = new Autor();

            autor.setId(resultSet.getInt("ID_AUTOR"));
            autor.setNome(resultSet.getString("NOME"));
            autor.setEmail(resultSet.getString("EMAIL"));

            Editora editora = new Editora();

            editora.setId(resultSet.getInt("ED_ID_EDITORA"));
            editora.setRazaoSocial(resultSet.getString("RAZAO_SOCIAL"));
            editora.setCidade(resultSet.getString("CIDADE"));
            editora.setEmail(resultSet.getString("ED_EMAIL"));

            autor.setEditora(editora);

            return autor;
        }
    }
}
