package com.inventarioDulceria.CRUD.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "productos")
@Schema(description = "Modelo de datos para un producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    @Schema(description = "ID único del producto", example = "1")
    private Integer idProducto;

    @Column(name = "nombre", nullable = false)
    @Schema(description = "Nombre del producto", example = "Gomitas")
    private String nombre;

    @Column(name = "categoria", nullable = false)
    @Schema(description = "Categoría del producto", example = "Dulces")
    private String categoria;

    @Column(name = "descripcion")
    @Schema(description = "Descripción del producto", example = "Gomitas de frutas surtidas")
    private String descripcion;

    @Column(name = "precio_venta", nullable = false)
    @Schema(description = "Precio de venta del producto", example = "15.50")
    private BigDecimal precioVenta;

    @Column(name = "peso_cantidad")
    @Schema(description = "Peso o cantidad del producto", example = "15ml")
    private String pesoCantidad;

    @Column(name = "stock_actual")
    @Schema(description = "Cantidad existentes del producto", example = "10")
    private Integer stockActual;

    @Column(name = "stock_minimo")
    @Schema(description = "Cantidad minima de ecistencias del producto", example = "2")
    private Integer stockMinimo;

    @Column(name = "fecha_caducidad")
    @Schema(description = "Fecha en la que se vence el producto", example = "2012-10-10")
    @Temporal(TemporalType.DATE)
    private Date fechaCaducidad;

    @Column(name = "fecha_registro", insertable = false, updatable = false)
    @Schema(description = "Fecha en la que se registro el producto", example = "2024-12-03")
    private Timestamp fechaRegistro;

    @Column(name = "estatus")

    @Schema(description = "Estado activo (1) o Inactivo (0) del producto", example = "1")
    private Integer estatus;

    // Constructores
    public Producto() {}

    public Producto(String nombre, String categoria, BigDecimal precioVenta) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.precioVenta = precioVenta;
    }

    // Getters y Setters (los mismos que en tu código original)
    // ... (copiar todos los getters y setters del código anterior)

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(BigDecimal precioVenta) {
        this.precioVenta = precioVenta;
    }

    public String getPesoCantidad() {
        return pesoCantidad;
    }

    public void setPesoCantidad(String pesoCantidad) {
        this.pesoCantidad = pesoCantidad;
    }

    public Integer getStockActual() {
        return stockActual;
    }

    public void setStockActual(Integer stockActual) {
        this.stockActual = stockActual;
    }

    public Integer getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(Integer stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public Timestamp getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Timestamp fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Integer getEstatus() {
        return estatus;
    }

    public void setEstatus(Integer estatus) {
        this.estatus = estatus;
    }
    
    

    // Métodos equals, hashCode y toString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return Objects.equals(idProducto, producto.idProducto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProducto);
    }

    @Override
    public String toString() {
        return "Producto{" +
                "idProducto=" + idProducto +
                ", nombre='" + nombre + '\'' +
                ", categoria='" + categoria + '\'' +
                ", precioVenta=" + precioVenta +
                ", stockActual=" + stockActual +
                '}';
    }
}