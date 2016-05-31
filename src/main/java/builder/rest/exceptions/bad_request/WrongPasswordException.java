package builder.rest.exceptions.bad_request;

import builder.rest.exceptions.BadRequestException;

/**
 * Created by oleh_kurpiak on 29.05.16.
 */
public class WrongPasswordException extends BadRequestException {

    public WrongPasswordException() {
        super(MESSAGE);
    }

    private static final String MESSAGE = "You passed incorrect password";
}
