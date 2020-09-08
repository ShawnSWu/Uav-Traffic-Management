package com.nutn.utm.model.dto.response.error;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */
public class ErrorFieldDto {
    String field;
    String message;

    public ErrorFieldDto(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }

}
