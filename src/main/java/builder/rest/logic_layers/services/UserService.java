package builder.rest.logic_layers.services;

import builder.rest.domain.entities.UserEntity;

/**
 * Created by oleh_kurpiak on 29.05.16.
 */
public interface UserService {

    UserEntity load(int id);

}