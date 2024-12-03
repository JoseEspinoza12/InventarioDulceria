package com.inventarioDulceria.CRUD.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.inventarioDulceria.CRUD.model.Producto;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    List<Producto> findByEstatus(Integer estatus);
    List<Producto> findByCategoriaAndEstatus(String categoria, Integer estatus);
    Optional<Producto> findByIdProductoAndEstatus(Integer idProducto, Integer estatus);
    List<Producto> findByNombreContainingAndEstatus(String nombre, Integer estatus);

    @Query("SELECT p FROM Producto p WHERE p.stockActual <= p.stockMinimo AND p.estatus = 1")
    List<Producto> findProductosBajoStockAndActivos();

    @Query("SELECT p FROM Producto p WHERE p.precioVenta BETWEEN :precioMin AND :precioMax AND p.estatus = 1")
    List<Producto> findProductosByRangoPrecio(@Param("precioMin") BigDecimal precioMin, @Param("precioMax") BigDecimal precioMax);

    @Query("SELECT p FROM Producto p WHERE " +
           "(:nombre IS NULL OR p.nombre LIKE %:nombre%) AND " +
           "(:categoria IS NULL OR p.categoria = :categoria) AND " +
           "(:precioMin IS NULL OR p.precioVenta >= :precioMin) AND " +
           "(:precioMax IS NULL OR p.precioVenta <= :precioMax) AND " +
           "p.estatus = 1")
    List<Producto> buscarProductosFiltrados(@Param("nombre") String nombre,
                                            @Param("categoria") String categoria,
                                            @Param("precioMin") BigDecimal precioMin,
                                            @Param("precioMax") BigDecimal precioMax);
}