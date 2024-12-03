package com.inventarioDulceria.CRUD.service;

import com.inventarioDulceria.CRUD.model.Producto;
import com.inventarioDulceria.CRUD.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    @Transactional(readOnly = true)
    public List<Producto> obtenerProductos() {
        return productoRepository.findByEstatus(1);
    }

    @Transactional
    public Producto guardar(Producto producto) {
        if (producto.getNombre() == null || producto.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto es obligatorio");
        }
        if (producto.getPrecioVenta() == null || producto.getPrecioVenta().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El precio de venta debe ser mayor a cero");
        }
        if (producto.getIdProducto() == null) {
            producto.setEstatus(1);
        }
        return productoRepository.save(producto);
    }

    @Transactional(readOnly = true)
    public Producto obtenerPorId(Integer idProducto) {
        return productoRepository.findByIdProductoAndEstatus(idProducto, 1)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + idProducto));
    }

    @Transactional
    public void eliminarLogicamente(Integer idProducto) {
        Producto producto = obtenerPorId(idProducto);
        producto.setEstatus(0);
        productoRepository.save(producto);
    }

    public List<Producto> obtenerProductosPorCategoria(String categoria) {
        return productoRepository.findByCategoriaAndEstatus(categoria, 1);
    }

    public List<Producto> obtenerProductosBajoStock() {
        return productoRepository.findProductosBajoStockAndActivos();
    }

    public List<Producto> buscarProductos(String nombre, String categoria, 
                                          BigDecimal precioMin, BigDecimal precioMax) {
        return productoRepository.buscarProductosFiltrados(nombre, categoria, precioMin, precioMax);
    }
}
