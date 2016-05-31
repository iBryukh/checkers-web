package builder.rest.exceptions.bad_request;

import builder.rest.exceptions.BadRequestException;

/**
 * Created by oleh_kurpiak on 29.05.16.
 */
public class WrongEmailFormatException extends BadRequestException {

    public WrongEmailFormatException() {
        super(MESSAGE);
    }

    private static final String MESSAGE = "This email has incorrect format";
}
