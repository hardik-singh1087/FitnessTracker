package com.hardiksingh.fitnessTracker.security;

import com.hardiksingh.fitnessTracker.enums.Role;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.List;

@Component
public class JwtUtils {
    // In a real app, move this to application.properties
    private final String jwtSecret = "yourSecretKeyMustBeAtLeast32CharactersLongasdfasdf";
    private final int jwtExpirationMs = 86400000 * 2; // 24 hours

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

    // 2. Extract Username
//    public String getUsernameFromToken(String token) {
//        return Jwts.parserBuilder()
//                .setSigningKey(getSigningKey())
//                .build()
//                .parseClaimsJws(token)
//                .getBody()
//                .getSubject();
//    }

    // 3. Validate Token
//    public boolean validateJwtToken(String authToken) {
//        try {
//            Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(authToken);
//            return true;
//        } catch (MalformedJwtException e) {
//            System.out.println("Invalid JWT token: " + e.getMessage());
//        } catch (ExpiredJwtException e) {
//            System.out.println("JWT token is expired: " + e.getMessage());
//        } catch (UnsupportedJwtException e) {
//            System.out.println("JWT token is unsupported: " + e.getMessage());
//        } catch (IllegalArgumentException e) {
//            System.out.println("JWT claims string is empty: " + e.getMessage());
//        }
//        return false;
//    }
}
