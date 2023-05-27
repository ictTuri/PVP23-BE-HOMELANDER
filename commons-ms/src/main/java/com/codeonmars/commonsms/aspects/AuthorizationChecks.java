package com.codeonmars.commonsms.aspects;

import com.codeonmars.commonsms.exception.CanNotAddResources;
import com.codeonmars.commonsms.exception.NotSuperuserException;
import com.codeonmars.commonsms.security.UserContext;
import com.codeonmars.commonsms.security.UserContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class AuthorizationChecks {

    public static final String NOT_ENOUGH_PROPERTIES_SLOTS = "Not enough Properties slots";
    public static final String NOT_ENOUGH_TENANTS_SLOTS = "Not enough Tenants slots";

    public AuthorizationChecks(){}

    @Around("@annotation(IsSuperUser)")
    public Object checkUserIsSuperUser(ProceedingJoinPoint joinPoint) throws Throwable {
        UserContext userContext = UserContextHolder.getContextOrThrowException();

        if(!userContext.isSuperUser()){
            throw new NotSuperuserException();
        }
        return joinPoint.proceed();
    }

    @Around("@annotation(CanAddProperties)")
    public Object checkUserCanAddProperties(ProceedingJoinPoint joinPoint) throws Throwable {
        UserContext userContext = UserContextHolder.getContextOrThrowException();

        if(!userContext.canAddProperties()){
            throw new CanNotAddResources(NOT_ENOUGH_PROPERTIES_SLOTS);
        }
        return joinPoint.proceed();
    }

    @Around("@annotation(CanAddTenants)")
    public Object checkUserCanAddTenants(ProceedingJoinPoint joinPoint) throws Throwable {
        UserContext userContext = UserContextHolder.getContextOrThrowException();

        if(!userContext.canAddTenants()){
            throw new CanNotAddResources(NOT_ENOUGH_TENANTS_SLOTS);
        }
        return joinPoint.proceed();
    }
}
