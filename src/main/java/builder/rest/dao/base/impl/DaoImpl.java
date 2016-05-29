package builder.rest.dao.base.impl;

import builder.rest.dao.base.Dao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by oleh_kurpiak on 29.05.16.
 */

@Repository
public class DaoImpl implements Dao {

    @Override
    public <T> T getById(Class tClass, int id) {
        return (T)getEntityManager().find(tClass, id);
    }

    @Override
    public <T> T update(T t) {
        return getEntityManager().merge(t);
    }

    @Override
    public <T> List<T> get(Class tClass, int offset, int limit) {
        Query query = getEntityManager().createQuery(String.format("SELECT o FROM %s o", tClass.getName()));
        query.setFirstResult(offset);
        query.setMaxResults(limit);

        return query.getResultList();
    }

    private EntityManager getEntityManager(){
        if(entityManager == null)
            entityManager = entityManagerFactory.createEntityManager();
        return entityManager;
    }

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    private EntityManager entityManager;

}
