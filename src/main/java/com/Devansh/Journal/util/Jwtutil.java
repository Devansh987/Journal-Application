package com.Devansh.Journal.util;

import com.sun.org.apache.xpath.internal.operations.Bool;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Component
public class Jwtutil {
    @Value("${jwt.secret}")
    private String SECRET_KEY;

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String generateToken(String userName){
        Map<String,Object> map = new HashMap<>();
        return createToken(map,userName);
    }

    public Claims getAllClaims(String token){
            return Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        }

        public String extractUserName(String token){
        return getAllClaims(token).getSubject();
        }

        public  Date expitartionDate(String token){
        return getAllClaims(token).getExpiration();
        }

        private Boolean isTokenExpired(String token){
        return expitartionDate(token).before(new Date());
        }

        public  boolean validateToken(String token){
            return !isTokenExpired(token);
        }


    private String createToken(Map<String, Object> claims, String username) {

        return Jwts.builder()
                .claims(claims)
                .subject(username)
                .header().empty().add("typ","jwt")
                .and()
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour
                .signWith(getSigningKey())   // your key
                .compact();
    }

}
