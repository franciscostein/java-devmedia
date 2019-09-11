package br.com.devmedia.dao;

import br.com.devmedia.domain.Playlist;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class PlaylistDaoImpl implements PlaylistDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void salvar(Playlist playlist) {
        entityManager.persist(playlist);
    }

    @Override
    public List<Playlist> recuperar() {
        return entityManager.createQuery("select p from Playlist p", Playlist.class).getResultList();
    }

    @Override
    public Playlist recuperarPorID(long id) {
        return entityManager.find(Playlist.class, id);
    }

    @Override
    public void atualizar(Playlist playlist) {
        entityManager.merge(playlist);
    }

    @Override
    public void excluir(long id) {
        entityManager.remove(entityManager.getReference(Playlist.class, id));
    }
}
