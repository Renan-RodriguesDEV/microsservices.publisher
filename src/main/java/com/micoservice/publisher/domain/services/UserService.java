package com.micoservice.publisher.domain.services;

import java.util.List;

import com.micoservice.exceptions.AlreadyExistsException;
import com.micoservice.publisher.domain.dto.request.UserDTO;
import com.micoservice.publisher.security.TokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.micoservice.publisher.domain.model.User;
import com.micoservice.publisher.domain.repositories.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager, TokenService tokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new AlreadyExistsException("Erro ao buscar usuario"));
    }

    public String login(UserDTO userDTO) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                userDTO.username(), userDTO.password());
        authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        return tokenService.generateToken(userDTO.username());
    }

    public User create(UserDTO userDTO) {
        if (userRepository.findByUsername(userDTO.username()) != null) {
            throw new AlreadyExistsException("Usuario já existe");
        }
        User user = userDTO.toEntity();
        return userRepository.save(user);
    }

    public User update(Long id, UserDTO userDTO) {
        User user = this.findById(id);
        if (user == null) {
            return null;
        }
        user.setUsername(userDTO.username());
        user.setPassword(passwordEncoder.encode(userDTO.password()));
        return userRepository.save(user);
    }

    public void deleteById(Long id) {
        User user = this.findById(id);
        if (user != null) {
            userRepository.delete(user);
        }
    }
}
