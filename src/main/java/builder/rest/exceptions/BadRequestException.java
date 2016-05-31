package builder.rest.exceptions;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by oleh_kurpiak on 26.05.16.
 */
public class BadRequestException extends Exception implements RestException {

    private final int code = HttpServletResponse.SC_BAD_REQUEST;

    public BadRequestException(String message){
        super(message);
    }

    public int getCode() {
        return code;
    }
}
