package br.com.devmedia.blog.web.controller;

import br.com.devmedia.blog.Service.AutorService;
import br.com.devmedia.blog.Service.UsuarioService;
import br.com.devmedia.blog.entity.Autor;
import br.com.devmedia.blog.entity.Usuario;
import br.com.devmedia.blog.entity.UsuarioLogado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("autor")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping(value = "/page/{page}", method = RequestMethod.GET)
    public ModelAndView pageAutores(@PathVariable("page") Integer pagina) {

        ModelAndView view = new ModelAndView("autor/perfil");

        Page<Autor> page = autorService.findByPagination(pagina - 1, 5);

        view.addObject("page", page);
        view.addObject("urlPagination", "/autor/page");

        return view;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id) {

        autorService.delete(id);

        return "redirect:/autor/add";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public ModelAndView preUpdate(@PathVariable("id") Long id) {

        ModelAndView view = new ModelAndView();

        Autor autor = autorService.findById(id);

        view.addObject("autor", autor);
        view.setViewName("autor/cadastro");

        return view;
    }

    @RequestMapping(value = {"/perfil/{id}", "/list"}, method = RequestMethod.GET)
    public ModelAndView getAutor(@PathVariable("id") Optional<Long> id) { //usa optional pq tem 2 metodo na msm função

        ModelAndView view = new ModelAndView("autor/perfil");

        if (id.isPresent()) {

            Autor autor = autorService.findById(id.get());

            view.addObject("autores", Arrays.asList(autor)); //trabalha com uma lista msm sendo um unico autor por causa da JSP que ta preparada pra receber uma lista

        } else {

            //List<Autor> autores = autorService.findAll();
            //view.addObject("autores", autores);

            Page<Autor> page = autorService.findByPagination(0, 5);

            view.addObject("page", page);
            view.addObject("urlPagination", "/autor/page");
        }

        return view;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("autor") @Validated Autor autor, BindingResult result,
                       @AuthenticationPrincipal UsuarioLogado logado) {

        if (result.hasErrors()) {

            return "autor/cadastro";
        }

        if (logado.getId() != null) {

            Usuario usuario = usuarioService.findById(logado.getId());

            autor.setUsuario(usuario);
        }

        autorService.save(autor);

        return "redirect:/autor/perfil/" + autor.getId();
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addAutor(@ModelAttribute("autor") Autor autor, @AuthenticationPrincipal()UsuarioLogado logado) {

        autor = autorService.findByUsuario(logado.getId());

        if (autor == null) {

            return new ModelAndView("autor/cadastro");
        }

        return new ModelAndView("redirect:/autor/perfil/" + autor.getId());
    }
}
