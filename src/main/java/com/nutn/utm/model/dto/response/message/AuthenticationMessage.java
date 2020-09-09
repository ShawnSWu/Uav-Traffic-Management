package com.nutn.utm.model.dto.response.message;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */
public interface AuthenticationMessage {

    String EXPIRED_MESSAGE = "Your token has expired, please reapply.";
    String INVALID_MESSAGE = "Invalid token, please try again";
    String JWT_AUTHORIZATION_OK = "OK";
    String ACCOUNT_OR_PASSWORD_NOT_MATCH = "Account or password not match.";
}
