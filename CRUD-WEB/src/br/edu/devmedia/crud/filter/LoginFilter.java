package br.edu.devmedia.crud.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalTime;

@WebFilter("/*")
public class LoginFilter implements Filter {

    private static final String[] URLS_TO_EXCLUDE = {".css", ".js", ".png", ".jpg", ".gif", "login.jsp"};

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Filtro de login iniciado: " + LocalTime.now());
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = ((HttpServletRequest) request);
        String uri = httpRequest.getRequestURI();
        String acao = httpRequest.getParameter("acao");

        if (!(isURLException(uri) || isPermitted(uri, acao))) {

            HttpSession session = httpRequest.getSession();

            if (session.getAttribute("usuario") == null) {

                request.setAttribute("msgErro", "Acesso negado. Você precisa logar primeiro.");
                request.getRequestDispatcher("login.jsp").forward(request, response);

            } else {

                chain.doFilter(request, response);
            }

        } else {
                                                //doFilter LIBERA! (sendo excessão ou permitido vai vir pra cá)
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        System.out.println("Filtro de login destruido: " + LocalTime.now());
    }

    private boolean isURLException(String uri) {

        boolean retorno = false;

        for (String url : URLS_TO_EXCLUDE) {

            if (uri != null && uri.endsWith(url)) {

                retorno = true;
            }
        }

        return retorno;
    }

    private boolean isPermitted(String uri, String acao) {

        boolean retorno = false;

        if (uri != null && uri.endsWith("main") && (acao != null && acao.equals("login"))) {

            retorno = true;
        }

        return retorno;
    }
}
