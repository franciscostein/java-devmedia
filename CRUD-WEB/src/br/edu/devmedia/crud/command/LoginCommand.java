package br.edu.devmedia.crud.command;

import br.edu.devmedia.crud.bo.PessoaBO;
import br.edu.devmedia.crud.bo.UsuarioBO;
import br.edu.devmedia.crud.dto.PreferenciaMusicalDTO;
import br.edu.devmedia.crud.dto.UfDTO;
import br.edu.devmedia.crud.dto.UsuarioDTO;
import br.edu.devmedia.crud.exception.NegocioException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class LoginCommand implements Command {

    private UsuarioBO usuarioBO;

    private String proxima;

    private PessoaBO pessoaBO;

    @Override
    public String execute(HttpServletRequest request) {
        //Sempre iniciar com o caso onde o erro pode acontecer
        proxima = "login.jsp";
        usuarioBO = new UsuarioBO();
        pessoaBO = new PessoaBO();

        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setUsuario(request.getParameter("login"));
        usuarioDTO.setSenha(request.getParameter("senha"));

        try {

            if (usuarioBO.validarUsuario(usuarioDTO)) {

                request.getSession().setAttribute("usuario", usuarioDTO);
                proxima = "index.jsp";

                List<UfDTO> listaUFs = pessoaBO.listarUfs();
                List<PreferenciaMusicalDTO> listaPreferencias = pessoaBO.listarPreferencias();
                request.getSession().setAttribute("listaUF", listaUFs);
                request.getSession().setAttribute("listaPreferencias", listaPreferencias);
            }

        } catch (NegocioException e) {

            e.printStackTrace();
            request.setAttribute("msgErro", e.getMessage());
        }

        return proxima;
    }
}
