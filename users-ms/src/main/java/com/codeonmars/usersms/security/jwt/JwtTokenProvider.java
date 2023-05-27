package com.codeonmars.usersms.security.jwt;

import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.LocalDate;
import java.util.Date;

@Component
public record JwtTokenProvider(JwtConfig jwtConfig,
							   SecretKey secretKey) {

	public String createToken(Authentication authentication) {

		return Jwts.builder()
				.setSubject(authentication.getName())
				.claim("authorities", authentication.getAuthorities())
				.setIssuedAt(new Date())
				.setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(jwtConfig.getTokenExpirationAfterDays())))
				.signWith(secretKey).compact();
	}
}
