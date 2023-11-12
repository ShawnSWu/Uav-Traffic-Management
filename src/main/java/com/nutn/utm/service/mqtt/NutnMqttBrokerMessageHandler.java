package com.nutn.utm.service.mqtt;

import com.nutn.utm.service.trajectory.RealTimeMonitorUavService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.handler.AbstractMessageHandler;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */

@Service
public class NutnMqttBrokerMessageHandler extends AbstractMessageHandler {

    @Autowired
    RealTimeMonitorUavService realTimeMonitorUavService;

    @Override
    protected void handleMessageInternal(Message<?> message) {
        realTimeMonitorUavService.receiveMqttBrokerMessage((String)message.getPayload());
    }
}
