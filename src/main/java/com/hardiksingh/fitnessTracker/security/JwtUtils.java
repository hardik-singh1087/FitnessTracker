package com.hardiksingh.fitnessTracker.security;

import com.hardiksingh.fitnessTracker.enums.Role;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.List;

@Component
public class JwtUtils {
    private final String jwtSecret = "yourSecretKeyMustBeAtLeast32CharactersLongasdfasdf";
    private final int jwtExpirationMs = 86400000 * 2; // 48 hours

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    // 1. Generate Token
    public String generateToken(String userId, String role) {
        return Jwts.builder()
                .subject(userId)
                .claim("roles", List.of(new SimpleGrantedAuthority(role)))
                .issuedAt(new Date())
                .expiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(getSigningKey())
                .compact();
    }

    //     2. Extract Jwt Token From Header
    public String getJwtFromHeader(HttpServletRequest httpServletRequest) {
        String bearerToken = httpServletRequest.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    //     3. Extract UserId From JwtToken
    public String getUserIdFromJwtToken(String jwtToken) {
        return Jwts.parser().verifyWith((SecretKey) getSigningKey())
                .build()
                .parseSignedClaims(jwtToken)
                .getPayload()
                .getSubject();
    }

    // 6. Extract all the claims
    public Claims getAllClaims(String jwtToken) {

        return Jwts.parser().verifyWith((SecretKey) getSigningKey())
                .build()
                .parseSignedClaims(jwtToken)
                .getPayload();
    }

    // 5. Validate Token
    public boolean validateJwtToken(String jwt) {
        try {
            Jwts.parser().verifyWith((SecretKey) getSigningKey())
                    .build()
                    .parseSignedClaims(jwt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }


}
