package com.aydsii.tp2.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class ConversionDivisaDTO {
    // Para estructurar la respuesta final de la API

    @Schema(description = "Monto original ingresado", example = "100.0")
    private Double montoOriginal;

    @Schema(description = "Codigo ISO de la moneda de origen", example = "USD")
    private String monedaOrigen;

    @Schema(description = "Codigo ISO de la moneda de destino", example = "ARS")
    private String monedaDestino;

    @Schema(description = "Tasa de cambio aplicada", example = "1234.56")
    private Double tasaCambio;

    @Schema(description = "Monto final convertido", example = "123456.0")
    private Double montoConvertido;

    @Schema(description = "Fecha de la cotizacion de la API externa", example = "2026-09-02")
    private String fecha;

    public ConversionDivisaDTO() {}

    public ConversionDivisaDTO(Double montoOriginal, String monedaOrigen, String monedaDestino, Double tasaCambio, Double montoConvertido, String fecha) {
        this.montoOriginal = montoOriginal;
        this.monedaOrigen = monedaOrigen;
        this.monedaDestino = monedaDestino;
        this.tasaCambio = tasaCambio;
        this.montoConvertido = montoConvertido;
        this.fecha = fecha;
    }

    public Double getMontoOriginal() { return montoOriginal; }
    public void setMontoOriginal(Double montoOriginal) { this.montoOriginal = montoOriginal; }

    public String getMonedaOrigen() { return monedaOrigen; }
    public void setMonedaOrigen(String monedaOrigen) { this.monedaOrigen = monedaOrigen; }

    public String getMonedaDestino() { return monedaDestino; }
    public void setMonedaDestino(String monedaDestino) { this.monedaDestino = monedaDestino; }

    public Double getTasaCambio() { return tasaCambio; }
    public void setTasaCambio(Double tasaCambio) { this.tasaCambio = tasaCambio; }

    public Double getMontoConvertido() { return montoConvertido; }
    public void setMontoConvertido(Double montoConvertido) { this.montoConvertido = montoConvertido; }

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }
}
