package br.edu.devmedia.crud.command;

import br.edu.devmedia.crud.bo.PessoaBO;
import br.edu.devmedia.crud.dto.PessoaDTO;
import br.edu.devmedia.crud.exception.NegocioException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ConsultasPessoaCommandSemDisplayTag implements Command {

    private String proximo;

    private PessoaBO pessoaBO;

    @Override
    public String execute(HttpServletRequest request) {

        proximo = "consultas.jsp";
        this.pessoaBO = new PessoaBO();

        try {

            List<PessoaDTO> listaPessoas = pessoaBO.listarPessoas();
            request.setAttribute("listaPessoas", listaPessoas);

        } catch (NegocioException e) {

            request.setAttribute("msgErro", e.getMessage());
        }

        return proximo;
    }
}
