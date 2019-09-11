package projetodao.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Hibernate {

    private static EntityManagerFactory entityManagerFactory;

    private static EntityManager em;

    public static EntityManager criarEntityManager() {

        if (em == null) {

            entityManagerFactory = Persistence.createEntityManagerFactory("PadraoDTO");

            em = entityManagerFactory.createEntityManager();
        }

        return em;
    }
}
