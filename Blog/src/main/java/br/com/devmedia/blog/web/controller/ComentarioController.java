package br.com.devmedia.blog.web.controller;

import br.com.devmedia.blog.Service.ComentarioService;
import br.com.devmedia.blog.Service.PostagemService;
import br.com.devmedia.blog.Service.UsuarioService;
import br.com.devmedia.blog.entity.Comentario;
import br.com.devmedia.blog.entity.Postagem;
import br.com.devmedia.blog.entity.UsuarioLogado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("comentario")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    @Autowired
    private PostagemService postagemService;

    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)           //O BindingResult sempre tem que estár depois do campo a ser validado
    public ModelAndView save(@ModelAttribute("comentario") @Validated Comentario comentario, BindingResult result,
                             @RequestParam("permalink") String permalink, @AuthenticationPrincipal UsuarioLogado logado) { //@PathVariable vem pela URL, por isso usando @RequestParam

        Postagem postagem = postagemService.findByPermalink(permalink);

        if (result.hasErrors()) {

            return new ModelAndView("post", "postagem", postagem);
        }

        comentario.setUsuario(usuarioService.findById(logado.getId()));
        comentario.setPostagem(postagem);

        comentarioService.save(comentario);

        return new ModelAndView("redirect:/Blog/" + permalink);
    }
}
