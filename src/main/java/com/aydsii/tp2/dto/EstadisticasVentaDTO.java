package com.aydsii.tp2.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class EstadisticasVentaDTO {
    
    @Schema(description = "Suma total facturada de todas las ventas", example = "13500.0")
    private Double totalFacturado;

    @Schema(description = "Cantidad total de ventas procesadas", example = "3")
    private Integer cantidadVentas;

    @Schema(description = "Promedio de venta (totalFacturado / cantidadVentas)", example = "4500.0")
    private Double ticketPromedio;

    @Schema(description = "Venta con el importe mayor")
    private VentaDTO ventaMayor;

    @Schema(description = "Venta con el importe menor")
    private VentaDTO ventaMenor;

    @Schema(description = "Nombre del producto acumulado con mayor cantidad vendida", example = "Mouse inalambrico")
    private String productoMasVendido;

    public EstadisticasVentaDTO() {}

    // Getters y Setters
    public Double getTotalFacturado() { return totalFacturado; }
    public void setTotalFacturado(Double totalFacturado) { this.totalFacturado = totalFacturado; }

    public Integer getCantidadVentas() { return cantidadVentas; }
    public void setCantidadVentas(Integer cantidadVentas) { this.cantidadVentas = cantidadVentas; }

    public Double getTicketPromedio() { return ticketPromedio; }
    public void setTicketPromedio(Double ticketPromedio) { this.ticketPromedio = ticketPromedio; }

    public VentaDTO getVentaMayor() { return ventaMayor; }
    public void setVentaMayor(VentaDTO ventaMayor) { this.ventaMayor = ventaMayor; }

    public VentaDTO getVentaMenor() { return ventaMenor; }
    public void setVentaMenor(VentaDTO ventaMenor) { this.ventaMenor = ventaMenor; }

    public String getProductoMasVendido() { return productoMasVendido; }
    public void setProductoMasVendido(String productoMasVendido) { this.productoMasVendido = productoMasVendido; }
}
