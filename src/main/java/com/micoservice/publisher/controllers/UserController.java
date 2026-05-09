package com.micoservice.publisher.controllers;

import java.util.List;

import com.micoservice.publisher.domain.dto.request.UserDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import com.micoservice.publisher.domain.model.User;
import com.micoservice.publisher.domain.services.UserService;

@RestController
@RequestMapping("users")
public class UserController {
    private final UserService userService;
    // objeto que permite enviar mensagens para o RabbitMQ
    private final RabbitTemplate rabbitTemplate;
    @Value("${broker.queue.processamento.name}")
    private String routingKey;

    public UserController(UserService userService, RabbitTemplate rabbitTemplate) {
        this.userService = userService;
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping("{id}")
    public User get(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping
    public List<User> get() {
        return userService.findAll();
    }

    @PostMapping
    public User post(@RequestBody UserDTO cliente) {
        User cliente_db = userService.create(cliente);
        // envia a mensagem para o RabbitMQ, o primeiro parâmetro é o nome da exchange,
        // o segundo é a routing key e o terceiro é a mensagem
        rabbitTemplate.convertAndSend("", routingKey, cliente.name());
        return cliente_db;

    }

    @PutMapping("{id}")
    public User put(@PathVariable Long id, @RequestBody UserDTO cliente) {
        return userService.update(id, cliente);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        userService.deleteById(id);
    }
}
