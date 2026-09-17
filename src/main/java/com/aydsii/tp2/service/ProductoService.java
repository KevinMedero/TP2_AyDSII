package com.aydsii.tp2.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.aydsii.tp2.exception.ResourceNotFoundException;
import com.aydsii.tp2.model.Producto;

import jakarta.annotation.PostConstruct;

@Service
public class ProductoService {

    private final List<Producto> catalogo = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @PostConstruct
    public void initData() {
        catalogo.add(new Producto(idGenerator.getAndIncrement(), "Mouse Inalambrico", "Perifericos", 4500.0, 15));
        catalogo.add(new Producto(idGenerator.getAndIncrement(), "Teclado Mecanico", "Perifericos", 25000.0, 10));
        catalogo.add(new Producto(idGenerator.getAndIncrement(), "Monitor 24 Pulgadas", "Monitores", 85000.0, 5));
        catalogo.add(new Producto(idGenerator.getAndIncrement(), "Auriculares Bluetooth", "Audio", 18000.0, 8));
        catalogo.add(new Producto(idGenerator.getAndIncrement(), "Webcam HD", "Perifericos", 12000.0, 20));
        catalogo.add(new Producto(idGenerator.getAndIncrement(), "Disco SSD 1TB", "Almacenamiento", 45000.0, 12));
        catalogo.add(new Producto(idGenerator.getAndIncrement(), "Memoria RAM 16GB", "Componentes", 32000.0, 7));
        catalogo.add(new Producto(idGenerator.getAndIncrement(), "Silla Gamer", "Mobiliario", 120000.0, 3));
    }

    public List<Producto> listarTodos() {
        return new ArrayList<>(catalogo);
    }

    public List<Producto> buscar(String categoria, Double precioMin, Double precioMax) {
        return catalogo.stream()
                .filter(p -> categoria == null || p.getCategoria().equalsIgnoreCase(categoria))
                .filter(p -> precioMin == null || p.getPrecio() >= precioMin)
                .filter(p -> precioMax == null || p.getPrecio() <= precioMax)
                .collect(Collectors.toList());
    }

    public List<Producto> ordenar(String criterio, String orden) {
        Comparator<Producto> comparator;

        if ("nombre".equalsIgnoreCase(criterio)) {
            comparator = Comparator.comparing(Producto::getNombre, String.CASE_INSENSITIVE_ORDER);
        } else {
            // Criterio por defecto: precio
            comparator = Comparator.comparing(Producto::getPrecio);
        }

        if ("desc".equalsIgnoreCase(orden)) {
            comparator = comparator.reversed();
        }

        return catalogo.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    public Producto agregar(Producto producto) {
        producto.setId(idGenerator.getAndIncrement());
        catalogo.add(producto);
        return producto;
    }

    public Producto modificarStock(Long id, Integer cantidad) {
        Producto producto = catalogo.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con ID: " + id));

        int nuevoStock = producto.getStock() + cantidad;
        if (nuevoStock < 0) {
            throw new IllegalArgumentException("La modificacion provocaria un stock negativo (Stock actual: " 
                    + producto.getStock() + ", cambio intentado: " + cantidad + ")");
        }

        producto.setStock(nuevoStock);
        return producto;
    }

    public void eliminar(Long id) {
        Producto producto = catalogo.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con ID: " + id));

        catalogo.remove(producto);
    }
}
