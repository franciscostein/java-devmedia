package br.edu.devmedia.crud.command;

import br.edu.devmedia.crud.bo.PessoaBO;
import br.edu.devmedia.crud.dao.PessoaDAO;
import br.edu.devmedia.crud.dto.CidadeDTO;
import br.edu.devmedia.crud.dto.PessoaDTO;
import br.edu.devmedia.crud.exception.NegocioException;
import br.edu.devmedia.crud.exception.PersistenciaException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class EditarPessoaCommand implements Command {

    private String proximo;

    private PessoaBO pessoaBO;

    private PessoaDAO pessoaDAO;

    @Override
    public String execute(HttpServletRequest request) {

        proximo = "edicaoPessoa.jsp";
        this.pessoaBO = new PessoaBO();
        this.pessoaDAO = new PessoaDAO();

        try {

            Integer idPessoa = Integer.parseInt(request.getParameter("id_pessoa"));
            PessoaDTO pessoa = pessoaBO.consultarPessoaPorId(idPessoa);

            List<CidadeDTO> listaCidades = pessoaDAO.consultarCidadesPorEstado(pessoa.getEndereco().getCidade().getUf().getIdUf());
            request.setAttribute("listaCidades", listaCidades);
            request.setAttribute("pessoa", pessoa);

        } catch (NegocioException | PersistenciaException e) {

            request.setAttribute("msgErro", e.getMessage());
        }

        return proximo;
    }
}
