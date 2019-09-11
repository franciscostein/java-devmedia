package br.com.devmedia.unitedburger.domain.dao;

import br.com.devmedia.unitedburger.domain.model.Notificacao;

import javax.persistence.EntityManager;

public class NotificacaoDAO {

    public void cadastrar(Notificacao notificacao) {
        EntityManager em = JpaUtil.getParceriasEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(notificacao);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();

            e.printStackTrace();

            throw new RuntimeException("Erro ao realizar cadastro da notificacao");
        } finally {
            em.close();
        }
    }
}
