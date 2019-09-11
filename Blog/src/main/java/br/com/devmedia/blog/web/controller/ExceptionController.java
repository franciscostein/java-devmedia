package br.com.devmedia.blog.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice //Faz o controler ficar esperando por uma exceção
public class ExceptionController {

    //Pra funcionar precisa de dispatcherServlet.setThrowExceptionIfNoHandlerFound(true); em SpringWebXmlConfig, pois por padrão é false
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, code = HttpStatus.NOT_FOUND)
    public ModelAndView noHandlerFoundException(HttpServletRequest req, NoHandlerFoundException ex) {

        ModelAndView view = new ModelAndView("error");

        view.addObject("mensagem", "Ops :( </br> Está página não existe por aqui!");
        view.addObject("url", req.getRequestURL());
        view.addObject("excecao", ex);

        return view;
    }

    //Exceção generica
    @ExceptionHandler(Exception.class)
    public ModelAndView genericException(HttpServletRequest req, Exception ex) {

        ModelAndView view = new ModelAndView("error");

        view.addObject("mensagem", "Ocorreu um erro durante a operação, tente novamente.");
        view.addObject("url", req.getRequestURL());
        view.addObject("excecao", ex);

        return view;
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ModelAndView maxUploadSizeExceededException(HttpServletRequest req, MaxUploadSizeExceededException ex) {

        ModelAndView view = new ModelAndView("error");

        view.addObject("mensagem", "O arquivo selecionado não pode ser maior que 100kb.");
        view.addObject("url", req.getRequestURL());
        view.addObject("excecao", ex);

        return view;
    }
}
