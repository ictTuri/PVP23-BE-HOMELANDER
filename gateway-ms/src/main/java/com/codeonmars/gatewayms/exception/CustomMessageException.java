package com.codeonmars.gatewayms.exception;

public class CustomMessageException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public CustomMessageException(String message) {
        super(message);
    }
}
