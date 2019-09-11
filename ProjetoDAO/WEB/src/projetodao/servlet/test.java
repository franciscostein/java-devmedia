package projetodao.servlet;

import projetodao.dto.ClienteDTO;
import projetodao.dto.OsDTO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/test")
public class test extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PadraoDTO");

        EntityManager em = entityManagerFactory.createEntityManager();

        OsDTO os = new OsDTO();
        ClienteDTO cliente = em.find(ClienteDTO.class, 1);

        os.setCliente(cliente);
        os.setConteudo("teste");
        os.setTitulo("Teste");

        HttpSession sessao = request.getSession();

        try {
            em.getTransaction().begin();
            em.persist(os);
            em.getTransaction().commit();

            request.setAttribute("msg", "Sucesso");

        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();

            request.setAttribute("msg", "Erro");

        } finally {
            em.close();
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("test.jsp");
        dispatcher.forward(request, response);
    }
}
