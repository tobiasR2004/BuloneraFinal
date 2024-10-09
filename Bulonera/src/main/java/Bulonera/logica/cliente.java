/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Bulonera.logica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Alumno
 */
@Entity
public class cliente implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int nro_client;
    @Basic
    private int dni_cliente, cuit_cliente;
    private String razon_social, domicilio_cliente;
    @Temporal(TemporalType.DATE)
    private Date fecha_ingreso;
    
    @OneToMany(mappedBy = "client")
    private ArrayList<pago> listaPagos_c;

    public cliente(){
        
    }

    public cliente(int nro_client, int dni_cliente, int cuit_cliente, String razon_social, String domicilio_cliente, Date fecha_ingreso, ArrayList<pago> listaPagos_c) {
        this.nro_client = nro_client;
        this.dni_cliente = dni_cliente;
        this.cuit_cliente = cuit_cliente;
        this.razon_social = razon_social;
        this.domicilio_cliente = domicilio_cliente;
        this.fecha_ingreso = fecha_ingreso;
        this.listaPagos_c = listaPagos_c;
    }

    public ArrayList<pago> getListaPagos_c() {
        return listaPagos_c;
    }

    public void setListaPagos_c(ArrayList<pago> listaPagos_c) {
        this.listaPagos_c = listaPagos_c;
    }
    
    public int getNro_client() {
        return nro_client;
    }

    public void setNro_client(int nro_client) {
        this.nro_client = nro_client;
    }

    public int getDni_cliente() {
        return dni_cliente;
    }

    public void setDni_cliente(int dni_cliente) {
        this.dni_cliente = dni_cliente;
    }

    public int getCuit_cliente() {
        return cuit_cliente;
    }

    public void setCuit_cliente(int cuit_cliente) {
        this.cuit_cliente = cuit_cliente;
    }

    public String getRazon_social() {
        return razon_social;
    }

    public void setRazon_social(String razon_social) {
        this.razon_social = razon_social;
    }

    public String getDomicilio_cliente() {
        return domicilio_cliente;
    }

    public void setDomicilio_cliente(String domicilio_cliente) {
        this.domicilio_cliente = domicilio_cliente;
    }

    public Date getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(Date fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public void add(cliente cliente) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void remove(cliente cliente) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
}