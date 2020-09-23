package com.nutn.utm.model.dto.response.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class SignUpFailedResponse {
    private String message;
}
