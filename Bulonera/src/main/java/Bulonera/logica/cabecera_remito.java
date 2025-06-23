/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Bulonera.logica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Alumno
 */
@Entity
public class cabecera_remito implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int idRemito;
    @Basic
    private String cuit_cliente;
    private String razon_social;
    private double importe_total;
    @Temporal(TemporalType.DATE)
    private Date fecha_Rem;
    @ManyToOne
    @JoinColumn(name = "ID_CLIENTE")
    private cliente clienteCabecera;
    
    @OneToMany(mappedBy = "cabeceraremito", cascade = CascadeType.ALL, orphanRemoval = true)
    private ArrayList<cuenta_corriente> listaCc;
    
    @OneToMany(mappedBy = "cabecdetalleremito", cascade = CascadeType.ALL, orphanRemoval = true)
    private ArrayList<detalle_remito> listadetalles;
    

    public cabecera_remito(int idRemito, String cuit_cliente, String razon_social, double importe_total, Date fecha_Rem, cliente clienteCabecera, ArrayList<detalle_remito> listadetalles) {
        this.idRemito = idRemito;
        this.cuit_cliente = cuit_cliente;
        this.razon_social = razon_social;
        this.importe_total = importe_total;
        this.fecha_Rem = fecha_Rem;
        this.clienteCabecera = clienteCabecera;
        this.listadetalles = listadetalles;
    }

    public cabecera_remito() {
    }

    public int getIdRemito() {
        return idRemito;
    }

    public void setIdRemito(int idRemito) {
        this.idRemito = idRemito;
    }

    public String getCuit_cliente() {
        return cuit_cliente;
    }

    public void setCuit_cliente(String cuit_cliente) {
        this.cuit_cliente = cuit_cliente;
    }

    public String getRazon_social() {
        return razon_social;
    }

    public void setRazon_social(String razon_social) {
        this.razon_social = razon_social;
    }

    public double getImporte_total() {
        return importe_total;
    }

    public void setImporte_total(double importe_total) {
        this.importe_total = importe_total;
    }

    public Date getFecha_Rem() {
        return fecha_Rem;
    }

    public void setFecha_Rem(Date fecha_Rem) {
        this.fecha_Rem = fecha_Rem;
    }

    public cliente getClienteCabecera() {
        return clienteCabecera;
    }

    public void setClienteCabecera(cliente clienteCabecera) {
        this.clienteCabecera = clienteCabecera;
    }

    public ArrayList<detalle_remito> getListadetalles() {
        return listadetalles;
    }

    public void setListadetalles(ArrayList<detalle_remito> listadetalles) {
        this.listadetalles = listadetalles;
    }

    public ArrayList<cuenta_corriente> getListaCc() {
        return listaCc;
    }

    public void setListaCc(ArrayList<cuenta_corriente> listaCc) {
        this.listaCc = listaCc;
    }
    
}