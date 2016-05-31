package builder.rest.exceptions;

/**
 * Created by oleh_kurpiak on 31.05.16.
 */
public interface RestException {

    int getCode();
    String getMessage();

}
