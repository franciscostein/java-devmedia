package br.com.devmedia.appfinal.web.controller;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@ControllerAdvice
public class GenericExceptionController {

    private static Logger logger = Logger.getLogger(GenericExceptionController.class);

    private static final String DEFAULT_PAGE_ERROR = "error";

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ModelAndView integrityKeyException(HttpServletRequest request, DataIntegrityViolationException exception) {

        logger.error("Request: " + request.getRequestURI() + " lançou a excessão: " + exception);

        ModelAndView modelAndView = new ModelAndView(DEFAULT_PAGE_ERROR);

        modelAndView.addObject("mensagem", "Impossível inserir, este registro já existe no banco de dados");
        modelAndView.addObject("exception", exception);
        modelAndView.addObject("url", request.getRequestURL());

        return modelAndView;
    }

    @ExceptionHandler({SQLException.class, DataAccessException.class})
    public ModelAndView sqlException(HttpServletRequest request, Exception exception) {

        logger.error("Request: " + request.getRequestURI() + " lançou a excessão: " + exception);

        ModelAndView modelAndView = new ModelAndView(DEFAULT_PAGE_ERROR);

        modelAndView.addObject("mensagem", "Ocorreu um erro ao acessar o banco de dados");
        modelAndView.addObject("exception", exception);
        modelAndView.addObject("url", request.getRequestURL());

        return modelAndView;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView defaultException(HttpServletRequest request, Exception exception) {

        logger.error("Request: " + request.getRequestURI() + " lançou a excessão: " + exception);

        ModelAndView modelAndView = new ModelAndView(DEFAULT_PAGE_ERROR);

        modelAndView.addObject("mensagem", "Ocorreu uma exceção, tente novamente");
        modelAndView.addObject("exception", exception);
        modelAndView.addObject("url", request.getRequestURL());

        return modelAndView;
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView notFoundException(HttpServletRequest request, Exception exception) {

        logger.error("Request: " + request.getRequestURI() + " lançou a excessão: " + exception);

        ModelAndView modelAndView = new ModelAndView(DEFAULT_PAGE_ERROR);

        modelAndView.addObject("mensagem", "Ops! Está página não existe por aqui");
        modelAndView.addObject("exception", exception);
        modelAndView.addObject("url", request.getRequestURL());

        return modelAndView;
    }
}
