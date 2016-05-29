package builder.rest.dao.base;

/**
 * Created by oleh_kurpiak on 29.05.16.
 */
public interface Dao {

    <T> T getById(Class tClass, int id);

    <T> T update(T t);

}
