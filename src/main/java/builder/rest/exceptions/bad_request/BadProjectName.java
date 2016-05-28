package builder.rest.exceptions.bad_request;

import builder.rest.exceptions.BadRequestException;

/**
 * Created by oleh_kurpiak on 27.05.16.
 */
public class BadProjectName extends BadRequestException {

    public BadProjectName(String name) {
        super(String.format(MESSAGE, name));
    }

    private static final String MESSAGE = "Project with such name '%s' does not exists";
}
