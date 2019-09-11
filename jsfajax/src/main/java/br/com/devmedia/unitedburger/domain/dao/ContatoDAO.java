package br.com.devmedia.unitedburger.domain.dao;

import br.com.devmedia.unitedburger.domain.model.Contato;

import javax.persistence.EntityManager;

public class ContatoDAO {

    public void cadastrar(Contato contato) {
        EntityManager em = JpaUtil.getUnitedBurgerEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(contato);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();

            e.printStackTrace();

            throw new RuntimeException("Erro ao realizar cadastro do contato");
        } finally {
            em.close();
        }
    }
}
