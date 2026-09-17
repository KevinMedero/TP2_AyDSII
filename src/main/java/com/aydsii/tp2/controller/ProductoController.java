package com.aydsii.tp2.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aydsii.tp2.dto.ApiResponse;
import com.aydsii.tp2.model.Producto;
import com.aydsii.tp2.service.ProductoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/catalogo")
@Tag(name = "Catalogo de Productos", description = "Endpoints para la gestion y busqueda de productos en memoria")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    @Operation(summary = "Obtener todos los productos del catalogo")
    public ResponseEntity<ApiResponse<List<Producto>>> listarTodos() {
        List<Producto> productos = productoService.listarTodos();
        ApiResponse<List<Producto>> response = new ApiResponse<>(200, "Productos recuperados con exito", productos);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/buscar")
    @Operation(summary = "Buscar y filtrar productos por categoria y/o rango de precios")
    public ResponseEntity<ApiResponse<List<Producto>>> buscar(
            @Parameter(description = "Categoria a filtrar") @RequestParam(required = false) String categoria,
            @Parameter(description = "Precio minimo") @RequestParam(required = false) Double precioMin,
            @Parameter(description = "Precio maximo") @RequestParam(required = false) Double precioMax) {

        List<Producto> resultado = productoService.buscar(categoria, precioMin, precioMax);
        ApiResponse<List<Producto>> response = new ApiResponse<>(200, "Busqueda realizada con exito", resultado);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/ordenar")
    @Operation(summary = "Ordenar productos por precio o nombre (asc/desc)")
    public ResponseEntity<ApiResponse<List<Producto>>> ordenar(
            @Parameter(description = "Criterio de ordenacion: 'precio' o 'nombre'") @RequestParam(required = false, defaultValue = "precio") String criterio,
            @Parameter(description = "Orden: 'asc' o 'desc'") @RequestParam(required = false, defaultValue = "asc") String orden) {

        List<Producto> resultado = productoService.ordenar(criterio, orden);
        ApiResponse<List<Producto>> response = new ApiResponse<>(200, "Productos ordenados con exito", resultado);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @Operation(summary = "Agregar un nuevo producto al catalogo")
    public ResponseEntity<ApiResponse<Producto>> agregar(@Valid @RequestBody Producto nuevoProducto) {
        Producto creado = productoService.agregar(nuevoProducto);
        ApiResponse<Producto> response = new ApiResponse<>(201, "Producto creado con exito", creado);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}/stock")
    @Operation(summary = "Modificar el stock de un producto por ID")
    public ResponseEntity<ApiResponse<Producto>> modificarStock(
            @PathVariable Long id,
            @Parameter(description = "Cantidad a modificar (positiva suma, negativa resta)") @RequestParam Integer cantidad) {

        Producto actualizado = productoService.modificarStock(id, cantidad);
        ApiResponse<Producto> response = new ApiResponse<>(200, "Stock actualizado con exito", actualizado);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un producto por ID")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
        productoService.eliminar(id);
        ApiResponse<Void> response = new ApiResponse<>(200, "Producto eliminado correctamente", null);
        return ResponseEntity.ok(response);
    }
}
