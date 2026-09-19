package com.aydsii.tp2.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ClienteDTO {

    @Schema(description = "Nombre del cliente (minimo 2 caracteres)", example = "Ana")
    @NotBlank(message = "el nombre no puede estar vacio")
    @Size(min = 2, message = "debe tener al menos 2 caracteres")
    private String nombre;

    @Schema(description = "Apellido del cliente (minimo 2 caracteres)", example = "Garcia")
    @NotBlank(message = "el apellido no puede estar vacio")
    @Size(min = 2, message = "debe tener al menos 2 caracteres")
    private String apellido;

    @Schema(description = "Correo electronico unico", example = "ana.garcia@mail.com")
    @NotBlank(message = "el email es obligatorio")
    @Email(message = "debe ser un email valido")
    private String email;

    @Schema(description = "Telefono opcional (solo digitos)", example = "3814567890")
    @Pattern(regexp = "^[0-9]*$", message = "solo debe contener digitos")
    private String telefono;

    public ClienteDTO() {}

    public ClienteDTO(String nombre, String apellido, String email, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
}
