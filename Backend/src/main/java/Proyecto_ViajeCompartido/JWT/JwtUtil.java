package Proyecto_ViajeCompartido.JWT;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtil {

    private static String SECRET_KEY ="CompartirViajesSiempreEsMasFacil";
    private static Algorithm ALGORITHM = Algorithm.HMAC256(SECRET_KEY);


    public String create(String username, String role) {
        return JWT.create()
                .withSubject(username) // Aquí almacenas el username en lugar del rol
                .withClaim("role", role) // Guardas el rol en un claim separado
                .withIssuer("companiaViajesCompartidos")
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(15)))
                .sign(ALGORITHM);
    }

    //verificamos si el token es valido o no
    public boolean isValid(String jwt){
        try{JWT.require(ALGORITHM)
                .build()
                .verify(jwt);
            return true;
        }
        catch (JWTVerificationException e) {
            return false;
        }
    }

    //verifica a que usuario pertenece un jwt
    public String getUsername(String jwt){
        return JWT.require(ALGORITHM)
                .build()
                .verify(jwt)
                .getSubject();
        //el subject porque cuando creamos el token en el subject pusimos el username
    }
}
