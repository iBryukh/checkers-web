package builder.rest.exceptions.bad_request;

import builder.rest.exceptions.BadRequestException;

/**
 * Created by oleh_kurpiak on 29.05.16.
 */
public class NoSuchEntityException extends BadRequestException {

    public NoSuchEntityException(String className, String params) {
        super(String.format(MESSAGE, className, params));
    }

    private static final String MESSAGE = "No such entity {%s} with params {%s}";
}
