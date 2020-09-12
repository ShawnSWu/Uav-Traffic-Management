package com.nutn.utm.config;

import com.nutn.utm.service.mqtt.NutnMqttBrokerMessageHandler;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;

import java.util.ResourceBundle;

@Configuration
public class MqttConfig {

    ResourceBundle properties = ResourceBundle.getBundle("mqtt");
    String username = properties.getString("mqtt.username");
    String password = properties.getString("mqtt.password");
    String url = properties.getString("mqtt.url");
    String clientId = properties.getString("mqtt.clientId");
    String defaultTopic = properties.getString("mqtt.default.topic");
    int qos = Integer.parseInt(properties.getString("mqtt.qos"));

    @Bean
    public DirectChannel fromMqBrokerInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public DirectChannel toMqttBrokerOutputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        MqttConnectOptions connectOptions = new MqttConnectOptions();
        connectOptions.setServerURIs(new String[]{url});
        connectOptions.setUserName(username);
        connectOptions.setPassword(password.toCharArray());

        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        factory.setConnectionOptions(connectOptions);
        return factory;
    }

    @Bean
    public MessageProducer inbound() {
        MqttPahoMessageDrivenChannelAdapter adapter =
                new MqttPahoMessageDrivenChannelAdapter(url, clientId, mqttClientFactory(),  defaultTopic);
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(qos);
        adapter.setOutputChannel(fromMqBrokerInputChannel());
        return adapter;
    }

    @Bean
    @ServiceActivator(inputChannel = "toMqttBrokerOutputChannel")
    public MqttPahoMessageHandler mqttOutbound() {
        MqttPahoMessageHandler handler = new MqttPahoMessageHandler(url, clientId, mqttClientFactory());
        handler.setDefaultQos(qos);
        return handler;
    }

    @Bean
    @ServiceActivator(inputChannel = "fromMqBrokerInputChannel")
    public NutnMqttBrokerMessageHandler mqttPahoMessageHandler() {
        return new NutnMqttBrokerMessageHandler();
    }
}


