package br.com.devmedia.blog.config;

import br.com.devmedia.blog.entity.UsuarioLogado;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SpringSecurityAuditor implements AuditorAware<String> {

    @Override
    public String getCurrentAuditor() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); //Pega o usuario do contexto da aplicação

        if (authentication == null || authentication.getPrincipal().equals("annonymousUser")) {

            return authentication.getPrincipal().toString();
        }

        return ((UsuarioLogado) authentication.getPrincipal()).getUsername();
    }
}
