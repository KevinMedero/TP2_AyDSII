package com.aydsii.tp2.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.aydsii.tp2.dto.ConversionDivisaDTO;
import com.aydsii.tp2.dto.FrankfurterResponseDTO;
import com.aydsii.tp2.exception.ResourceNotFoundException;

@Service 
public class DivisaService {

private final RestClient restClient;

    public DivisaService() {
        // Usa el host oficial directo para evitar redirecciones a paginas HTML
        this.restClient = RestClient.builder()
                .baseUrl("https://api.frankfurter.dev")
                .defaultHeader(HttpHeaders.USER_AGENT, "Mozilla/5.0")
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public ConversionDivisaDTO convertir(Double monto, String origen, String destino) {
        // Validaciones
        if (monto == null || monto <= 0) {
            throw new IllegalArgumentException("El monto debe ser un valor mayor que 0");
        }
        if (origen == null || !origen.matches("^[a-zA-Z]{3}$")) {
            throw new IllegalArgumentException("La moneda de origen debe ser un codigo de 3 letras (ej. USD)");
        }
        if (destino == null || !destino.matches("^[a-zA-Z]{3}$")) {
            throw new IllegalArgumentException("La moneda de destino debe ser un codigo de 3 letras (ej. ARS)");
        }

        String origenUpper = origen.toUpperCase();
        String destinoUpper = destino.toUpperCase();

        try {
            // Consulta HTTP directa a la API de Frankfurter
            FrankfurterResponseDTO response = restClient.get()
                    .uri("/v1/latest?amount={amount}&from={from}&to={to}", monto, origenUpper, destinoUpper)
                    .retrieve()
                    .body(FrankfurterResponseDTO.class);

            if (response == null || response.getRates() == null || !response.getRates().containsKey(destinoUpper)) {
                throw new ResourceNotFoundException("La moneda destino '" + destinoUpper + "' no esta disponible en el servicio de divisas.");
            }

            // Calculos y construccion de la respuesta final
            Double montoConvertido = response.getRates().get(destinoUpper);
            Double tasaCambio = montoConvertido / monto;

            return new ConversionDivisaDTO(
                    monto,
                    origenUpper,
                    destinoUpper,
                    tasaCambio,
                    montoConvertido,
                    response.getDate()
            );

        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al comunicarse con la API externa de divisas: " + e.getMessage());
        }
    }
}
