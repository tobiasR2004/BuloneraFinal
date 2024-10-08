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
public class detalle_remito implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id_remito;
    @Basic
    private int cant_prod, precio_unit, importe, importe_total;
    private String nomb_prod;

    public detalle_remito()
    {
    }
    
    public detalle_remito(int id_remito, int cant_prod, int precio_unit, int importe, int importe_total, String nomb_prod)
    {
        this.id_remito = id_remito;
        this.cant_prod = cant_prod;
        this.precio_unit = precio_unit;
        this.importe = importe;
        this.importe_total = importe_total;
        this.nomb_prod = nomb_prod;
    }

    public int getId_remito() {
        return id_remito;
    }

    public void setId_remito(int id_remito) {
        this.id_remito = id_remito;
    }

    public int getCant_prod() {
        return cant_prod;
    }

    public void setCant_prod(int cant_prod) {
        this.cant_prod = cant_prod;
    }

    public int getPrecio_unit() {
        return precio_unit;
    }

    public void setPrecio_unit(int precio_unit) {
        this.precio_unit = precio_unit;
    }

    public int getImporte() {
        return importe;
    }

    public void setImporte(int importe) {
        this.importe = importe;
    }

    public int getImporte_total() {
        return importe_total;
    }

    public void setImporte_total(int importe_total) {
        this.importe_total = importe_total;
    }

    public String getNomb_prod() {
        return nomb_prod;
    }

    public void setNomb_prod(String nomb_prod) {
        this.nomb_prod = nomb_prod;
    }
    
    
    
}
