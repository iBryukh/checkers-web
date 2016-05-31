package builder.rest.logic_layers.rest;

import builder.rest.domain.request.CreateUserForm;
import builder.rest.exceptions.BadRequestException;
import builder.rest.exceptions.bad_request.NoSuchEntityException;

import java.util.*;

/**
 * Created by oleh_kurpiak on 29.05.16.
 */
public interface UserRestService {

    Map<String, Object> getUser(int id, Set<String> fields) throws BadRequestException;

    List<Map<String, Object>> getUsers(int offset, int limit, Set<String> fields) throws BadRequestException;

    boolean registerUser(CreateUserForm form) throws BadRequestException;

    Map<String, Object> signIn(CreateUserForm form) throws BadRequestException;
}
