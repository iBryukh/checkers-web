package builder.rest.logic_layers.rest;

import java.util.*;

/**
 * Created by oleh_kurpiak on 29.05.16.
 */
public interface UserRestService {

    Map<String, Object> getUser(int id, String fields);

    List<Map<String, Object>> getUsers(int limit, int offset, String fields);

}
