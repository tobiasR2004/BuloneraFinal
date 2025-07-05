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
import javax.persistence.TemporalType;

/**
 *
 * @author Alumno
 */
@Entity
public class pago implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int id_pago;
    @Basic
    private double importe_pago;
    private String formaPago;
    private int nroCheque;
    private String bancoCheque;   
    @Temporal(TemporalType.DATE)
    private Date fecha_pago;
    @Temporal(TemporalType.DATE)
    private Date fechaPagoCheque;
    
    @ManyToOne
    private cliente cliente_pago;
    @ManyToOne
    private cuenta_corriente cc_pago;

    public pago()
    {
    }

    public pago(int id_pago, double importe_pago, String formaPago, int nroCheque, String bancoCheque, Date fecha_pago, Date fechaPagoCheque, cliente cliente_pago, cuenta_corriente cc_pago) {
        this.id_pago = id_pago;
        this.importe_pago = importe_pago;
        this.formaPago = formaPago;
        this.nroCheque = nroCheque;
        this.bancoCheque = bancoCheque;
        this.fecha_pago = fecha_pago;
        this.fechaPagoCheque = fechaPagoCheque;
        this.cliente_pago = cliente_pago;
        this.cc_pago = cc_pago;
    }

    public cuenta_corriente getCc_pago() {
        return cc_pago;
    }

    public void setCc_pago(cuenta_corriente cc_pago) {
        this.cc_pago = cc_pago;
    }

    public cliente getCliente_pago() {
        return cliente_pago;
    }

    public void setCliente_pago(cliente cliente_pago) {
        this.cliente_pago = cliente_pago;
    }
    
    public int getId_pago() {
        return id_pago;
    }

    public void setId_pago(int id_pago) {
        this.id_pago = id_pago;
    }

    public double getImporte_pago() {
        return importe_pago;
    }

    public void setImporte_pago(double importe_pago) {
        this.importe_pago = importe_pago;
    }

    public Date getFecha_pago() {
        return fecha_pago;
    }

    public void setFecha_pago(Date fecha_pago) {
        this.fecha_pago = fecha_pago;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public long getNroCheque() {
        return nroCheque;
    }

    public void setNroCheque(int nroCheque) {
        this.nroCheque = nroCheque;
    }

    public String getBancoCheque() {
        return bancoCheque;
    }

    public void setBancoCheque(String bancoCheque) {
        this.bancoCheque = bancoCheque;
    }

    public Date getFechaPagoCheque() {
        return fechaPagoCheque;
    }

    public void setFechaPagoCheque(Date fechaPagoCheque) {
        this.fechaPagoCheque = fechaPagoCheque;
    }
    
    
    
    
    
}