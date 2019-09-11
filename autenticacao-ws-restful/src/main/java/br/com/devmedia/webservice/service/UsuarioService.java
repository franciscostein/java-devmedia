package br.com.devmedia.webservice.service;

import br.com.devmedia.webservice.dao.UsuarioDAO;
import br.com.devmedia.webservice.domain.Usuario;

import javax.persistence.NoResultException;

public class UsuarioService {

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    public boolean validarUsuario(Usuario usuario) {
        try {
            usuarioDAO.obterUsuario(usuario);
        } catch (NoResultException ex) {
            return false;
        }
        return true;
    }
}
