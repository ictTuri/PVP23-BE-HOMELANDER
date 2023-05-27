package com.codeonmars.commonsms.security;

import com.codeonmars.commonsms.exception.UserContextNotPresentException;

import java.util.Optional;

public class UserContextHolder {

    private static final ThreadLocal<UserContext> contextHolder = new ThreadLocal<>();

    public static Optional<UserContext> getContext(){ return Optional.ofNullable(contextHolder.get()); }

    public static UserContext getContextOrThrowException(){
        return Optional.ofNullable(contextHolder.get()).orElseThrow(UserContextNotPresentException::new);
    }

    public static void setContext(UserContext userContext){
        if (userContext != null){
            contextHolder.set(userContext);
        }
    }

    public static void clearContext(){ contextHolder.remove(); }
}
