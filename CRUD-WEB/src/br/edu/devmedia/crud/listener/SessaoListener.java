package br.edu.devmedia.crud.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessaoListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("Sessão iniciada");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("Sessão encerrada");
    }
}
