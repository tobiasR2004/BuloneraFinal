/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Bulonera.logica;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Alumno
 */
@Entity
public class detalle_remito implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int id_remito;
    @Basic
    private double precio_unit, importe, importe_total;
    private String nomb_prod;
    private double cant_prod;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaDet;
    private String cod_prod;
    @ManyToOne
    private cabecera_remito cabecdetalleremito;
    
    @ManyToOne
    private producto producDetalle;
    

    public detalle_remito()
    {
    }

    public detalle_remito(int id_remito, double precio_unit, double importe, double importe_total, String nomb_prod, double cant_prod, Date fechaDet, String cod_prod, cabecera_remito cabecdetalleremito, producto producDetalle) {
        this.id_remito = id_remito;
        this.precio_unit = precio_unit;
        this.importe = importe;
        this.importe_total = importe_total;
        this.nomb_prod = nomb_prod;
        this.cant_prod = cant_prod;
        this.fechaDet = fechaDet;
        this.cod_prod = cod_prod;
        this.cabecdetalleremito = cabecdetalleremito;
        this.producDetalle = producDetalle;
    }

    public String getCod_prod() {
        return cod_prod;
    }

    public void setCod_prod(String cod_prod) {
        this.cod_prod = cod_prod;
    }

    public Date getFechaDet() {
        return fechaDet;
    }

    public void setFechaDet(Date fechaDet) {
        this.fechaDet = fechaDet;
    }

    public int getId_remito() {
        return id_remito;
    }

    public void setId_remito(int id_remito) {
        this.id_remito = id_remito;
    }

    public double getPrecio_unit() {
        return precio_unit;
    }

    public void setPrecio_unit(double precio_unit) {
        this.precio_unit = precio_unit;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public double getImporte_total() {
        return importe_total;
    }

    public void setImporte_total(double importe_total) {
        this.importe_total = importe_total;
    }

    public String getNomb_prod() {
        return nomb_prod;
    }

    public void setNomb_prod(String nomb_prod) {
        this.nomb_prod = nomb_prod;
    }

    public double getCant_prod() {
        return cant_prod;
    }

    public void setCant_prod(double cant_prod) {
        this.cant_prod = cant_prod;
    }

    public cabecera_remito getCabecdetalleremito() {
        return cabecdetalleremito;
    }

    public void setCabecdetalleremito(cabecera_remito cabecdetalleremito) {
        this.cabecdetalleremito = cabecdetalleremito;
    }

    public producto getProducDetalle() {
        return producDetalle;
    }

    public void setProducDetalle(producto producDetalle) {
        this.producDetalle = producDetalle;
    }
      
    
}
