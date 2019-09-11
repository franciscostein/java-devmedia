package br.com.devmedia.editora;

import br.com.devmedia.editora.dao.*;
import br.com.devmedia.editora.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Hello world!
 *
 */
@EnableAutoConfiguration
@ComponentScan
public class AppEditora implements CommandLineRunner {

    @Autowired
    private EditoraDAO editoraDAO;

    @Autowired
    private ExecuteExample executeExample;

    @Autowired
    private LivroDAO livroDAO;

    @Autowired
    private LivroAutorDAO livroAutorDAO;

    @Autowired
    private AutorDAO autorDAO;

    @Autowired
    private EnderecoDAO enderecoDAO;

    public static void main( String[] args ) {

        SpringApplication springApplication = new SpringApplication();

        springApplication.run(AppEditora.class);
    }

    @Override
    public void run(String... strings) throws Exception {

        System.out.println( "------------------------------------------------" );

        //insertEditora();
        //findAllEditoras();
        //findByIdEditora();
        //findByCidadesEditora();
        //findByRazaoSocialEditora();
        //countEditora();
        //findEmailByIdEditora();
        //findAllEmails();
        //findCidadeAndEmailByIdEditora();
        //findCidadeAndEmailForIdEditora();
        //findCidadesAndEmailsEditora();
        //updateEditora();
        //deleteEditora();

        //execute();
        //deleteLivro();
        //findLivro();
        //dropTable();

        //insertAutor();
        //findAllAutores();
        //findAutoresByEditora();
        //updateAutor();
        //deleteAutor();
        //findEditoraWithAutores();

        //insertLivro();
        //findLivroWithAutores();
        //findAutorWithLivros();
        //findLivrosByEdicao();
        //findLivrosByPaginas();
        //updateLivro();
        //findLivroByTituloAndEdicao();

        //procedureUppercase();
        //procedureInfo();

        //functionAutorLivro();

        //insertBatchEditoras();
        //updateBatchAutores();
        //deleteBatchAutores();

        //findAllEnderecos();

        System.out.println( "------------------------------------------------" );
    }

    private void findAllEnderecos() {

        List<Endereco> enderecos = enderecoDAO.findAll();

        enderecos.forEach(System.out::println);
    }

    private void deleteBatchAutores() {

        autorDAO.deleteBatch(Arrays.asList(9, 10, 11));
    }

    private void updateBatchAutores() {

        Autor autor = autorDAO.findById(1);
        autor.setEmail("email@email.com");

        Autor autor1 = autorDAO.findById(2);
        autor1.setNome("Mozart");

        Autor autor2 = autorDAO.findById(3);
        autor2.setNome("Pedro");

        autorDAO.updateBatch(Arrays.asList(autor, autor1, autor2));

        findAllAutores();
    }

    private void insertBatchEditoras() {

        List<Editora> editoras = new ArrayList<Editora>();

        editoras.add(new Editora("Editora LTDA", "São Paulo", "editora@email.com"));
        editoras.add(new Editora("Editora Onça LTDA", "São Paulo", "onca@email.com"));
        editoras.add(new Editora("Editora Leão LTDA", "São Paulo", "leao@email.com"));
        editoras.add(new Editora("Editora Urso LTDA", "São Paulo", "urso@email.com"));

        editoraDAO.saveBatch(editoras);

        editoras = new ArrayList<Editora>();

        editoras.add(new Editora("Editora Gato LTDA", "Campinas", "gato@email.com"));
        editoras.add(new Editora("Editora Cachorro LTDA", "Manaus", "cachorro@email.com"));
        editoras.add(new Editora("Editora Tigre LTDA", "Rio de Janeiro", "tigre@email.com"));
        editoras.add(new Editora("Editora Elefante LTDA", "Florianopolis", "elefante@email.com"));

        editoraDAO.insertBatch(editoras);

        findAllEditoras();
    }

    private void functionAutorLivro() {

        String texto = livroDAO.callFunctionTotalLivrosByAutor(1);

        System.out.println(texto);
    }

    private void procedureInfo() {

        List<String> retorno = livroDAO.callProcedureInfoLivro(3);

        System.out.println(retorno.toString());
    }

    private void procedureUppercase() {

        Map<String, Object> map = livroDAO.callProcedureUppercaseTitulo(1);

        for (Map.Entry<String, Object> keyValue : map.entrySet()) {

            System.out.println(keyValue.getKey() + "\n" + keyValue.getValue());
        }
    }

    private void findLivroByTituloAndEdicao() {

        Livro livro = livroDAO.findByTituloAndEdicao("The Last Wish", 2);

        System.out.println(livro.toString());
    }

    private void updateLivro() {

        Livro livro = livroDAO.findLivroWithAutores(2);

        System.out.println(livro.toString());

        livro.setEdicao(3);

        int ok = livroDAO.update(livro);

        if (ok == 1) {

            livro = livroDAO.findLivroWithAutores(2);

            System.out.println(livro.toString());

        } else {

            System.out.println("Operação não realizada");
        }
    }

    private void findLivrosByPaginas() {

        List<Livro> livros = livroDAO.findByPaginas(100, 400);

        for (Livro livro : livros) {

            System.out.println(livro.toString());
        }
    }

    private void findLivrosByEdicao() {

        List<Livro> livros = livroDAO.findByEdicao(1);

        livros.forEach(System.out::println);
    }

    private void findAutorWithLivros() {

        Autor autor = autorDAO.findAutorWithLivros(1);

        System.out.println(autor.toString());

        autor.getLivros().forEach(System.out::println);
    }

    private void findLivroWithAutores() {

        Livro livro = livroDAO.findLivroWithAutores(1);

        System.out.println(livro.toString());

        livro.getAutores().forEach(System.out::println);
    }

    private void insertLivro() {

        String titulo = "The Last Wish";
        int edicao = 2;
        int paginas = 384;
        String[] autores = {"Andrzej Sapkowski"};

        Livro livro = new Livro();

        livro.setTitulo(titulo);
        livro.setEdicao(edicao);
        livro.setPaginas(paginas);

        livro = livroDAO.save(livro);

        Integer idLivro = livro.getId();

        for (String nome : autores) {

            Integer idAutor = autorDAO.getIdByNome(nome);

            livroAutorDAO.save(new LivroAutor(idLivro, idAutor));
        }
    }

    private void findEditoraWithAutores() {

        Editora editora = editoraDAO.findEditoraWithAutores(6, 0, 3);

        System.out.println("Editora: " + editora.getId() + ", " + editora.getRazaoSocial() + ", " + editora.getCidade() + ", " + editora.getEmail());

        System.out.println("Autores: ");

        for (Autor autor : editora.getAutores()) {

            System.out.println("         " + autor.getId() + ", " + autor.getNome() + ", " + autor.getEmail());
        }

        System.out.println("---------------------------------------------------------");

        editora = editoraDAO.findEditoraWithAutores(6, 1, 3);

        for (Autor autor : editora.getAutores()) {

            System.out.println("         " + autor.getId() + ", " + autor.getNome() + ", " + autor.getEmail());
        }

        System.out.println("---------------------------------------------------------");

        editora = editoraDAO.findEditoraWithAutores(6, 2, 3);

        for (Autor autor : editora.getAutores()) {

            System.out.println("         " + autor.getId() + ", " + autor.getNome() + ", " + autor.getEmail());
        }
    }

    private void deleteAutor() {

        int ok = autorDAO.delete(2);

        if (ok == 1) {

            System.out.println("Excluido com sucesso");

        } else {

            System.out.println("Não excluido");
        }
    }

    private void updateAutor() {

        Autor autor = autorDAO.findById(1);

        Editora editora = new Editora();
        editora.setId(4);

        autor.setEditora(editora);

        int ok = autorDAO.update(autor);

        if (ok == 1) {

            Autor autor1 = autorDAO.findById(1);

            System.out.println(autor1.toString());

        } else {

            System.out.println("Não alterado");
        }
    }

    private void findAutoresByEditora() {

        List<Autor> autores = autorDAO.findAutoresByEditora("Darski");

        for (Autor autor : autores) {

            System.out.println(autor.toString());
        }
    }

    private void findAllAutores() {

        List<Autor> autores = autorDAO.findAll();

        autores.forEach(System.out::println);
    }

    private void insertAutor() {

        Editora editora = editoraDAO.findById(4);

        Autor autor = new Autor();
        autor.setNome("Bert Bates");
        autor.setEmail("bates@email.com");
        autor.setEditora(editora);

        if (autor.getId() == null) {

            autor = autorDAO.save(autor);
        }

        if (autor.getId() != null) {

            System.out.println(autor.toString());
        }
    }

    private void dropTable() {

        executeExample.dropTable();
    }

    private void findLivro() {

        executeExample.find();
    }

    private void deleteLivro() {

        executeExample.delete();
    }

    private void execute() {

        executeExample.insert();
    }

    private void deleteEditora() {

        int ok = editoraDAO.delete(1);

        if (ok == 1) {

            System.out.println("Operação realizada com sucesso");

        } else {

            System.out.println("Operação não realizada");
        }
    }

    private void updateEditora() {

        Editora editora = editoraDAO.findById(3);
        System.out.println(editora.toString());

        editora.setCidade("London");

        int ok = editoraDAO.update(editora);

        if (ok == 1) {

            Editora editora1 = editoraDAO.findById(3);

            System.out.println(editora.toString());

        } else {

            System.out.println("Atualização não realizada");
        }
    }

    private void findCidadesAndEmailsEditora() {

        List<Editora> editoras = editoraDAO.findCidadesAndEmails();

        for (Editora editora: editoras) {

            System.out.println(editora);
        }
    }

    private void findCidadeAndEmailForIdEditora() {

        Editora editora = editoraDAO.findCidadeAndEmailForId(3);

        System.out.println(editora.toString());
    }

    private void findCidadeAndEmailByIdEditora() {

        List<String> lista = editoraDAO.findCidadeAndEmailById(2);

        lista.forEach(System.out::println);
    }

    private void findAllEmails() {

        List<String> emails = editoraDAO.findEmails();

        for (String email : emails) {

            System.out.println(email);
        }
    }

    private void findEmailByIdEditora() {

        String email = editoraDAO.findByEmailById(2);

        System.out.println("E-mail: " + email);
    }

    private void countEditora() {

        int count = editoraDAO.count();

        System.out.println("Count: " + count);
    }

    private void findByRazaoSocialEditora() {

        List<Editora> editoras = editoraDAO.findByRazaoSocial("LTDA");

        editoras.forEach(System.out::println);
    }

    private void findByCidadesEditora() {

        List<Editora> editoras = editoraDAO.findByCidades("Campinas", "Warsaw");

        editoras.forEach(System.out::println);
    }

    private void findByIdEditora() {

        Editora editora = editoraDAO.findById(1);

        System.out.println(editora.toString());
    }

    private void findAllEditoras() {

        List<Editora> editoras = new ArrayList<Editora>();

        editoras = editoraDAO.findAll();

        for (Editora editora : editoras) {

            System.out.println(editora.toString());
        }
    }

    private void insertEditora() {

        Editora editora = new Editora();
        editora.setRazaoSocial("Lighthouse LTDA ME");
        editora.setCidade("London");
        editora.setEmail("lighthouse@email.com");

        //int ok = editoraDAO.insert(editora);
        //System.out.println("OK = " + ok);

        //int id = editoraDAO.save(editora);
        //System.out.println("Id: " + id);

        editora = editoraDAO.add(editora);
        System.out.println(editora.toString());
    }
}
