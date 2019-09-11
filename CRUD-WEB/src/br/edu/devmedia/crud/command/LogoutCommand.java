package br.edu.devmedia.crud.command;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {

        request.getSession().invalidate();
        request.setAttribute("msgSucesso", "Usuário deslogado!");

        return "login.jsp";
    }
}
