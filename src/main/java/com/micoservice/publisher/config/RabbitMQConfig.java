package com.micoservice.publisher.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Criamos a configuração do RabbitMQ, onde definimos a fila que será utilizada para o envio das mensagens. A anotação @Configuration indica que esta classe é uma classe de configuração do Spring, e a anotação @Bean indica que o método que retorna a fila deve ser gerenciado pelo Spring como um bean. O nome da fila é injetado a partir do arquivo de propriedades usando a anotação @Value. Não usamos essa configuração para criar a fila, pois ela já foi criada no RabbitMQ, mas é necessário para que o Spring possa gerenciar a fila e enviar as mensagens para ela.
@Configuration
public class RabbitMQConfig {
    @Value("${broker.queue.processamento.name}")
    private String queue;

    @Bean
    public Queue queu() {
        return new Queue(queue, true);
    }
}
