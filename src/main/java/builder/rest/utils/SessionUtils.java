package builder.rest.utils;

import builder.rest.domain.enums.Role;
import builder.rest.exceptions.AuthorizationException;
import builder.rest.utils.token.JwtUtil;
import builder.rest.utils.token.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by oleh_kurpiak on 31.05.16.
 */
@Component
public class SessionUtils {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private JwtUtil jwtUtil;

    public User getUserByToken(){
        if(user == null){
            parseToken();
        }
        return user;
    }

    private void parseToken(){
        String token = request.getParameter(JwtUtil.ACCESS_TOKEN);
        if(token != null && !token.isEmpty()){
            user = jwtUtil.parse(token);
        }
    }

    public void requireRoles(Role... roles) throws AuthorizationException {
        boolean has = false;
        if(getUserByToken() != null){
            for(Role role : roles){
                if(getUserByToken().getRole() == role){
                    has = true;
                    break;
                }
            }
        }
        if(!has)
            throw new AuthorizationException();
    }

    public void authorizationRequired() throws AuthorizationException {
        if(getUserByToken() == null)
            throw new AuthorizationException();
    }

    public boolean theSame(int id){
        return getUserByToken() != null && getUserByToken().getId() == id;
    }

    public boolean hasRole(Role... roles){
        if(getUserByToken() != null)
            for(Role role : roles)
                if(getUserByToken().getRole() == role)
                    return true;
        return false;
    }

    private User user;
}
