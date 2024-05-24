package com.sahil.fileupload.security.jwt;

import com.sahil.fileupload.entities.UserEntity;
import com.sahil.fileupload.security.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import java.util.function.Function;
import javax.crypto.SecretKey;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/** Jwtgenerator */
@Service
public class JwtService {

    private final String SECRET_KEY = "a647f060250f5ab787964f33ccf786ba641fed0b255495d7d20808f58242a7d9";

    public String jwtGenerate(UserEntity authentication) {
        String username = authentication.getUsername();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + SecurityConstants.JWT_EXPIRATION);
        String jwtToken = Jwts.builder()
                .subject(username)
                .issuedAt(currentDate)
                .expiration(expireDate)
                .signWith(getSigninKey())
                .compact();
        return jwtToken;
    }

    private SecretKey getSigninKey() {
        byte[] keys = Decoders.BASE64URL.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keys);
    }

    public <T> T extractClaims(String jwtToken, Function<Claims, T> extracting) {
        Claims claims = extractAllClaim(jwtToken);
        return extracting.apply(claims);
    }

    private Claims extractAllClaim(String jwtToken) {
        return Jwts.parser()
                .verifyWith(getSigninKey())
                .build()
                .parseSignedClaims(jwtToken)
                .getPayload();
    }

    public boolean verifyToken(String token, UserDetails user) {
        return extractUsername(token).equals(user.getUsername()) && !isTokenExpired(token);
    }

    public String extractUsername(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    private boolean isTokenExpired(String token) {
        return extractClaims(token, Claims::getExpiration)
                .before(new Date());
    }
}
