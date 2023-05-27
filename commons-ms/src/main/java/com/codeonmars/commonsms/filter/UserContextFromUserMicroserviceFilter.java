package com.codeonmars.commonsms.filter;

import com.codeonmars.commonsms.security.UserContextHolder;
import com.codeonmars.commonsms.security.UserContextRetriever;
import com.codeonmars.commonsms.utils.Constants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class UserContextFromUserMicroserviceFilter extends OncePerRequestFilter {

    private final UserContextRetriever userContextRetriever;

    public UserContextFromUserMicroserviceFilter(UserContextRetriever userContextRetriever) {
        this.userContextRetriever = userContextRetriever;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String credential = request.getHeader(Constants.X_HL_CONTEXT_CREDENTIAL);
        if(credential != null){
            userContextRetriever.retrieveUserContext(credential).ifPresent(UserContextHolder::setContext);
        }
        filterChain.doFilter(request, response);
        UserContextHolder.clearContext();
    }
}
