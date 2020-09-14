package com.nutn.utm.service.mqtt;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */

@Component
@MessagingGateway(defaultRequestChannel = "toMqttBrokerOutputChannel")
public interface MqttMessagePublisher {

    void sendToMqtt(@Header(MqttHeaders.TOPIC) String topic, String data);
}
