package com.aydsii.tp2.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aydsii.tp2.dto.ApiResponse;
import com.aydsii.tp2.dto.EstadisticasVentaDTO;
import com.aydsii.tp2.dto.VentaDTO;
import com.aydsii.tp2.service.VentaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/ventas")
@Tag(name = "Ventas", description = "Endpoints para procesamiento y analisis de lotes de ventas") 
public class VentaController {

    private final VentaService ventaService;

    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    // Ej 1 - Endpoint 1
    @PostMapping("/estadisticas")
    @Operation(summary = "Calcular estadisticas de un lote de ventas")
    public ResponseEntity<ApiResponse<EstadisticasVentaDTO>> calcularEstadisticas(
            @Valid @RequestBody List<VentaDTO> ventas) {
        
        EstadisticasVentaDTO estadisticas = ventaService.calcularEstadisticas(ventas);
        ApiResponse<EstadisticasVentaDTO> response = new ApiResponse<>(
            200,
            "Estadisticas calculadas con exito",
            estadisticas
        );
        return ResponseEntity.ok(response);
    } 

    // EJ1 - Endpoint 2
    @PostMapping("/aplicar-descuento")
    @Operation(summary = "Aplicar un porcentaje de descuento a una lista de ventas")
    public ResponseEntity<ApiResponse<Map<String, Object>>> aplicarDescuento(
            @Valid @RequestBody List<VentaDTO> ventas,
            @Parameter(description = "Porcentaje de descuento (0 a 100)") @RequestParam Double porcentaje) {

        Map<String, Object> resultado = ventaService.aplicarDescuento(ventas, porcentaje);
        ApiResponse<Map<String, Object>> response = new ApiResponse<>(
            200,
            "Descuento aplicado con exito",
            resultado
        );
        return ResponseEntity.ok(response);
    }
}
