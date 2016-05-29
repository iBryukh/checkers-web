package builder.rest.dao.base.impl;

import builder.rest.dao.base.Dao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by oleh_kurpiak on 29.05.16.
 */

@Repository
public class DaoImpl implements Dao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public <T> T getById(Class tClass, int id) {
        return (T)entityManager.find(tClass, id);
    }

    @Override
    public <T> T update(T t) {
        return entityManager.merge(t);
    }

    @Override
    public <T> List<T> get(Class tClass, int offset, int limit) {
        Query query = entityManager.createQuery(String.format("SELECT o FROM %s o", tClass.getName()));
        query.setFirstResult(offset);
        query.setMaxResults(limit);

        return query.getResultList();
    }
}
