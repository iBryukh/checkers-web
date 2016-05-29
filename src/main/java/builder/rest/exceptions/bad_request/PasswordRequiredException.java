package builder.rest.exceptions.bad_request;

import builder.rest.exceptions.BadRequestException;

/**
 * Created by oleh_kurpiak on 29.05.16.
 */
public class PasswordRequiredException extends BadRequestException {

    public PasswordRequiredException() {
        super(MESSAGE);
    }

    private static final String MESSAGE = "Password is required";
}
