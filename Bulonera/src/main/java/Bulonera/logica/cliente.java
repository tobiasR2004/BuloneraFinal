/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Bulonera.logica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Alumno
 */
@Entity
@Table(name = "cliente") 
public class cliente implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int nroClient;
    @Basic
    @Column(name = "dni_cliente")  // Mapeo si el nombre en la tabla es diferente
    private int dniCliente;
    private String cuit_cliente;
    @Column(name = "razon_social") 
    private String razonSocial;
    private String domicilio_cliente;
    @Temporal(TemporalType.DATE)
    private Date fecha_ingreso;
    
    @OneToMany(mappedBy = "cliente_pago")
    private ArrayList<pago> listaPagos_c;
    
    @OneToMany(mappedBy = "clienteCabecera", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<cabecera_remito> remitos = new ArrayList<>();

    public cliente(){
        
    }

    public cliente(int nroClient, int dni_cliente, String cuit_cliente, String razon_social, String domicilio_cliente, Date fecha_ingreso, ArrayList<pago> listaPagos_c) {
        this.nroClient = nroClient;
        this.dniCliente = dni_cliente;
        this.cuit_cliente = cuit_cliente;
        this.razonSocial = razon_social;
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
    
    public int getNroClient() {
        return nroClient;
    }

    public void setNroClient(int nro_client) {
        this.nroClient = nro_client;
    }

    public int getDni_cliente() {
        return dniCliente;
    }

    public void setDni_cliente(int dni_cliente) {
        this.dniCliente = dni_cliente;
    }

    public String getCuit_cliente() {
        return cuit_cliente;
    }

    public void setCuit_cliente(String cuit_cliente) {
        this.cuit_cliente = cuit_cliente;
    }

    public String getRazon_social() {
        return razonSocial;
    }

    public void setRazon_social(String razon_social) {
        this.razonSocial = razon_social;
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

    public List<cabecera_remito> getRemitos() {
        return remitos;
    }

    public void setRemitos(List<cabecera_remito> remitos) {
        this.remitos = remitos;
    }
    
    
}