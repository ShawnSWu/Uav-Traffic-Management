package com.nutn.utm.service;

import com.nutn.utm.model.dto.response.LogInResponseDto;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */
public interface AccountService {

    LogInResponseDto logIn(String account, String hashedPassword);

}
