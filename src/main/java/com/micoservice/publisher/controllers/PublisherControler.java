package com.micoservice.publisher.controllers;

import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.micoservice.publisher.model.Cliente;
import com.micoservice.publisher.services.ClienteService;

public class PublisherControler {
    private final ClienteService clienteService;
    // objeto que permite enviar mensagens para o RabbitMQ
    private final RabbitTemplate rabbitTemplate;
    @Value("${broker.queue.processamento.name}")
    private String routingKey;

    public PublisherControler(ClienteService clienteService, RabbitTemplate rabbitTemplate) {
        this.clienteService = clienteService;
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping("/{id}")
    public Cliente get(@PathVariable Long id) {
        return clienteService.findById(id);
    }

    @GetMapping
    public List<Cliente> get() {
        return clienteService.findAll();
    }

    @PostMapping
    public Cliente post(Cliente cliente) {
        Cliente cliente_db = clienteService.create(cliente);
        // envia a mensagem para o RabbitMQ, o primeiro parâmetro é o nome da exchange,
        // o segundo é a routing key e o terceiro é a mensagem
        rabbitTemplate.convertAndSend("", routingKey, cliente);
        return cliente_db;

    }

    @PutMapping("/{id}")
    public Cliente put(Long id, Cliente cliente) {
        return clienteService.update(id, cliente);
    }

    @DeleteMapping("/{id}")
    public void delete(Long id) {
        clienteService.deleteById(id);
    }
}
