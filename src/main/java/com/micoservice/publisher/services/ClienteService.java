package com.micoservice.publisher.services;

import java.util.List;

import com.micoservice.publisher.dto.ClienteDTO;
import org.springframework.stereotype.Service;

import com.micoservice.publisher.model.Cliente;
import com.micoservice.publisher.repositories.ClienteRepository;

@Service
public class ClienteService {
    private final ClienteRepository clienteReporitory;

    public ClienteService(ClienteRepository clienteReporitory) {
        this.clienteReporitory = clienteReporitory;
    }

    public List<Cliente> findAll() {
        return clienteReporitory.findAll();
    }

    public Cliente findById(Long id) {
        return clienteReporitory.findById(id).orElse(null);
    }

    public Cliente create(ClienteDTO cliente) {
        return clienteReporitory.save(new Cliente(cliente.name()));
    }

    public Cliente update(Long id, ClienteDTO cliente) {
        Cliente clienteDB = this.findById(id);
        if (clienteDB == null) {
            return null;
        }
        clienteDB.setName(cliente.name());
        return clienteReporitory.save(clienteDB);
    }

    public void deleteById(Long id) {
        Cliente cliente = this.findById(id);
        if (cliente != null) {
            clienteReporitory.delete(cliente);
        }
    }
}
