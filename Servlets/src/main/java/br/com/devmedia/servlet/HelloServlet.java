package br.com.devmedia.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/servlet/*")
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.getWriter().print("Be welcome my good friend!");
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String nomeUsuario = req.getParameter("nome");

        nomeUsuario = (!nomeUsuario.isEmpty()) ? nomeUsuario : "visitante";

        StringBuilder html = new StringBuilder();
        html.append("<html>");
        html.append(" <head>");
        html.append("  <title>Estudando Servlets - Recuperando dados enviados por parâmetro</title>");
        html.append(" </head>");
        html.append(" <body>");
        html.append("  <h1>Seja bem-vindo, " + nomeUsuario + "!</h1>");
        html.append(" </body>");
        html.append("</html>");

        resp.getWriter().print(html);
    }
}
