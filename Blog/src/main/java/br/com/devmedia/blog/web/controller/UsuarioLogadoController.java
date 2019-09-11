package br.com.devmedia.blog.web.controller;

import br.com.devmedia.blog.entity.UsuarioLogado;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class UsuarioLogadoController {

    @ModelAttribute("logado")
    public UsuarioLogado getUsuarioLogado(Authentication authentication) {

        return (authentication == null) ? null : (UsuarioLogado) authentication.getPrincipal();
    }
}
