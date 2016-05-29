package builder.rest.service;

import builder.rest.domain.entities.UserEntity;

/**
 * Created by oleh_kurpiak on 29.05.16.
 */
public interface UserService {

    UserEntity load(int id);

}
