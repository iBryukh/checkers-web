package builder.rest.utils.token;

import builder.rest.domain.Fields;
import builder.rest.domain.entities.UserEntity;
import builder.rest.domain.enums.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

/**
 * Created by oleh_kurpiak on 29.05.16.
 */
@Component
public class JwtUtil {

    private String secret = "builder";

    public User parse(String token){
        if(token == null)
            return null;
        try {
            Claims body = Jwts
                    .parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
            User user = new User();
            user.setId(Integer.valueOf(body.get(Fields.ID).toString()));
            user.setEmail(body.get(Fields.User.EMAIL).toString());
            user.setRole(Role.valueOf(body.get(Fields.User.ROLE).toString()));

            return user;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public String generate(UserEntity user){
        Claims claims = Jwts.claims().setSubject(user.getEmail());
        claims.put(Fields.ID, user.getId()+"");
        claims.put(Fields.User.EMAIL, user.getEmail());
        claims.put(Fields.User.ROLE, user.getRole());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public static final String ACCESS_TOKEN = "access_token";
}
