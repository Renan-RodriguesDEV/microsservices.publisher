package com.micoservice.publisher.controllers;

import java.util.List;

import com.micoservice.publisher.dto.ClienteDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import com.micoservice.publisher.model.Cliente;
import com.micoservice.publisher.services.ClienteService;

@RestController
@RequestMapping("clientes")
public class ClienteController {
    private final ClienteService clienteService;
    // objeto que permite enviar mensagens para o RabbitMQ
    private final RabbitTemplate rabbitTemplate;
    @Value("${broker.queue.processamento.name}")
    private String routingKey;

    public ClienteController(ClienteService clienteService, RabbitTemplate rabbitTemplate) {
        this.clienteService = clienteService;
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping("{id}")
    public Cliente get(@PathVariable Long id) {
        return clienteService.findById(id);
    }

    @GetMapping
    public List<Cliente> get() {
        return clienteService.findAll();
    }

    @PostMapping
    public Cliente post(@RequestBody ClienteDTO cliente) {
        Cliente cliente_db = clienteService.create(cliente);
        // envia a mensagem para o RabbitMQ, o primeiro parâmetro é o nome da exchange,
        // o segundo é a routing key e o terceiro é a mensagem
        rabbitTemplate.convertAndSend("", routingKey, cliente.name());
        return cliente_db;

    }

    @PutMapping("{id}")
    public Cliente put(@PathVariable Long id,@RequestBody ClienteDTO cliente) {
        return clienteService.update(id, cliente);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        clienteService.deleteById(id);
    }
}
