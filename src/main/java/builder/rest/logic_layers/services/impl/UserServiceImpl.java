package builder.rest.logic_layers.services.impl;

import builder.rest.dao.UserDao;
import builder.rest.domain.Fields;
import builder.rest.domain.entities.UserEntity;
import builder.rest.domain.enums.Role;
import builder.rest.domain.request.CreateUserForm;
import builder.rest.exceptions.BadRequestException;
import builder.rest.exceptions.bad_request.NoSuchEntityException;
import builder.rest.exceptions.bad_request.PasswordRequiredException;
import builder.rest.exceptions.bad_request.WrongEmailFormatException;
import builder.rest.exceptions.bad_request.WrongPasswordException;
import builder.rest.logic_layers.services.UserService;
import builder.rest.logic_layers.services.ValidationService;
import builder.rest.utils.token.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by oleh_kurpiak on 29.05.16.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao dao;

    @Autowired
    private ValidationService validationService;

    @Autowired
    private Md5PasswordEncoder md5PasswordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

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

    @Override
    public boolean registerUser(CreateUserForm form) throws BadRequestException {
        if(!validationService.isValidEmail(form.getEmail()))
            throw new WrongEmailFormatException();
        // todo: check if email exists

        String password = form.getPassword();
        if(password == null || password.isEmpty())
            throw new PasswordRequiredException();

        UserEntity newUser = new UserEntity();
        newUser.setEmail(form.getEmail());
        newUser.setPassword(md5PasswordEncoder.encodePassword(password, null));
        newUser.setRole(Role.organization_owner);

        newUser = dao.update(newUser);

        return newUser != null;
    }

    @Override
    public Map<String, Object> signIn(CreateUserForm form) throws BadRequestException {
        UserEntity user = dao.getById(1);
        // todo: get by email
        if(user == null)
            throw new NoSuchEntityException("user", String.format("email %s", form.getEmail()));
        if(!user.getPassword().equals(form.getPassword()))
            throw new WrongPasswordException();

        Map<String, Object> map = new HashMap<>();
        map.put(Fields.User.ROLE, user.getRole());
        map.put(JwtUtil.ACCESS_TOKEN, jwtUtil.generate(user));
        return map;
    }
}
