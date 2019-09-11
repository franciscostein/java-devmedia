package br.com.devmedia.curso.web.controller;

import br.com.devmedia.curso.dao.UsuarioDao;
import br.com.devmedia.curso.domain.TipoSexo;
import br.com.devmedia.curso.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("usuario")
public class UsuarioController {

    @Autowired
    private UsuarioDao dao;

    @GetMapping("/todos")
    public ModelAndView home(ModelMap model) {
        model.addAttribute("usuarios", dao.getTodos());
        model.addAttribute("conteudo", "/user/list");
        return new ModelAndView("layout", model);
    }

    @GetMapping("/nome")
    public ModelAndView listarPorNome(@RequestParam(value = "nome") String nome, ModelMap model) {
        if (nome == null) {
            return new ModelAndView("redirect:/usuario/todos");
        }
        model.addAttribute("usuarios", dao.getByNome(nome));
        model.addAttribute("conteudo", "/user/list");
        return new ModelAndView("layout", model);
    }

    @ModelAttribute("sexos")    //Pra ficar disponivel em qualquer parte basta chamar a variavel sexos
    public TipoSexo[] tipoSexo() {
        return TipoSexo.values();
    }

    @GetMapping("/sexo")
    public ModelAndView listarPorSexo(@RequestParam(value = "tipoSexo") TipoSexo sexo, ModelMap model) {
        if (sexo == null) {
            return new ModelAndView("redirect:/usuario/todos");
        }
        model.addAttribute("usuarios", dao.getBySexo(sexo));
        model.addAttribute("conteudo", "/user/list");
        return new ModelAndView("layout", model);
    }

    @GetMapping("/cadastro") //RequestMapping no metodo GET
    public ModelAndView cadastro(Usuario usuario) {

        return new ModelAndView("layout", "conteudo", "/user/add");
    }
                    //@Valid é para utilizar o BeanValidation dos campos
    @PostMapping("/save") //RequestMapping no metodo POST                   //BindingResult tem que estar antes do RedirectAttributes
    public ModelAndView save(@Valid Usuario usuario, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return new ModelAndView("layout", "conteudo", "/user/add");
        }
        dao.salvar(usuario);                                        //Usa RedirectAttributes porque ModelMap se perde no redirect
        attributes.addFlashAttribute("message", "Registro inserido com sucesso.");
        return new ModelAndView("redirect:/usuario/todos");
    }

    @GetMapping("/update/{id}")
    public ModelAndView preUpdate(@PathVariable("id") Long id, ModelMap model) { //PathVariable captura o valor recebido e armazena no objeto
        model.addAttribute("usuario", dao.getId(id));
        model.addAttribute("conteudo", "/user/add");
        return new ModelAndView("layout", model);
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute("usuario") Usuario usuario, BindingResult result, RedirectAttributes attributes) { //PathVariable captura o valor recebido e armazena no objeto
        if (result.hasErrors()) {
            return new ModelAndView("layout", "conteudo", "/user/add");
        }
        dao.editar(usuario);    //RedirectAttributes não é válido para retornar como parametro, mas como ele faz parte da assinatura irá retornar junto
        attributes.addFlashAttribute("message", "Usuário alterado com sucesso!");
        return new ModelAndView("redirect:/usuario/todos");
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id") Long id, RedirectAttributes attributes) {
        dao.excluir(id);
        attributes.addFlashAttribute("message", "Usuário excluido com sucesso!");
        return "redirect:/usuario/todos";
    }
}
