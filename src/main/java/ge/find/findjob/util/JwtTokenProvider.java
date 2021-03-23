package ge.find.findjob.util;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    private static String secret = "test";
    private static long activationTokenInMilliseconds = 86400000;

    public static String createToken(String username) {
        Claims claims = Jwts.claims().setSubject(username);

        Date now = new Date();
        Date validity = new Date(now.getTime() + activationTokenInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public static String getUsername(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public static boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Value("${jwt.token.secret}")
    public void setSecret(String secret) {
        JwtTokenProvider.secret = secret;
    }

    ;

    @Value("${jwt.token.activation.expired}")
    public void setActivationTokenInMilliseconds(long activationTokenInMilliseconds) {
        JwtTokenProvider.activationTokenInMilliseconds = activationTokenInMilliseconds;
    }
}
