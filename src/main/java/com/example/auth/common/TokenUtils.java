package com.example.auth.common;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;

@Component
@Getter
public class TokenUtils {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration_time}")
    private Long expirationTime;

    public String createJwt(Integer userId, List<String> roles) {

        Claims claims = Jwts.claims().setSubject(String.valueOf(userId));
        Date now = new Date();
        claims.setIssuedAt(now);
        claims.setExpiration(new Date(now.getTime() + expirationTime));
        claims.put("roles", String.join(",", roles));

        SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes());


        return Jwts.builder()
                .setClaims(claims)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

}
