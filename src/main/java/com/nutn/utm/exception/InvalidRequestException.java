package com.nutn.utm.exception;

import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */

public class InvalidRequestException extends RuntimeException {

    private List<FieldError>  fieldErrors;

    public InvalidRequestException(List<FieldError> fieldErrors) {
        super("Invalid Request");
        this.fieldErrors = fieldErrors;
    }

    public InvalidRequestException(String object, String field, String message) {
        super("Invalid Request");
        List<FieldError> fieldErrors = new ArrayList<>();
        fieldErrors.add(new FieldError(object, field, message));
        this.fieldErrors = fieldErrors;
    }

    public List<FieldError>  getErrors() {
        return fieldErrors;
    }
}
