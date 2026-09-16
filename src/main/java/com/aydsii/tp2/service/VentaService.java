package com.aydsii.tp2.service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.aydsii.tp2.dto.EstadisticasVentaDTO;
import com.aydsii.tp2.dto.VentaDTO;

@Service
public class VentaService {

    // Ej 1 - Endpoint 1
    public EstadisticasVentaDTO calcularEstadisticas(List<VentaDTO> ventas) {
        if(ventas == null || ventas.isEmpty()) {
            throw new IllegalArgumentException("La lista de ventas no puede estar vacia");
        }

        EstadisticasVentaDTO estadisticas = new EstadisticasVentaDTO();


        double totalFacturado = ventas.stream()
                .mapToDouble(VentaDTO::getImporteTotal)
                .sum();

        int cantidadVentas = ventas.size();

        double ticketPromedio = cantidadVentas > 0 ? totalFacturado / cantidadVentas : 0.0;

        VentaDTO ventaMayor = ventas.stream()
                .max(Comparator.comparingDouble(VentaDTO::getImporteTotal))
                .orElse(null);

        VentaDTO ventaMenor = ventas.stream()
                .min(Comparator.comparingDouble(VentaDTO::getImporteTotal))
                .orElse(null);
 
        String productoMasVendido = ventas.stream()
                .collect(Collectors.groupingBy(
                        VentaDTO::getProducto,
                        Collectors.summingInt(VentaDTO::getCantidad)
                ))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("");


        estadisticas.setTotalFacturado(totalFacturado);
        estadisticas.setCantidadVentas(cantidadVentas);
        estadisticas.setTicketPromedio(ticketPromedio);
        estadisticas.setVentaMayor(ventaMayor);
        estadisticas.setVentaMenor(ventaMenor);
        estadisticas.setProductoMasVendido(productoMasVendido);
        
        return estadisticas;
    }
}
