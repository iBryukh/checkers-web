package builder.rest.dao.base;

import java.util.List;

/**
 * Created by oleh_kurpiak on 29.05.16.
 */
public interface Dao {

    <T> T getById(Class tClass, int id);

    <T> T update(T t);

    <T> List<T> get(Class<T> tClass, int offset, int limit);

}
