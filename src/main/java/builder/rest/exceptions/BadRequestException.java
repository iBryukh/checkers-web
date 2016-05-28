package builder.rest.exceptions;

/**
 * Created by oleh_kurpiak on 26.05.16.
 */
public class BadRequestException extends Exception {

    private final int code;

    public BadRequestException(String message){
        super(message);
        this.code = 400;
    }

    public int getCode() {
        return code;
    }
}
