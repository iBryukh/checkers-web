package builder.rest.exceptions;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by oleh_kurpiak on 31.05.16.
 */
public class AuthorizationException extends Exception implements RestException {

    private final int code = HttpServletResponse.SC_UNAUTHORIZED;

    public AuthorizationException(){
        super(MESSAGE);
    }

    public int getCode() {
        return code;
    }

    private static final String MESSAGE = "This method require authorization";

}
