package com.nutn.utm.model.dto.response;

import org.springframework.http.HttpStatus;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */

public class JwtAuthResponseDto {

    private HttpStatus status;
    private final String message;


    public JwtAuthResponseDto(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
