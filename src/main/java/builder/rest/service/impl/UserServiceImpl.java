package builder.rest.service.impl;

import builder.rest.dao.UserDao;
import builder.rest.domain.entities.UserEntity;
import builder.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by oleh_kurpiak on 29.05.16.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao dao;

    @Override
    @Transactional
    public UserEntity load(int id) {
        UserEntity user = dao.getById(id);
        return user;
    }
}
