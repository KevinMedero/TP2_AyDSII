package com.aydsii.tp2.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aydsii.tp2.dto.ApiResponse;
import com.aydsii.tp2.dto.ConversionDivisaDTO;
import com.aydsii.tp2.service.DivisaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/divisas")
@Tag(name = "Conversor de Divisas", description = "Endpoints para la conversion de monedas utilizando la API externa Frankfurter")
public class DivisaController {
    
    private final DivisaService divisaService;

    public DivisaController(DivisaService divisaService) {
        this.divisaService = divisaService;
    }

    @GetMapping("/convertir")
    @Operation(summary = "Convertir un monto entre dos monedas utilizando cotizaciones en tiempo real")
    public ResponseEntity<ApiResponse<ConversionDivisaDTO>> convertir(
            @Parameter(description = "Monto a convertir", example = "100") @RequestParam Double monto,
            @Parameter(description = "Codigo ISO de la moneda origen", example = "USD") @RequestParam String origen,
            @Parameter(description = "Codigo ISO de la moneda destino", example = "EUR") @RequestParam String destino) {

        ConversionDivisaDTO resultado = divisaService.convertir(monto, origen, destino);
        ApiResponse<ConversionDivisaDTO> response = new ApiResponse<>(
                200,
                "Conversion realizada con exito",
                resultado
        );
        return ResponseEntity.ok(response);
    }
}
