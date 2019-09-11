package br.com.devmedia.curso.dao;

import br.com.devmedia.curso.domain.TipoSexo;
import br.com.devmedia.curso.domain.Usuario;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional  //Padrão do Transactional é readOnly false, vale pra todos os metodos da classe quando anotado aqui
public class UsuarioDaoImpl implements UsuarioDao {

    @PersistenceContext     //Com essa anotação já é trazido um entityManagerFactory da classe SpringJpaConfig, pela injeção de dependencias do spring
    private EntityManager entityManager;

    @Override
    public void salvar(Usuario usuario) {
        entityManager.persist(usuario);     //Com SpringJPA basta o persist, não precisa do begin, commit e close da JPA
    }

    @Override
    public void editar(Usuario usuario) {
        entityManager.merge(usuario);
    }

    @Override
    public void excluir(Long id) {
        entityManager.remove(entityManager.getReference(Usuario.class, id));
    }

    @Transactional(readOnly = true)
    @Override
    public Usuario getId(Long id) {
        String jpql = "from Usuario u where u.id = :id";
        TypedQuery<Usuario> query = entityManager.createQuery(jpql, Usuario.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Transactional(readOnly = true)     //Sobrescreve o Transactional da classe
    @Override
    public List<Usuario> getTodos() {
        String jpql = "from Usuario u";
        TypedQuery<Usuario> query = entityManager.createQuery(jpql, Usuario.class);
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Usuario> getBySexo(TipoSexo sexo) {
        String jpql = "from Usuario u where u.sexo = :sexo";
        TypedQuery<Usuario> query = entityManager.createQuery(jpql, Usuario.class);
        query.setParameter("sexo", sexo);
        return query.getResultList();
    }

    @Override
    public List<Usuario> getByNome(String nome) {
        String jpql = "from Usuario u where u.nome like :nome or u.sobrenome like :sobrenome";
        TypedQuery<Usuario> query = entityManager.createQuery(jpql, Usuario.class);
        query.setParameter("nome", "%" + nome + "%");
        query.setParameter("sobrenome", "%" + nome + "%");
        return query.getResultList();
    }
}
