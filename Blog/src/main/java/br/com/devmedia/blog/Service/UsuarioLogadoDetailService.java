package br.com.devmedia.blog.Service;

import br.com.devmedia.blog.entity.Usuario;
import br.com.devmedia.blog.entity.UsuarioLogado;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioLogadoDetailService implements UserDetailsService {

    private static final Logger LOG = Logger.getLogger(UsuarioLogadoDetailService.class);

    @Autowired
    private UsuarioService service;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario;

        try {

            usuario = service.findByEmail(username);

            LOG.info("Usuário encontrado: {" + username + "}.");

        } catch (Exception ex) {

            LOG.error("Usuário não encontrado: {" + username + "}.");

            throw new UsernameNotFoundException("Usuário " + username + " não encontrado no sistema");
        }

        return new UsuarioLogado(usuario);
    }
}
