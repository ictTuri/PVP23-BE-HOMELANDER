package com.codeonmars.usersms.config;

import com.codeonmars.commonsms.aspects.AuthorizationChecks;
import com.codeonmars.commonsms.filter.RequestingUserExtractor;
import com.codeonmars.commonsms.filter.UserContextFromUserMicroserviceFilter;
import com.codeonmars.commonsms.security.UserContextRetriever;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Predicate;
import java.util.regex.Pattern;


@Configuration
public class UserContextFilterConfig {

    @Autowired
    private UserContextRetriever userContextRetriever;

    public static class AllowDetailUserExtractorFilter extends RequestingUserExtractor {

        protected boolean shouldContinue(HttpServletRequest request){
            boolean original = super.shouldContinue(request);
//            Predicate<String> userDetailsAPI = Pattern.compile("/users/context/(\\d|\\w|\\.|\\$\\(\\))*$").asPredicate();
            Predicate<String> apiDocs = Pattern.compile("/users/api-docs").asPredicate();
            Predicate<String> apiLogin = Pattern.compile("/users/auth/_login").asPredicate();
            Predicate<String> apiRegister = Pattern.compile("/users/auth/register").asPredicate();
            String requestURI = request.getRequestURI();
            return original /*|| userDetailsAPI.test(requestURI)*/ || apiDocs.test(requestURI) || apiLogin.test(requestURI) || apiRegister.test(requestURI);
        }
    }

    @Bean
    public RequestingUserExtractor credentialCheck() {
        return new AllowDetailUserExtractorFilter();
    }

    @Bean
    public UserContextFromUserMicroserviceFilter userContextFilter(){
        return new UserContextFromUserMicroserviceFilter(userContextRetriever);
    }

    @Bean
    public AuthorizationChecks authorizationChecks(){
        return new AuthorizationChecks();
    }
}
