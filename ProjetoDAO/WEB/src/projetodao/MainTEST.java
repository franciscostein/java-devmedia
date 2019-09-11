package projetodao;

import projetodao.dto.ClienteDTO;
import projetodao.dto.OsDTO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MainTEST {

    public static void main(String[] args) {

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
