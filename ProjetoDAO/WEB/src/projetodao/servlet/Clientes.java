package projetodao.servlet;

import projetodao.dto.ClienteDTO;
import projetodao.dto.OsDTO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Clientes")
public class Clientes extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //PrintWriter print = response.getWriter();

        //print.println("<h2>Hello Fucking World!</h2>");


        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PadraoDTO");

        EntityManager em = entityManagerFactory.createEntityManager();

        OsDTO os = new OsDTO();
        ClienteDTO cliente = em.find(ClienteDTO.class, 1);

        os.setCliente(cliente);
        os.setConteudo("teste");
        os.setTitulo("Teste");

        try {
            em.getTransaction().begin();
            em.persist(os);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
