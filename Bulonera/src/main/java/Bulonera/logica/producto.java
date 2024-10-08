/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Bulonera.logica;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Alumno
 */
@Entity
public class producto implements Serializable
{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id_prod;
    @Basic
    private int cod_prod;
    private int precio_compra, precio_venta;
    private String nomb_prod, categoria_prod;

    public producto()
    {
    }
    
    public producto(int id_prod, int cod_prod, int precio_compra, int precio_venta, String nomb_prod, String categoria_prod)
    {
        this.id_prod = id_prod;
        this.cod_prod = cod_prod;
        this.precio_compra = precio_compra;
        this.precio_venta = precio_venta;
        this.nomb_prod = nomb_prod;
        this.categoria_prod = categoria_prod;
    }

    public int getId_prod() {
        return id_prod;
    }

    public void setId_prod(int id_prod) {
        this.id_prod = id_prod;
    }
    
    public int getCod_prod() {
        return cod_prod;
    }

    public void setCod_prod(int cod_prod) {
        this.cod_prod = cod_prod;
    }

    public int getPrecio_compra() {
        return precio_compra;
    }

    public void setPrecio_compra(int precio_compra) {
        this.precio_compra = precio_compra;
    }

    public int getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(int precio_venta) {
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
