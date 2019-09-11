package br.com.devmedia.webservice.service;

import br.com.devmedia.webservice.dao.UsuarioDAO;
import br.com.devmedia.webservice.domain.Tipo;
import br.com.devmedia.webservice.domain.Usuario;

import javax.persistence.NoResultException;
import java.util.List;

public class UsuarioService {

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    public Usuario autenticarUsuario(Usuario usuario) {
        try {
            usuario = usuarioDAO.recuperarUsuarioPorUsernameEPassword(usuario.getUsername(), usuario.getPassword());
        } catch (NoResultException ex) {
            return null;
        }
        return usuario;
    }

    public Usuario cadastrarUsuario(Usuario usuario) {
        if (Tipo.CLIENTE.equals(usuario.getTipo())) {
            return usuarioDAO.salvarUsuario(usuario);
        }
        return null;
    }

    public Usuario obterUsuario(long id) {
        return usuarioDAO.recuperarUsuarioPorId(id);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioDAO.selecionarUsuarios();
    }

    public void atualizarUsuario(Usuario usuario, long id) {
        usuario.setId(id);
        usuarioDAO.atualizarUsuario(usuario);
    }

    public void descadastrarUsuario(long id) {
        usuarioDAO.excluirUsuario(id);
    }
}
