package com.nutn.utm.model.dto.mqtt;

import lombok.Builder;
import lombok.Getter;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */
@Builder
@Getter
public class MqttParameterDto {

    private String host;
    private String port;
    private String username;
    private String password;
    private String clientId;
}
