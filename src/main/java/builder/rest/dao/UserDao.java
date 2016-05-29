package builder.rest.dao;

import builder.rest.domain.entities.UserEntity;

/**
 * Created by oleh_kurpiak on 29.05.16.
 */
public interface UserDao {

    UserEntity getById(int id);

}
