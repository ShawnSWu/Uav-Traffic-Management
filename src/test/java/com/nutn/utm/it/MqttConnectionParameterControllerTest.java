package com.nutn.utm.it;

import com.nutn.utm.controller.MqttConnectionParameterController;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */
public class MqttConnectionParameterControllerTest extends BaseMvcTest{

    private final String URL_PREFIX = "/mqttConnectionParameter";

    @Autowired
    MqttConnectionParameterController parameterController;

    @Override
    protected Object controller() {
        return parameterController;
    }

    @Test
    public void testGetLimitAreaData() throws Exception {
        mockMvc.perform(get(URL_PREFIX))
                .andExpect(status().isOk())
                .andExpect(jsonPath("username").isNotEmpty())
                .andExpect(jsonPath("password").isNotEmpty())
                .andExpect(jsonPath("clientId").isNotEmpty())
                .andExpect(jsonPath("host").isNotEmpty())
                .andExpect(jsonPath("port").isNotEmpty());
    }

}
