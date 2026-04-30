package com.micoservice.publisher.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.micoservice.publisher.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
}
