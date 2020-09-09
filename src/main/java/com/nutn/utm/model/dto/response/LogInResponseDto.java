package com.nutn.utm.model.dto.response;

import jdk.nashorn.internal.runtime.options.Option;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */
public class LogInResponseDto {

    private String token;

    private String message;

    public LogInResponseDto(String token, String message) {
        this.token = token;
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public String getMessage() {
        return message;
    }
}
