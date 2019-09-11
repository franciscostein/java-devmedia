package jdbc.servlet;

import jdbc.bo.LoginBO;
import jdbc.dto.LoginDTO;
import jdbc.exception.NegocioException;
import jdbc.bo.UfBO;
import jdbc.dto.UfDTO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        LoginBO loginBO = new LoginBO();

        String login = request.getParameter("login");
        String senha = request.getParameter("senha");

        HttpSession sessao = request.getSession();

        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setNome(login);
        loginDTO.setSenha(senha);

        String proxPage = "home.jsp";

        try {

            boolean resposta = loginBO.logar(loginDTO);

            if (!resposta) {

                request.setAttribute("msg", "Usuário/senha inválidos");
                proxPage = "login.jsp";

            } else {

                UfBO ufBO = new UfBO();

                List<UfDTO> lista = ufBO.listaUFs();

                sessao.setAttribute("listaUfs", lista);
            }

        } catch (NegocioException e) {
            e.printStackTrace();
            request.setAttribute("msg", e.getMessage());
            proxPage = "login.jsp";
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(proxPage);
        dispatcher.forward(request, response);
    }
}
