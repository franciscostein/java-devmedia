package br.com.devmedia.dao;

import br.com.devmedia.domain.Musica;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MusicaDaoImpl implements MusicaDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void salvar(Musica musica) {
        entityManager.persist(musica);
    }

    @Override
    public List<Musica> recuperarPorPlaylist(long playlistId) {
        return entityManager.createQuery("select m from Musica m where m.playlist.id = :playlistId", Musica.class)
                .setParameter("playlistId", playlistId)
                .getResultList();
    }

    @Override
    public Musica recuperarPorPlaylistIdEMusicaId(long playlistId, long musicaId) {
        return entityManager.createQuery("select m from Musica m where m.playlist.id = :playlistId and m.id = :musicaId", Musica.class)
                .setParameter("playlistId", playlistId)
                .setParameter("musicaId", musicaId)
                .getSingleResult();
    }

    @Override
    public void atualizar(Musica musica) {
        entityManager.merge(musica);
    }

    @Override
    public void excluir(long musicaId) {
        entityManager.remove(entityManager.getReference(Musica.class, musicaId));
    }
}
