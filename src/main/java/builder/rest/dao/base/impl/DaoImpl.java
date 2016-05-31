package builder.rest.dao.base.impl;

import builder.rest.dao.base.Dao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
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
    public <T> List<T> get(Class<T> tClass, int offset, int limit) {
        TypedQuery<T> query = buildQueryFromCriteria(tClass);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.getResultList();
    }

    private <T> TypedQuery<T> buildQueryFromCriteria(Class<T> tClass){
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = builder.createQuery(tClass);
        Root<T> root = criteriaQuery.from(tClass);
        criteriaQuery.select(root);



        return entityManager.createQuery(criteriaQuery);
    }
}
