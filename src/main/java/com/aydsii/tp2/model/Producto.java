package com.aydsii.tp2.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class Producto {

    @Schema(description = "Identificador unico del producto", example = "1")
    private Long id;

    @Schema(description = "Nombre del producto", example = "Teclado Mecanico")
    @NotBlank(message = "El nombre no puede estar vacio")
    private String nombre;

    @Schema(description = "Categoria del producto", example = "Perifericos")
    @NotBlank(message = "La categoria no puede estar vacia")
    private String categoria;

    @Schema(description = "Precio unitario (debe ser mayor a 0)", example = "25000.0")
    @NotNull(message = "El precio no puede ser nulo")
    @Positive(message = "El precio debe ser mayor que 0")
    private Double precio;

    @Schema(description = "Stock disponible (mayor o igual a 0)", example = "10")
    @NotNull(message = "El stock no puede ser nulo")
    @Min(value = 0, message = "El stock debe ser mayor o igual a 0")
    private Integer stock;

    public Producto() {}

    public Producto(Long id, String nombre, String categoria, Double precio, Integer stock) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.stock = stock;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }

    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }
}
