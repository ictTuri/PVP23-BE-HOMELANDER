package com.codeonmars.gatewayms.config.jwt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "app.jwt")
public class JwtConfig {

	private String secretKey;
	private String tokenPrefix;
	private Integer tokenExpirationAfterDays;
}
