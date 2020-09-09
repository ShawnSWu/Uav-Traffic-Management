package com.nutn.utm.model.dto.form;


import javax.validation.constraints.NotBlank;

import static com.nutn.utm.model.dto.response.message.ValidationMessage.MAY_NOT_BE_EMPTY;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */

public class LoginFormDto {

    @NotBlank(message = MAY_NOT_BE_EMPTY)
    private String pilotAccount;

    @NotBlank(message = MAY_NOT_BE_EMPTY)
    private String password;

    public LoginFormDto() {}

    public LoginFormDto(String pilotAccount, String password) {
        this.pilotAccount = pilotAccount;
        this.password = password;
    }

    public String getPilotAccount() {
        return pilotAccount;
    }

    public String getPassword() {
        return password;
    }
}
