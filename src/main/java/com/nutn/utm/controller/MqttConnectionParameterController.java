package com.nutn.utm.controller;

import com.nutn.utm.model.dto.mqtt.MqttParameterDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */
@RestController
@RequestMapping("/mqttConnectionParameter")
public class MqttConnectionParameterController {

    @Value("${mqtt_username}")
    private String username;

    @Value("${mqtt_password}")
    private String password;

    @Value("${mqtt_client_host}")
    private String clientHost;

    @Value("${mqtt_client_port}")
    private String clientPort;

    @GetMapping
    MqttParameterDto getMqttBrokerParameter() {
        String clientId = createUUID();
        return MqttParameterDto.builder()
                .username(username)
                .password(password)
                .host(clientHost)
                .port(clientPort)
                .clientId(clientId)
                .build();
    }

    private String createUUID(){
        return UUID. randomUUID().toString();
    }
}
