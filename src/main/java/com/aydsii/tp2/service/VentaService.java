package com.aydsii.tp2.service;

import java.util.Comparator;
import java.util.HashMap;
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

    // EJ 1 - Endpoint 2
    public Map<String, Object> aplicarDescuento(List<VentaDTO> ventas, Double porcentaje) {
        if (ventas == null || ventas.isEmpty()) {
            throw new IllegalArgumentException("La lista de ventas no puede estar vacia");
        }

        if (porcentaje == null || porcentaje < 0 || porcentaje > 100) {
            throw new IllegalArgumentException("El porcentaje de descuento debe ser un valor entre 0 y 100 inclusive");
        }

        double factorDescuento = (100.0 - porcentaje) / 100.0;

        List<VentaDTO> ventasConDescuento = ventas.stream().map(v -> {
            VentaDTO dto = new VentaDTO(v.getProducto(), v.getCantidad(), v.getPrecioUnitario());
            double montoConDescuento = v.getImporteTotal() * factorDescuento;
            dto.setMontoConDescuento(montoConDescuento);
            return dto;
        }).collect(Collectors.toList());

        double totalConDescuento = ventasConDescuento.stream()
                .mapToDouble(VentaDTO::getMontoConDescuento)
                .sum();

        Map<String, Object> resultado = new HashMap<>();
        resultado.put("ventas", ventasConDescuento);
        resultado.put("totalConDescuento", totalConDescuento);

        return resultado;
    }
}
