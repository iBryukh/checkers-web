package builder.rest.domain.request;

/**
 * Created by oleh_kurpiak on 29.05.16.
 */
public class CreateUserForm {

    private String email;

    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
