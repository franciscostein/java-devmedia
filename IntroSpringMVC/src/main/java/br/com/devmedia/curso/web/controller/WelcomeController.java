package br.com.devmedia.curso.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WelcomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)    //Assim que entrar na aplicação vai cair aqui
    public String welcome() {
        //return "welcome";   //nome da página
        return "redirect:/usuario/todos";   //caminho do controller/method, redirect é sempre pra controller
    }

    @RequestMapping(value = "/teste", method = RequestMethod.GET)
    public ModelAndView teste() {
        ModelAndView view = new ModelAndView("welcome");    //nome da pagina
        view.addObject("teste", "Mensagem de teste para método teste do SpringMVC");
        return view;
    }
}
