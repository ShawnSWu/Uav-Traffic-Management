package com.nutn.utm.it;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nutn.utm.UtmApplication;
import com.nutn.utm.model.entity.Pilot;
import com.nutn.utm.service.AccountService;
import com.nutn.utm.service.jwt.JwtService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UtmApplication.class)
@WebAppConfiguration
@AutoConfigureMockMvc
public abstract class BaseMvcTest {

    @Autowired
    protected ObjectMapper objectMapper;

    protected MockMvc mockMvc;

    protected abstract Object controller();

    @Autowired
    protected JwtService jwtService;

    @Autowired
    protected AccountService accountService;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller()).build();
        setupAuthentication();
    }

    protected MockHttpServletRequestBuilder jsonRequest(MockHttpServletRequestBuilder requestBuilder, Object object)
            throws JsonProcessingException {
        return requestBuilder.content(toJson(object))
                .contentType(MediaType.APPLICATION_JSON);
    }

    protected String toJson(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

    protected void setupAuthentication() {
        Pilot pilot = accountService.getPilotByAccount("ShawnWu");
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(pilot.getId(), pilot.getAccount());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
