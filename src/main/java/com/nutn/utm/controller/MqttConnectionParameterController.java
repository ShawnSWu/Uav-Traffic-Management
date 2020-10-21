package com.nutn.utm.controller;

import com.nutn.utm.model.dto.mqtt.MqttParameterDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ResourceBundle;
import java.util.UUID;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */
@RestController
@RequestMapping("/mqttConnectionParameter")
public class MqttConnectionParameterController {

    @GetMapping
    MqttParameterDto getMqttBrokerParameter() {
        ResourceBundle properties = ResourceBundle.getBundle("mqtt");
        String username = properties.getString("mqtt.username");
        String password = properties.getString("mqtt.password");
        String host = properties.getString("mqtt.client.host");
        String port = properties.getString("mqtt.client.port");
        String clientId = createUUID();
        return MqttParameterDto.builder()
                .username(username)
                .password(password)
                .host(host)
                .port(port)
                .clientId(clientId)
                .build();
    }

    private String createUUID(){
        return UUID. randomUUID().toString();
    }
}
