package com.nutn.utm.it;

import com.nutn.utm.controller.AccountController;
import com.nutn.utm.model.dto.form.LoginFormDto;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("dev")
@Sql(scripts = {"classpath:clear.sql", "classpath:testData.sql"}, executionPhase = BEFORE_TEST_METHOD)
public class AccountControllerTest extends BaseMvcTest {

    private final String URL_PREFIX = "/account";

    @Autowired
    AccountController pilotController;

    @Override
    protected Object controller() {
        return pilotController;
    }

    @Test
    public void should_return_token_and_account_when_login_successfully() throws Exception {
        mockMvc.perform(jsonRequest(post(URL_PREFIX+"/login"),
                new LoginFormDto("ShawnWu", "password")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("token").isString())
                .andExpect(jsonPath("token").isNotEmpty());
    }


}
