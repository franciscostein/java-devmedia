package br.edu.devmedia.crud.command;

import br.edu.devmedia.crud.dao.PessoaDAO;
import br.edu.devmedia.crud.dto.CidadeDTO;
import br.edu.devmedia.crud.dto.PreferenciaMusicalDTO;
import br.edu.devmedia.crud.dto.UfDTO;
import br.edu.devmedia.crud.exception.PersistenciaException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class MontagemCadastroCommand implements Command {

    private String proximo;

    private PessoaDAO pessoaDAO;

    @Override
    public String execute(HttpServletRequest request) {

        pessoaDAO = new PessoaDAO();
        String isEdit = request.getParameter("isEdit");
        String isSearch = request.getParameter("isSearch");

        if (isEdit != null && !"".equals(isEdit)) {

            proximo = "edicaoPessoa.jsp";

        } else if (isSearch != null && !"".equals(isSearch)) {

            proximo = "main?acao=filtrar";

        } else {

            proximo = "cadastroPessoa.jsp";
        }

        String getCidades = request.getParameter("getCidades");

        try {

            if (getCidades != null && !"".equals(getCidades)) {

                String id = request.getParameter("idEstado");
                int idEstado = Integer.parseInt(id);

                List<CidadeDTO> listaCidades = pessoaDAO.consultarCidadesPorEstado(idEstado);
                request.setAttribute("listaCidades", listaCidades);
            }

        } catch (PersistenciaException e) {

            request.setAttribute("msgErro", e.getMessage());
        }

        return proximo;
    }
}
