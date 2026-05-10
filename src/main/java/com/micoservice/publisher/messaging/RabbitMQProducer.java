package com.micoservice.publisher.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component // vai ser gerenciado pelo Spring, ou seja, o Spring vai criar uma instância
           // dessa classe e vai injetar onde for necessário
public class RabbitMQProducer {

    private final RabbitTemplate template;
    @Value("${broker.queue.processamento.name}")
    private String routingKey;

    public RabbitMQProducer(RabbitTemplate template) {
        this.template = template;
    }

    public void send(Object message) {
        template.convertAndSend("", routingKey, message);
    }
}
