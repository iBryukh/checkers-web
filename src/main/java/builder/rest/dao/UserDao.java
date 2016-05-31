package builder.rest.dao;

import builder.rest.domain.entities.UserEntity;

import java.util.List;

/**
 * Created by oleh_kurpiak on 29.05.16.
 */
public interface UserDao {

    UserEntity getById(int id);

    UserEntity update(UserEntity user);

    List<UserEntity> get(int offset, int limit);

}
