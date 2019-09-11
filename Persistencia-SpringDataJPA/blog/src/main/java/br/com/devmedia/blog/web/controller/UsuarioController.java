package br.com.devmedia.blog.web.controller;

import br.com.devmedia.blog.Service.AvatarService;
import br.com.devmedia.blog.Service.UsuarioService;
import br.com.devmedia.blog.entity.Avatar;
import br.com.devmedia.blog.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AvatarService avatarService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView listUsuarios(ModelMap model) {

        List<Usuario> usuarios = usuarioService.findAll();

        model.addAttribute("usuarios", usuarios);

        return new ModelAndView("usuario/list", model);
    }

    @RequestMapping(value = "/perfil/{id}", method = RequestMethod.GET)
    public ModelAndView perfil(@PathVariable("id") Long id) {

        ModelAndView view = new ModelAndView();

        Usuario usuario = usuarioService.findById(id);

        view.addObject("usuario", usuario);
        view.setViewName("usuario/perfil");

        return view;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("usuario") Usuario usuario, @RequestParam(value = "file", required = false) MultipartFile file) {

        Avatar avatar = avatarService.getAvatarByUpload(file);

        usuario.setAvatar(avatar);

        usuarioService.save(usuario);

        //usuario nesse caso se refere a esse controle, perfil é o metodo
        return "redirect:/usuario/perfil/" + usuario.getId();
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView showForm(@ModelAttribute("usuario") Usuario usuario) {

        //nesse caso o usuario/cadastro se refere ao cadastro.jsp (não pode usar jsp pq já foi configurado, ele procuraria .jsp.jsp)
        return new ModelAndView("usuario/cadastro");
    }
}
