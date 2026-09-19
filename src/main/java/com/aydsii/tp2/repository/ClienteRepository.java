package com.aydsii.tp2.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aydsii.tp2.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    // Metodo para comprobar si el email ya existe en la base de datos
    boolean existsByEmail(String email);

    Optional<Cliente> findByEmail(String email);
}