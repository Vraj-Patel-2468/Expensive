package com.expensive.api.services;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    @Value("${security.jwt.secret-key}")
    private String jwtSecretKey;

    @Value("${security.jwt.expiration-time}")
    private long jwtExpirationTime;

    private SecretKey getKeyForJwt() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(long id, String username, String email) {
        return Jwts
            .builder()
            .subject(username)
            .claims()
            .add("email", email)
            .add("id", id)    
            .and()
            .issuedAt(new Date(System.currentTimeMillis()))
            .expiration(new Date(System.currentTimeMillis() + jwtExpirationTime))
            .signWith(getKeyForJwt())
            .compact();
    }

    private Claims extractClaims(String token) {
        return Jwts
            .parser()
            .verifyWith(getKeyForJwt())
            .build()
            .parseSignedClaims(token)
            .getPayload();
    }

    public Claims verifyTokenAndSendDetails(String token) {
        Claims claims = this.extractClaims(token);
        
        if(claims.getExpiration().before(new Date())) {
            throw new RuntimeException("Session Expired. Login again.");
        }

        return claims;
    }

}
