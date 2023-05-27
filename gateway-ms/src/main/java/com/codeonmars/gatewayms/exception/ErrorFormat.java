package com.codeonmars.gatewayms.exception;

import lombok.Data;

@Data
public class ErrorFormat {

    private String message;
    private String desc;
    private String suggestion;
}