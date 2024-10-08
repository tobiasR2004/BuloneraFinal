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
public class cuenta_corriente implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id_cc;
    @Basic
    private int debe_cc, haber_cc, saldo_cc;
    @Temporal(TemporalType.DATE)
    private Date fecha_cc;
    
    @OneToMany
    private ArrayList<pago> listaPagos_cc;

    public cuenta_corriente()
    {
    }

    public cuenta_corriente(int id_cc, int debe_cc, int haber_cc, int saldo_cc, Date fecha_cc, ArrayList<pago> listaPagos_cc) {
        this.id_cc = id_cc;
        this.debe_cc = debe_cc;
        this.haber_cc = haber_cc;
        this.saldo_cc = saldo_cc;
        this.fecha_cc = fecha_cc;
        this.listaPagos_cc = listaPagos_cc;
    }

    public ArrayList<pago> getListaPagos_cc() {
        return listaPagos_cc;
    }

    public void setListaPagos_cc(ArrayList<pago> listaPagos_cc) {
        this.listaPagos_cc = listaPagos_cc;
    }

    public int getId_cc() {
        return id_cc;
    }

    public void setId_cc(int id_cc) {
        this.id_cc = id_cc;
    }

    public int getDebe_cc() {
        return debe_cc;
    }

    public void setDebe_cc(int debe_cc) {
        this.debe_cc = debe_cc;
    }

    public int getHaber_cc() {
        return haber_cc;
    }

    public void setHaber_cc(int haber_cc) {
        this.haber_cc = haber_cc;
    }

    public int getSaldo_cc() {
        return saldo_cc;
    }

    public void setSaldo_cc(int saldo_cc) {
        this.saldo_cc = saldo_cc;
    }

    public Date getFecha_cc() {
        return fecha_cc;
    }

    public void setFecha_cc(Date fecha_cc) {
        this.fecha_cc = fecha_cc;
    }

    public void add(cuenta_corriente cuenta_corriente) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void remove(cuenta_corriente cuenta_corriente) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
}