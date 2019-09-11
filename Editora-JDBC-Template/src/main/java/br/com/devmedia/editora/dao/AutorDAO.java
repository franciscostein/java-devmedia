package br.com.devmedia.editora.dao;

import br.com.devmedia.editora.dao.mapper.AutorMapper;
import br.com.devmedia.editora.entity.Autor;
import br.com.devmedia.editora.entity.Editora;
import br.com.devmedia.editora.entity.Livro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
@PropertySource("classpath:sql/autor.xml")
public class AutorDAO {

    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    private EditoraDAO editoraDAO;

    @Value("${sql.autor.findAll}")
    private String SQL_FIND_ALL;

    @Value("${sql.autor.findAutoresBy.editora}")
    private String SQL_FIND_AUTORES_BY_EDITORA;

    @Value("${sql.autor.update}")
    private String SQL_UPDATE;

    @Value("${sql.autor.findBy.id}")
    private String SQL_FIND_BY_ID;

    @Value("${sql.autor.delete}")
    private String SQL_DELETE;

    @Value("${sql.autor.findIdByNome}")
    private String SQL_GET_ID_BY_NOME;

    @Value("${sql.autor.findAutorWithLivros}")
    private String SQL_FIND_AUTOR_WITH_LIVROS;

    public void deleteBatch(final List<Integer> ids) {

        jdbcTemplate.batchUpdate(SQL_DELETE, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {

                preparedStatement.setInt(1, ids.get(i));
            }

            @Override
            public int getBatchSize() {

                return ids.size();
            }
        });
    }

    public void updateBatch(final List<Autor> autores) {

        jdbcTemplate.batchUpdate(SQL_UPDATE, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {

                preparedStatement.setString(1, autores.get(i).getNome());
                preparedStatement.setString(2, autores.get(i).getEmail());
                preparedStatement.setInt(3, autores.get(i).getEditora().getId());
                preparedStatement.setInt(4, autores.get(i).getId());
            }

            @Override
            public int getBatchSize() {

                return autores.size();
            }
        });
    }

    public Autor findAutorWithLivros(int idAutor) {

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(SQL_FIND_AUTOR_WITH_LIVROS, idAutor);

        Autor autor = null;

        List<Livro> livros = new ArrayList<Livro>();

        for (Map row : rows) {

            if (autor == null) {

                autor = new Autor();

                autor.setId((Integer) row.get("ID_AUTOR"));
                autor.setNome((String) row.get("NOME"));
                autor.setEmail((String) row.get("EMAIL"));

                Editora editora = new Editora();

                editora.setId((Integer) row.get("ID_EDITORA"));
                editora.setRazaoSocial((String) row.get("RAZAO_SOCIAL"));
                editora.setCidade((String) row.get("CIDADE"));
                editora.setEmail((String) row.get("ED_EMAIL"));

                autor.setEditora(editora);
            }

            Livro livro = new Livro();

            livro.setId((Integer) row.get("ID_LIVRO"));
            livro.setTitulo((String) row.get("TITULO"));
            livro.setEdicao((Integer) row.get("EDICAO"));
            livro.setPaginas((Integer) row.get("PAGINAS"));

            livros.add(livro);
        }

        autor.setLivros(livros);

        return autor;
    }

    public Integer getIdByNome(String nome) {

        return jdbcTemplate.queryForObject(SQL_GET_ID_BY_NOME, Integer.class, nome);
    }

    public int delete(int id) {

        return jdbcTemplate.update(SQL_DELETE, id);
    }

    public Autor findById(int id) {

        return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new AutorMapper(editoraDAO), id);
    }

    public int update(Autor autor) {

        return jdbcTemplate.update(SQL_UPDATE, autor.getNome(), autor.getEmail(), autor.getEditora().getId(), autor.getId());
    }

    public List<Autor> findAutoresByEditora(String razaoSocial) {

        return jdbcTemplate.query(SQL_FIND_AUTORES_BY_EDITORA, new AutorMapper().new AutorWithEditoraMapper(), razaoSocial);
    }

    public List<Autor> findAll() {

        return jdbcTemplate.query(SQL_FIND_ALL, new AutorMapper(editoraDAO));
    }

    public Autor save(Autor autor) {

        //Tem que usar dessa forma e não com o .usingColumns abaixo por causa do Objeto Editora
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("NOME", autor.getNome())
                .addValue("EMAIL", autor.getEmail())
                .addValue("ID_EDITORA", autor.getEditora().getId());

        Number key = simpleJdbcInsert.executeAndReturnKey(parameterSource);

        autor.setId(key.intValue());

        return autor;
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {

        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("AUTORES")
                //.usingColumns("NOME", "EMAIL", "ID_EDITORA")
                .usingGeneratedKeyColumns("ID_AUTOR");
    }
}
