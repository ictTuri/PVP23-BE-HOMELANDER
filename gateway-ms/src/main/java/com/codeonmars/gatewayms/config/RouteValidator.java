package com.codeonmars.gatewayms.config;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator {

    public static final List<String> openApiEndpoints = List.of(
            "/users/auth/_login",
            "/properties/search",
            "/properties/details/",
            "/eureka",
            "/users/api-docs",
            "/properties/api-docs",
            "/issues/api-docs",
            "/notifications/api-docs",
            "/files/api-docs"
    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoints
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));
}
