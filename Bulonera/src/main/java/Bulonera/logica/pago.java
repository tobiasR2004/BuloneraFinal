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
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Alumno
 */
@Entity
public class pago implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id_pago;
    @Basic
    private int num_pago;
    private int importe_pago;
    @Temporal(TemporalType.DATE)
    private Date fecha_pago;
    
    @ManyToOne
    private cliente cliente_pago;
    @ManyToOne
    private cuenta_corriente cc_pago;

    public pago()
    {
    }

    public pago(int id_pago, int num_pago, int importe_pago, Date fecha_pago, cliente cliente_pago, cuenta_corriente cc_pago) {
        this.id_pago = id_pago;
        this.num_pago = num_pago;
        this.importe_pago = importe_pago;
        this.fecha_pago = fecha_pago;
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

    public int getNum_pago() {
        return num_pago;
    }

    public void setNum_pago(int num_pago) {
        this.num_pago = num_pago;
    }

    public int getImporte_pago() {
        return importe_pago;
    }

    public void setImporte_pago(int importe_pago) {
        this.importe_pago = importe_pago;
    }

    public Date getFecha_pago() {
        return fecha_pago;
    }

    public void setFecha_pago(Date fecha_pago) {
        this.fecha_pago = fecha_pago;
    }
    
    
    
}