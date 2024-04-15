package com.rumor.kitchen.users.domain;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;

@Component
public class Jwt {

    private final String secretKey;

    public Jwt(@Value("${jwt.secretKey}") String secretKey) {
        this.secretKey = secretKey;
    }

    public String createToken(User user) {
        Claims claims = Jwts.claims()
                .setSubject(user.getId().toString());

        claims.put("name", user.getName());
        claims.put("company", "rumor-lab");

        Date now = new Date();

        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + 3600000))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
        return token;
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return !claimsJws.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public String getAuthenticationSubject(String token) throws IllegalAccessException {
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return claimsJws.getBody().getSubject();
        } catch (Exception e) {
            throw new IllegalAccessException("JWT TOKEN CHECK.");
        }
    }

    public String extract(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
            return bearerToken.substring(6);
        }
        return null;
    }
}
