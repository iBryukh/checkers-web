package builder.rest.logic_layers.rest.impl;

import builder.rest.logic_layers.rest.UserRestService;
import builder.rest.logic_layers.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by oleh_kurpiak on 29.05.16.
 */
@Service
public class UserRestServiceImpl implements UserRestService{

    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public Map<String, Object> getUser(int id, String fields) {
        return null;
    }

    @Override
    @Transactional
    public List<Map<String, Object>> getUsers(int limit, int offset, String fields) {
        return null;
    }
}
