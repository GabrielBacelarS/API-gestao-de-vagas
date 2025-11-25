package br.estudante.com.api_gestao_vagas.helprs.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtProvider {

    @Value("${security.token.secret}")
    private String secretKey;

    public String verifyToken(String token) {

        token = token.replace("Bearer ", "");
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        try {
            var subject = JWT.require(algorithm)
                    .build()
                    .verify(token)
                    .getSubject();
            return subject;
        } catch (JWTVerificationException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public String extractRoles(String token) {
        token = token.replace("Bearer ", "");
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        try {
            var roles = JWT.require(algorithm)
                    .build()
                    .verify(token)
                    .getClaim("roles")
                    .asString();
            return roles;
        } catch (JWTVerificationException ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
