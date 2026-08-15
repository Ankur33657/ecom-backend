
package com.example.ecom_backend.services.Auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    private static final String SECRET_KEY_STRING = "Ankur336@#225TSR$$KarinSasukeNarutoLuffyZoroSanjiNami336@#$%**WorkingTogetherWithStrawHatCREW";
    private static final long ACCESS_TOKEN_EXPIRATION = 15 * 60 * 1000; // 15 minutes
    private static final long REFRESH_TOKEN_EXPIRATION = 1000L * 60 * 60 * 24; // 24 hours

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY_STRING.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(String username, boolean isAccessToken) {
        long expiration = isAccessToken ? ACCESS_TOKEN_EXPIRATION : REFRESH_TOKEN_EXPIRATION;
        long now = System.currentTimeMillis();

        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date(now))
                .expiration(new Date(now + expiration))
                .signWith(getSigningKey())
                .compact();
    }

    public String getUsernameFromToken(String token) {
        return extractAllClaims(token).getSubject();
    }

    public boolean validateToken(String token) {
        try {
            extractAllClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
