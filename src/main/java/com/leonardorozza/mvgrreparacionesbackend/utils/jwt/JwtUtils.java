package com.leonardorozza.mvgrreparacionesbackend.utils.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtUtils {

    @Value("${security.jwt.secret}")
    private String jwtSecret;

    @Value("${security.jwt.expiration}")
    private long jwtExpirationMs;

    @Value("${security.jwt.issuer}")
    private String issuer;

    /**
     * Genera un token JWT firmado.
     */
    public String generateToken(UserDetails userDetails) {
        Algorithm algorithm = Algorithm.HMAC256(jwtSecret);

        return JWT.create()
                .withIssuer(issuer)
                .withSubject(userDetails.getUsername())
                .withClaim("role", userDetails.getAuthorities().iterator().next().getAuthority())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .sign(algorithm);
    }

    /**
     * Obtiene el username desde el token.
     */
    public String extractUsername(String token) {
        return decodeToken(token).getSubject();
    }

    /**
     * Valida token: firma + expiración + issuer.
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        try {
            String username = extractUsername(token);
            return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
        } catch (Exception ex) {
            return false;
        }
    }

    private boolean isTokenExpired(String token) {
        return decodeToken(token).getExpiresAt().before(new Date());
    }

    /**
     * Decodifica y verifica JWT.
     */
    private DecodedJWT decodeToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
            return JWT.require(algorithm)
                    .withIssuer(issuer)
                    .build()
                    .verify(token);
        } catch (JWTVerificationException ex) {
            throw new RuntimeException("Token inválido: " + ex.getMessage());
        }
    }
}
