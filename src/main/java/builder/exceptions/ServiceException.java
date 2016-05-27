package builder.exceptions;

/**
 * Created by oleh_kurpiak on 26.05.16.
 */
public class ServiceException extends Exception {

    private final int code;

    public ServiceException(String message){
        super(message);
        this.code = 500;
    }

    public int getCode() {
        return code;
    }
}
