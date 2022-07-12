package com.yordanm.notification.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class NotificationConfig {

    @Value("${rabbitmq.exchanges.internal}")
    private String internalExchange;

    @Value("${rabbitmq.queue.notification}")
    private String notification;

    @Value("${rabbitmq.routing-keys.internal-notification}")
    private String internalNotificationRoutingKey;
}
