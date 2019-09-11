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

    @ModelAttribute("sexos")    //Pra ficar disponivel em qualquer parte basta chamar a variavel sexos
    public TipoSexo[] tipoSexo() {
        return TipoSexo.values();
    }

    @GetMapping("/sexo")
    public ModelAndView listarPorSexo(@RequestParam(value = "tipoSexo") TipoSexo sexo) {
        if (sexo == null) {
            return new ModelAndView("redirect:/usuario/todos");
        }
        return new ModelAndView("/user/list", "usuarios", dao.getBySexo(sexo));
    }

    @GetMapping("/nome")
    public ModelAndView listarPorNome(@RequestParam(value = "nome") String nome) {
        if (nome == null) {
            return new ModelAndView("redirect:/usuario/todos");
        }
        return new ModelAndView("/user/list", "usuarios", dao.getByNome(nome));
    }

    @RequestMapping(value = "/todos", method = RequestMethod.GET)
    public ModelAndView listaTodos(ModelMap model) {    //ModelMap já vem instanciado
        model.addAttribute("usuarios", dao.getTodos());
        return new ModelAndView("/user/list", model);   //Caminho dentro de view /user/list.jsp
    }

    @GetMapping("/cadastro") //RequestMapping no metodo GET
    public String cadastro(@ModelAttribute("usuario") Usuario usuario, ModelMap model) { //ModelAttribute aqui faz ligação com o modelAttribute do form da JSP
        //model.addAttribute("sexos", TipoSexo.values());
        return "/user/add";
    }
                    //@Valid é para utilizar o BeanValidation dos campos
    @PostMapping("/save") //RequestMapping no metodo POST                   //BindingResult tem que estar antes do RedirectAttributes
    public String save(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "/user/add";
        }
        dao.salvar(usuario);                                        //Usa RedirectAttributes porque ModelMap se perde no redirect
        attributes.addFlashAttribute("message", "Usuário salvo com sucesso!");
        return "redirect:/usuario/todos";   //Redirect = controller, não view
    }

    @GetMapping("/update/{id}")
    public ModelAndView preUpdate(@PathVariable("id") Long id, ModelMap model) { //PathVariable captura o valor recebido e armazena no objeto
        Usuario usuario = dao.getId(id);
        model.addAttribute("usuario", usuario);
        return new ModelAndView("/user/add", model);
    }

    @PostMapping("/update")
    public ModelAndView Update(@ModelAttribute("usuario") Usuario usuario, BindingResult result, RedirectAttributes attributes) { //PathVariable captura o valor recebido e armazena no objeto
        if (result.hasErrors()) {
            return new ModelAndView("/user/add");
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
