package builder.rest.logic_layers.services;

import builder.rest.domain.entities.UserEntity;
import builder.rest.exceptions.BadRequestException;

import java.util.List;

/**
 * Created by oleh_kurpiak on 29.05.16.
 */
public interface UserService {

    UserEntity load(int id) throws BadRequestException;

    List<UserEntity> get(int offset, int limit) throws BadRequestException;

}