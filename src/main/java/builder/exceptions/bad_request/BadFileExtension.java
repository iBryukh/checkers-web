package builder.exceptions.bad_request;

import builder.exceptions.BadRequestException;

/**
 * Created by oleh_kurpiak on 27.05.16.
 */
public class BadFileExtension extends BadRequestException {

    public BadFileExtension(String extensions) {
        super(String.format(MESSAGE, extensions));
    }


    private static final String MESSAGE = "Your file extension must be %s";
}
