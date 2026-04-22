/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Bulonera.logica;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author tobi2
 */
@Entity
public class pagoDetalle implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long idPagoDet;
    
    private Double montoPagado;
    
    @ManyToOne
    private detalle_remito detPago;
    
    @ManyToOne
    private pago pagoDet;

    public pagoDetalle(long idPagoDet, Double montoPagado, detalle_remito detPago, pago pagoDet) {
        this.idPagoDet = idPagoDet;
        this.montoPagado = montoPagado;
        this.detPago = detPago;
        this.pagoDet = pagoDet;
    }

    public pagoDetalle() {
       
    }

    public long getIdPagoDet() {
        return idPagoDet;
    }

    public void setIdPagoDet(long idPagoDet) {
        this.idPagoDet = idPagoDet;
    }

    public Double getMontoPagado() {
        return montoPagado;
    }

    public void setMontoPagado(Double montoPagado) {
        this.montoPagado = montoPagado;
    }

    public detalle_remito getDetPago() {
        return detPago;
    }

    public void setDetPago(detalle_remito detPago) {
        this.detPago = detPago;
    }

    public pago getPagoDet() {
        return pagoDet;
    }

    public void setPagoDet(pago pagoDet) {
        this.pagoDet = pagoDet;
    }
    
    
}
