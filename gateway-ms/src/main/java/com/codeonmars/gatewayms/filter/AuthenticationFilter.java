package com.codeonmars.gatewayms.filter;

import com.codeonmars.gatewayms.config.RouteValidator;
import com.codeonmars.gatewayms.config.jwt.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


@Component
@RefreshScope
public class AuthenticationFilter implements GatewayFilter {

    private static final String X_HL_CONTEXT_CREDENTIAL = "X-HL-context-credential";
    private final RouteValidator routeValidator;
    private final JwtTokenUtil jwtTokenUtil;

    public AuthenticationFilter(RouteValidator routeValidator, JwtTokenUtil jwtTokenUtil) {
        this.routeValidator = routeValidator;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        if (routeValidator.isSecured.test(request)) {
            if (this.isAuthMissing(request)){
                return this.onError(exchange, "Authorization header is missing in request");
            }
            final String token = this.getAuthHeader(request).replace("Bearer ", "");
            if (jwtTokenUtil.isInvalid(token)){
                return this.onError(exchange, "Authorization header is invalid");
            }
            this.populateRequestWithHeaders(exchange, token);
        }
        return chain.filter(exchange);
    }

    private Mono<Void> onError(ServerWebExchange exchange, String err) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        return response.setComplete();
    }

    private String getAuthHeader(ServerHttpRequest request) {
        return request.getHeaders().getOrEmpty("Authorization").get(0);
    }
    private boolean isAuthMissing(ServerHttpRequest request) {
        return !request.getHeaders().containsKey("Authorization");
    }
    private void populateRequestWithHeaders(ServerWebExchange exchange, String token) {
        Claims claims = jwtTokenUtil.getAllClaimsFromToken(token);
        exchange.getRequest().mutate()
                .header(X_HL_CONTEXT_CREDENTIAL, String.valueOf(claims.get("sub")))
                .build();
    }
}
