package br.com.devmedia.jdbc;

import br.com.devmedia.jdbc.dao.PessoaDAO;
import br.com.devmedia.jdbc.entity.Pessoa;

import java.util.Calendar;
import java.util.List;

public class App {

    public static void main(String[] args) {

        //new App().insert();
        //new App().findNome();
        //new App().findAll();
        //new App().findProfissao();
        //new App().update();
        new App().delete();
    }

    private void delete() {

        Pessoa pessoa = new PessoaDAO().findById(2);
        System.out.println(pessoa.toString());

        int success = new PessoaDAO().delete(pessoa);

        if (success > 0) {

            System.out.println("Pessoa excluida com sucesso");

        } else {

            System.out.println("Nenhum dado alterado");
        }
    }

    private void update() {

        Pessoa pessoa = new PessoaDAO().findById(2);
        pessoa.setProfissao("Desenvolvedor");

        int success = new PessoaDAO().update(pessoa);

        if (success > 0) {

            System.out.println(new PessoaDAO().findById(pessoa.getId()).toString());

        } else {

            System.out.println("Nenhum dado alterado");
        }
    }

    private void findProfissao() {

        String profissao = "Desenvolvedor";

        List<Pessoa> pessoas = new PessoaDAO().findByProfissao(profissao);

        if (pessoas.size() > 0) {

            for (Pessoa pessoa : pessoas) {

                System.out.println(pessoa.toString());
            }

        } else {

            System.out.println("Nenhum resultado encontrado");
        }
    }

    private void findAll() {

        List<Pessoa> pessoas = new PessoaDAO().findAll();

        System.out.println(pessoas.toString());
    }

    private void findNome() {

        String nome = "Leandro";

        Pessoa pessoa = new PessoaDAO().findByNome(nome);

        System.out.print(pessoa.toString());
    }

    private void insert() {

        Pessoa pessoa = new Pessoa();

        pessoa.setNome("Maria da Silva");
        pessoa.setProfissao("Contador");

        Calendar data = Calendar.getInstance();
        data.set(Calendar.DATE, 5);
        data.set(Calendar.MONTH, Calendar.NOVEMBER);
        data.set(Calendar.YEAR, 1973);

        pessoa.setDtNascimento(data.getTime());

        int id = new PessoaDAO().save(pessoa);

        Pessoa pessoa12 = new PessoaDAO().findById(id);

        System.out.print(pessoa12.toString());
    }
}
