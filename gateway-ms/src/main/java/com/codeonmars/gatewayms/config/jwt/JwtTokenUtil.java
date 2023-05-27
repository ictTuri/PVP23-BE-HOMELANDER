package com.codeonmars.gatewayms.config.jwt;

import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenUtil {

    private final SecretKey secretKey;

    public JwtTokenUtil(SecretKey secretKey) {
        this.secretKey = secretKey;
    }

    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
    }
    private boolean isTokenExpired(String token) {
        return this.getAllClaimsFromToken(token).getExpiration().before(new Date());
    }
    public boolean isInvalid(String token) {
        return this.isTokenExpired(token);
    }

}
