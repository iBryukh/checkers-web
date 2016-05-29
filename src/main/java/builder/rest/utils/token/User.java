package builder.rest.utils.token;

import builder.rest.domain.enums.Role;

/**
 * Created by oleh_kurpiak on 29.05.16.
 */
public class User {

    private int id;

    private String email;

    private Role role;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}