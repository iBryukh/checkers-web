package builder.rest.dao.impl;

import builder.rest.dao.UserDao;
import builder.rest.dao.base.Dao;
import builder.rest.domain.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by oleh_kurpiak on 29.05.16.
 */
@Service
public class UserDaoImpl implements UserDao {

    @Autowired
    private Dao dao;

    @Override
    public UserEntity getById(int id) {
        return dao.getById(UserEntity.class, id);
    }

    @Override
    public UserEntity update(UserEntity user) {
        return dao.update(user);
    }
}
