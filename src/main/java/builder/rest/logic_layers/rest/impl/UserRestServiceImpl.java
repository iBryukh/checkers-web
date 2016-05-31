package builder.rest.logic_layers.rest.impl;

import builder.rest.converter.Converter;
import builder.rest.domain.entities.UserEntity;
import builder.rest.domain.request.CreateUserForm;
import builder.rest.exceptions.BadRequestException;
import builder.rest.exceptions.bad_request.NoSuchEntityException;
import builder.rest.logic_layers.rest.UserRestService;
import builder.rest.logic_layers.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by oleh_kurpiak on 29.05.16.
 */
@Service
public class UserRestServiceImpl implements UserRestService{

    @Autowired
    private UserService userService;

    @Autowired
    private Converter<UserEntity> userConverter;

    @Override
    @Transactional
    public Map<String, Object> getUser(int id, Set<String> fields) throws BadRequestException {
        UserEntity user = userService.load(id);

        return userConverter.convert(user, fields);
    }

    @Override
    @Transactional
    public List<Map<String, Object>> getUsers(int offset, int limit, Set<String> fields) throws BadRequestException {
        List<UserEntity> users = userService.get(offset, limit);

        return userConverter.convert(users, fields);
    }

    @Override
    @Transactional
    public boolean registerUser(CreateUserForm form) throws BadRequestException {
        return userService.registerUser(form);
    }

    @Override
    @Transactional
    public Map<String, Object> signIn(CreateUserForm form) throws BadRequestException {
        return userService.signIn(form);
    }
}
