package com.codeonmars.usersms.exception;

import java.io.Serial;

public class CustomMessageException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public CustomMessageException(String message) {
        super(message);
    }
}
