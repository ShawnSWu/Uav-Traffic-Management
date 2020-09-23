package com.nutn.utm.model.dto.form;

import lombok.Data;
import javax.validation.constraints.*;
import static com.nutn.utm.model.dto.response.message.ValidationMessage.*;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */

@Data
public class SignUpFormDto {

    @Size(min = 8, max = 16, message = ACCOUNT_NOT_CONFORM_FORMAT)
    private String account;

    @Size(min = 8, max = 16, message = PASSWORD_NOT_CONFORM_FORMAT)
    private String password;

    @Size(min = 8, max = 16, message = PASSWORD_NOT_CONFORM_FORMAT)
    private String confirmedPassword;

    @Pattern(regexp = "[0-9]{9,10}", message = USERNAME_NOT_CONFORM_FORMAT)
    @NotBlank(message = MAY_NOT_BE_EMPTY)
    private String phoneNumber;

    @Pattern(regexp = "[A-Za-z0-9\\-]{2,16}", message = USERNAME_NOT_CONFORM_FORMAT)
    private String name;

    @Email(message = EMAIL_NOT_CONFORM_FORMAT)
    @NotBlank(message = MAY_NOT_BE_EMPTY)
    private String email;

    @NotBlank(message = MAY_NOT_BE_EMPTY)
    private String institution;

    @AssertTrue(message = PASSWORD_AND_RECONFIRM_DIFFERENCE)
    private boolean isPasswordSame() {
        return password.equals(confirmedPassword);
    }

    @AssertFalse(message = ACCOUNT_AND_PASSWORD_SAME)
    private boolean isAccountAndPasswordSame() {
        return account.equals(password);
    }
}
