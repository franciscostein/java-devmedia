package br.edu.devmedia.crud.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ServletReqListener implements ServletRequestListener {

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("Destruiu. \n IP Remoto: " + sre.getServletRequest().getRemoteAddr());
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println("Iniciou. \n IP Remoto: " + sre.getServletRequest().getRemoteAddr());
    }
}
