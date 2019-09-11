package br.edu.devmedia.crud.command;

import br.edu.devmedia.crud.bo.PessoaBO;
import br.edu.devmedia.crud.exception.NegocioException;

import javax.servlet.http.HttpServletRequest;

public class RemoverPessoaCommand implements Command {

    private String proximo;

    private PessoaBO pessoaBO;

    @Override
    public String execute(HttpServletRequest request) {

        proximo = "main?acao=consultas";
        this.pessoaBO = new PessoaBO();

        try {

            Integer idPessoa = Integer.parseInt(request.getParameter("id_pessoa"));
            pessoaBO.removerPessoa(idPessoa);

            request.setAttribute("msgSucesso", "Pessoa removida com sucesso");

        } catch (NegocioException e) {

            request.setAttribute("msgErro", e.getMessage());
        }

        return proximo;
    }
}
