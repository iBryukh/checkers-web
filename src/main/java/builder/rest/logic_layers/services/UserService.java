package builder.rest.logic_layers.services;

import builder.rest.domain.entities.UserEntity;
import builder.rest.domain.request.CreateUserForm;
import builder.rest.exceptions.BadRequestException;

import java.util.*;

/**
 * Created by oleh_kurpiak on 29.05.16.
 */
public interface UserService {

    UserEntity load(int id) throws BadRequestException;

    List<UserEntity> get(int offset, int limit) throws BadRequestException;

    boolean registerUser(CreateUserForm form) throws BadRequestException;

    Map<String, Object> signIn(CreateUserForm form) throws BadRequestException;

}