package com.nutn.utm.model.dto.response;

import com.nutn.utm.model.entity.Pilot;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */
@AllArgsConstructor
@Getter
public class LogInResponseDto {

    private String token;

    private Pilot pilot;

    private String message;

}
