package builder.rest.logic_layers.services.impl;

import builder.rest.dao.UserDao;
import builder.rest.domain.entities.UserEntity;
import builder.rest.exceptions.BadRequestException;
import builder.rest.exceptions.bad_request.NoSuchEntityException;
import builder.rest.logic_layers.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by oleh_kurpiak on 29.05.16.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao dao;

    @Override
    public UserEntity load(int id) throws BadRequestException {
        UserEntity user = dao.getById(id);

        if(user == null)
            throw new NoSuchEntityException("user", String.format("id %d", id));

        return user;
    }

    @Override
    public List<UserEntity> get(int offset, int limit) throws BadRequestException {
        List<UserEntity> users = dao.get(offset, limit);

        if(users == null || users.isEmpty())
            throw new NoSuchEntityException("user[]", String.format("offset %d, limit %d", offset, limit));

        return users;
    }
}
