package com.codeonmars.propertiesms.config;

import com.codeonmars.commonsms.security.UserContext;
import com.codeonmars.commonsms.security.UserContextHolder;
import com.codeonmars.commonsms.utils.Constants;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;

public class UserHeadersInterceptor implements RequestInterceptor {


    @Override
    public void apply(RequestTemplate template) {
        getCredentialHeader().ifPresent(h -> template.header(Constants.X_HL_CONTEXT_CREDENTIAL, h));
    }

    private Optional<String> getCredentialHeader(){
        if(RequestContextHolder.getRequestAttributes() == null){
            return UserContextHolder.getContext().map(UserContext::getUsername);
        }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        return Optional.ofNullable(request.getHeader(Constants.X_HL_CONTEXT_CREDENTIAL));
    }

}
