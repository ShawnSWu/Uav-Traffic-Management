package com.nutn.utm.model.dto.response.error;

import java.util.List;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */
public class RequestErrorResponseDto {

    private List<ErrorFieldDto> errors;
    private String message;

    public RequestErrorResponseDto(List<ErrorFieldDto> errors, String message) {
        this.errors = errors;
        this.message = message;
    }

    public List<ErrorFieldDto> getErrors() {
        return errors;
    }

    public String getMessage() {
        return message;
    }
}
