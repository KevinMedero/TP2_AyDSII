package com.aydsii.tp2.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aydsii.tp2.dto.ApiResponse;
import com.aydsii.tp2.dto.ClienteDTO;
import com.aydsii.tp2.model.Cliente;
import com.aydsii.tp2.service.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/clientes")
@Tag(name = "Modulo de Clientes", description = "Endpoints para el alta y gestion de clientes")
public class ClienteController {
    
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    // EJ 4 - Endpoint 1
    @PostMapping
    @Operation(summary = "Alta simple de cliente sin validaciones estrictas")
    public ResponseEntity<ApiResponse<Cliente>> registrarSimple(@RequestBody ClienteDTO dto) {
        
        Cliente creado = clienteService.crearClienteSimple(dto);
        ApiResponse<Cliente> response = new ApiResponse<>(
                201, 
                "Cliente creado con exito", 
                creado
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // EJ 4 - Endpoint 2
    @PostMapping("/validado")
    @Operation(summary = "Alta de cliente con validacion de campos y verificacion de email unico")
    public ResponseEntity<ApiResponse<Cliente>> registrarValidado(@Valid @RequestBody ClienteDTO dto) {
        
        Cliente creado = clienteService.crearClienteValidado(dto);
        ApiResponse<Cliente> response = new ApiResponse<>(
                201, 
                "Cliente creado con exito", 
                creado
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
