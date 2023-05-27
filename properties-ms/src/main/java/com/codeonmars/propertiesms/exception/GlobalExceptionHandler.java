package com.codeonmars.propertiesms.exception;

import com.codeonmars.commonsms.exception.CanNotAddResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @ExceptionHandler(value = { CanNotAddResources.class })
    protected ResponseEntity<Object> handleJWT(RuntimeException ex, WebRequest request) {
        var errorBody = new ErrorFormat();
        errorBody.setMessage(ex.getMessage());
        errorBody.setDesc(request.getDescription(false));
        errorBody.setSuggestion("Upgrade to add more resources");
        return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
    }
}