package com.codeonmars.commonsms.exception;

public class NotSuperuserException extends HLAuthorizationException{

    public static final String USER_IS_NOT_AN_SUPERUSER = "User is not an superuser";

    public NotSuperuserException(){
        super(USER_IS_NOT_AN_SUPERUSER);
    }
}
