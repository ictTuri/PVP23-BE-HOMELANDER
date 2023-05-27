package com.codeonmars.commonsms.exception;

public class HLAuthorizationException extends RuntimeException{

    public HLAuthorizationException(String s){ super(s); }

    public HLAuthorizationException(String s, Throwable throwable){
        super(s, throwable);
    }
}
