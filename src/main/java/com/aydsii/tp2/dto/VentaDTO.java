package com.aydsii.tp2.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@JsonInclude(JsonInclude.Include.NON_NULL) // Oculta atributos que valgan null (montoConDescuento cuando no corresponde)
public class VentaDTO {
    
    @Schema(description = "Nombre del producto vendido", example = "Mouse inalambrico")
    @NotEmpty(message = "El nombre del producto no puede ser vacio")
    private String producto;
    
    @Schema(description = "Cantidad vendida (debe ser mayor a 0)", example = "3")
    @NotNull(message = "La cantidad no puede ser nula")
    @Min(value = 1, message = "La cantidad debe ser un entero positivo mayor a 0")
    private Integer cantidad;

    @Schema(description = "Precio unitario del producto (debe ser mayor a 0)", example = "4500.0")
    @NotNull(message = "El precio unitario no puede ser nulo")
    @Positive(message = "El precio unitario debe ser un valor positivo mayor a 0")
    private Double precioUnitario;

    // Campo adicional calculado opcional (usado en el EJ 1 - endpoint 2 de descuento)
    @Schema(description = "Monto con descuento aplicado", example = "12150.0")
    private Double montoConDescuento;

    public VentaDTO() {}

    public VentaDTO(String producto, Integer cantidad, Double precioUnitario) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    // Método auxiliar para calcular el importe total individual de esta venta
    @JsonIgnore // <-- Evita que 'importeTotal' se agregue al JSON de respuesta
    public Double getImporteTotal() {
        Double importeTotal = 0.0;
        
        if(cantidad != null && precioUnitario != null) {
            importeTotal = cantidad * precioUnitario;
        }

        return importeTotal;
    }

    // Getters y Setters
    public String getProducto() { return producto; }
    public void setProducto(String producto) { this.producto = producto; }

    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }

    public Double getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(Double precioUnitario) { this.precioUnitario = precioUnitario; }

    public Double getMontoConDescuento() { return montoConDescuento; }
    public void setMontoConDescuento(Double montoConDescuento) { this.montoConDescuento = montoConDescuento; }
}
