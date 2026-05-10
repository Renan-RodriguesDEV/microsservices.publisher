package com.micoservice.publisher.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micoservice.publisher.domain.dto.request.UserDTO;
import com.micoservice.publisher.domain.model.User;
import com.micoservice.publisher.domain.services.UserService;

@RestController
@RequestMapping("users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
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
    public User post(@RequestBody UserDTO data) {
        User user_db = userService.create(data);
        // envia a mensagem para o RabbitMQ, o primeiro parâmetro é o nome da exchange,
        // o segundo é a routing key e o terceiro é a mensagem
        return user_db;

    }

    @PutMapping("{id}")
    public User put(@PathVariable Long id, @RequestBody UserDTO data) {
        return userService.update(id, data);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        userService.deleteById(id);
    }
}
