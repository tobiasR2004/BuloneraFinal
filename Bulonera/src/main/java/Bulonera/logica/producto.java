/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Bulonera.logica;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Alumno
 */
@Entity
public class producto implements Serializable
{
    @Id
    private int id_prod;
    @Basic
    private String cod_prod;
    private double precio_compra, precio_venta;
    private String nomb_prod, categoria_prod;
    
    @OneToMany(mappedBy="producDetalle")
      private List<detalle_remito> detalles;

    public producto()
    {
    }

    public producto(int id_prod, String cod_prod, double precio_compra, double precio_venta, String nomb_prod, String categoria_prod) {
        this.id_prod = id_prod;
        this.cod_prod = cod_prod;
        this.precio_compra = precio_compra;
        this.precio_venta = precio_venta;
        this.nomb_prod = nomb_prod;
        this.categoria_prod = categoria_prod;
    }

    public List<detalle_remito> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<detalle_remito> detalles) {
        this.detalles = detalles;
    }

    public int getId_prod() {
        return id_prod;
    }

    public void setId_prod(int id_prod) {
        this.id_prod = id_prod;
    }
    
    public String getCod_prod() {
        return cod_prod;
    }

    public void setCod_prod(String cod_prod) {
        this.cod_prod = cod_prod;
    }

    public double getPrecio_compra() {
        return precio_compra;
    }

    public void setPrecio_compra(double precio_compra) {
        this.precio_compra = precio_compra;
    }

    public double getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(double precio_venta) {
        this.precio_venta = precio_venta;
    }

    public String getNomb_prod() {
        return nomb_prod;
    }

    public void setNomb_prod(String nomb_prod) {
        this.nomb_prod = nomb_prod;
    }

    public String getCategoria_prod() {
        return categoria_prod;
    }

    public void setCategoria_prod(String categoria_prod) {
        this.categoria_prod = categoria_prod;
    }
    
    
    
}
