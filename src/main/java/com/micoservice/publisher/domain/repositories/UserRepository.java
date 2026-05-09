package com.micoservice.publisher.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.micoservice.publisher.domain.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
