package br.com.devmedia.dao;

import br.com.devmedia.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

public abstract class GenericDAO <T extends Serializable> {

    private Class<T> aClass;

    protected GenericDAO(Class<T> aClass) {

        this.aClass = aClass;
    }

    protected EntityManager getEntityManager() {

        return JPAUtil.getInstance().getEntityManager();
    }

    public Long count() {

        EntityManager manager = getEntityManager();

        manager.getTransaction().begin();

        Query query = manager.createQuery("select count(c) from " + aClass.getSimpleName() + " c");

        Long count = (Long) query.getSingleResult();

        manager.getTransaction().commit();
        manager.close();

        return count;
    }

    public T findOne(String jpql, Object... params) {

        EntityManager manager = getEntityManager();

        manager.getTransaction().begin();

        Query query = manager.createQuery(jpql);

        for (int i = 0; i < params.length; i++) {

            query.setParameter(i + 1, params[i]);
        }

        T entity = (T) query.getSingleResult();

        manager.getTransaction().commit();
        manager.close();

        return entity;
    }

    @SuppressWarnings("unchecked")
    public List<T> find(String jpql, Object... params) {

        EntityManager manager = getEntityManager();

        manager.getTransaction().begin();

        Query query = manager.createQuery(jpql);

        for (int i = 0; i < params.length; i++) {

            query.setParameter(i + 1, params[i]);
        }

        List<T> entities = query.getResultList();

        manager.getTransaction().commit();
        manager.close();

        return entities;
    }

    public T findById(Long id) {

        EntityManager manager = getEntityManager();

        manager.getTransaction().begin();

        T entity = (T) manager.find(aClass, id);

        manager.getTransaction().commit();
        manager.close();

        return entity;
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll() {

        EntityManager manager = getEntityManager();

        manager.getTransaction().begin();

        Query query = manager.createQuery("from " + aClass.getSimpleName());

        List<T> entities = query.getResultList();

        manager.getTransaction().commit();
        manager.close();

        return entities;
    }

    public void save(T entity) {

        EntityManager manager = getEntityManager();

        manager.getTransaction().begin();
        manager.persist(entity);
        manager.getTransaction().commit();
        manager.close();
    }

    public void update(T entity) {

        EntityManager manager = getEntityManager();

        manager.getTransaction().begin();
        manager.merge(entity);
        manager.getTransaction().commit();
        manager.close();
    }

    public void delete(Long id) {

        EntityManager manager = getEntityManager();

        manager.getTransaction().begin();
        //Usando Reference é mais rapido que Find, mas só retorna a referência, não o objeto
        manager.remove(manager.getReference(aClass, id));
        manager.getTransaction().commit();
        manager.close();
    }

    //Metodo para usar na exclusão OnCascade do telefone, não da pra usar o reference do outro delete pois ele não
    //usaria a mesma instancia de Person com o telefone deletado da List
    public void delete(T entity) {

        EntityManager manager = getEntityManager();

        manager.getTransaction().begin();
        manager.remove(manager.merge(entity));
        manager.getTransaction().commit();
        manager.close();
    }
}