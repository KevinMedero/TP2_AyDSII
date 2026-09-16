package com.aydsii.tp2.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aydsii.tp2.dto.ApiResponse;
import com.aydsii.tp2.dto.EstadisticasVentaDTO;
import com.aydsii.tp2.dto.VentaDTO;
import com.aydsii.tp2.service.VentaService;

import io.swagger.v3.oas.annotations.Operation;
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
    public ResponseEntity<ApiResponse<EstadisticasVentaDTO>> calcularEstadisticas(@Valid @RequestBody List<VentaDTO> ventas) {
        
        EstadisticasVentaDTO estadisticas = ventaService.calcularEstadisticas(ventas);
        ApiResponse<EstadisticasVentaDTO> response = new ApiResponse<>(
            200,
            "Estadisticas calculadas con exito",
            estadisticas
        );
        return ResponseEntity.ok(response);
    } 
}
