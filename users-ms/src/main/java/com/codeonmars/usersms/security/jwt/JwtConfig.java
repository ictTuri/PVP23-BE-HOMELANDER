package com.codeonmars.usersms.security.jwt;

import com.google.common.net.HttpHeaders;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "app.jwt")
@Component
public class JwtConfig {

	private String secretKey;
	private String tokenPrefix;
	private Integer tokenExpirationAfterDays;
	
	public String getAuthorizationHeader() {
		return HttpHeaders.AUTHORIZATION;
	}
}
