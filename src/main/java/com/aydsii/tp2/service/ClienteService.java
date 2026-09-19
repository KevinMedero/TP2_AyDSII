package com.aydsii.tp2.service;

import org.springframework.stereotype.Service;

import com.aydsii.tp2.dto.ClienteDTO;
import com.aydsii.tp2.model.Cliente;
import com.aydsii.tp2.repository.ClienteRepository;

@Service 
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    // EJ 4 - Endpoint 1
    public Cliente crearClienteSimple(ClienteDTO dto) {
        Cliente cliente = new Cliente(
                dto.getNombre(),
                dto.getApellido(),
                dto.getEmail(),
                dto.getTelefono()
        );
        return clienteRepository.save(cliente);
    }

    // EJ 4 - Endpoint 2
    public Cliente crearClienteValidado(ClienteDTO dto) {
        // Validacion de negocio: Comprobar duplicidad de email en BD
        if (clienteRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("El email ya está registrado");
        }

        Cliente cliente = new Cliente(
                dto.getNombre(),
                dto.getApellido(),
                dto.getEmail(),
                dto.getTelefono()
        );
        return clienteRepository.save(cliente);
    }
}
