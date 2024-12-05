package com.inventarioDulceria.CRUD.controller;

import com.inventarioDulceria.CRUD.model.Producto;
import com.inventarioDulceria.CRUD.service.ProductoService;
import com.inventarioDulceria.CRUD.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/productos/api")
@CrossOrigin(origins = "*")
@Tag(name = "Productos", description = "Operaciones relacionadas con los productos")
public class ProductoController {
    
    @Autowired
    private ProductoService productoService;

    @Operation(summary = "Obtener todos los productos", description = "Devuelve una lista de productos.")
    @GetMapping
    public ResponseEntity<ApiResponse<List<Producto>>> obtenerProductos() {
        List<Producto> productos = productoService.obtenerProductos();
        return ResponseEntity.ok(new ApiResponse<>(true, "Productos encontrados", productos));
    }

    @Operation(summary = "Guardar un producto", description = "Guarda un producto en la base de datos.")
    @PostMapping
    public ResponseEntity<ApiResponse<Producto>> guardarProducto(@RequestBody Producto producto) {
        Producto productoGuardado = productoService.guardar(producto);
        return ResponseEntity.ok(new ApiResponse<>(true, "Producto guardado exitosamente", productoGuardado));
    }

    @Operation(summary = "Obtener un producto por ID", description = "Devuelve los detalles de un producto específico.")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Producto>> obtenerProductoPorId(@PathVariable Integer id) {
        Producto producto = productoService.obtenerPorId(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Producto encontrado", producto));
    }

    @Operation(summary = "Eliminar un producto", description = "Elimina un producto de forma lógica.")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarProducto(@PathVariable Integer id) {
        productoService.eliminarLogicamente(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Producto eliminado exitosamente", null));
    }
    
    @Operation(summary = "Actualizar un producto", description = "Actualiza los detalles de un producto existente.")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Producto>> actualizarProducto(@PathVariable Integer id, @RequestBody Producto producto) {
        // Verificar que el ID del path coincida con el ID del producto
        if (!id.equals(producto.getIdProducto())) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, "El ID del producto no coincide", null));
        }

        // Actualizar el producto
        Producto productoActualizado = productoService.guardar(producto);
        return ResponseEntity.ok(new ApiResponse<>(true, "Producto actualizado exitosamente", productoActualizado));
    }
}