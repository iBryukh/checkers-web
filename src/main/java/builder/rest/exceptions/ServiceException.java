package builder.rest.exceptions;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by oleh_kurpiak on 26.05.16.
 */
public class ServiceException extends Exception implements RestException {

    private final int code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;

    public ServiceException(String message){
        super(message);
    }

    public int getCode() {
        return code;
    }
}
