package builder.utils;

import builder.domain.response.Error;
import builder.domain.response.Response;
import org.springframework.stereotype.Service;

/**
 * Created by oleh_kurpiak on 26.05.16.
 */
@Service
public class ResponseBuilder {

    public <T> Response<T> get(T what){
        Response<T> r = new Response<T>();
        r.setResult(what);
        return r;
    }

    public Response error(String message, int code){
        Error e = new Error(message, code);
        Response r = new Response();
        r.setError(e);
        return r;
    }

}
