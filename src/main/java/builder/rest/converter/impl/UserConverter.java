package builder.rest.converter.impl;

import builder.rest.converter.Converter;
import builder.rest.domain.entities.UserEntity;
import builder.rest.domain.enums.Role;
import builder.rest.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static builder.rest.domain.Fields.User.*;
import static builder.rest.domain.Fields.*;

/**
 * Created by oleh_kurpiak on 29.05.16.
 */
@Component
public class UserConverter extends Converter<UserEntity> {

    @Autowired
    private SessionUtils sessionUtils;

    @Override
    public Map<String, Object> convert(UserEntity o, Set<String> fields) {
        Map<String, Object> map = new HashMap<>();

        if(fields.contains(ID))
            map.put(ID, o.getId());
        if(fields.contains(EMAIL))
            map.put(EMAIL, o.getEmail());
        if(fields.contains(PASSWORD)) {
            if(sessionUtils.hasRole(Role.admin) || sessionUtils.theSame(o.getId()))
                map.put(PASSWORD, o.getPassword());
            else
                map.put(PASSWORD, null);
        }
        if(fields.contains(ROLE)){
            map.put(ROLE, o.getRole());
        }
        if(fields.contains(ORGANIZATION)){
            Integer id = null;
            if(o.getOrganization() != null)
                id = o.getOrganization().getId();
            map.put(ORGANIZATION, id);
        }

        return map;
    }

}
