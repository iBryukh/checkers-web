package builder.rest.converter;

import java.util.*;

/**
 * Created by oleh_kurpiak on 29.05.16.
 */
public abstract class Converter<T> {

    public abstract Map<String, Object> convert(T o, Set<String> fields);

    public List<Map<String, Object>> convert(List<T> objects, Set<String> fields){
        List<Map<String, Object>> result = new ArrayList<>();

        for(T t : objects)
            result.add(convert(t, fields));

        return result;
    }
}
