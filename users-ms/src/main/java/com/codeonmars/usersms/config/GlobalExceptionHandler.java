package com.codeonmars.usersms.config;

import com.codeonmars.commonsms.exception.HLAuthorizationException;
import com.codeonmars.usersms.exception.CustomMessageException;
import com.codeonmars.usersms.exception.DataFilteredNotFoundException;
import com.codeonmars.usersms.exception.DataIdNotFoundException;
import com.codeonmars.usersms.exception.ErrorFormat;
import com.codeonmars.usersms.exception.UserNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@Component
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
            errors.put("error", "Validation Error");
        });
        return errors;
    }

    @ExceptionHandler(value = { CustomMessageException.class, BadCredentialsException.class, HLAuthorizationException.class })
    protected ResponseEntity<Object> handleCustomExceptions(RuntimeException ex, WebRequest request) {
        var errorBody = new ErrorFormat();
        errorBody.setMessage(ex.getMessage());
        errorBody.setDesc(request.getDescription(false));
        errorBody.setSuggestion("Contact Admin");

        return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { DataFilteredNotFoundException.class, DataIdNotFoundException.class, UserNotFoundException.class })
    protected ResponseEntity<Object> handleNotFoundExceptions(RuntimeException ex, WebRequest request) {
        var errorBody = new ErrorFormat();
        errorBody.setMessage(ex.getMessage());
        errorBody.setDesc(request.getDescription(false));
        errorBody.setSuggestion("Contact Admin");

        return new ResponseEntity<>(errorBody, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = { ConstraintViolationException.class})
    protected ResponseEntity<Object> handleValidationConstraint(RuntimeException ex, WebRequest request){
        var errorBody = new ErrorFormat();
        errorBody.setMessage(ex.getMessage());
        errorBody.setDesc(request.getDescription(false));
        errorBody.setSuggestion("Contact Admin");

        return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
    }

}