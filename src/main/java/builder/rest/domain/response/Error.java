package builder.rest.domain.response;

/**
 * Created by oleh_kurpiak on 26.05.16.
 */
public class Error {

    private String message;

    private int code;

    public Error(){

    }

    public Error(String message, int code){
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
