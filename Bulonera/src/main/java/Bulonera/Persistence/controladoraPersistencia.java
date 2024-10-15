/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Bulonera.Persistence;

import Bulonera.Persistence.exceptions.NonexistentEntityException;
import Bulonera.logica.cabecera_remito;
import Bulonera.logica.cliente;
import Bulonera.logica.cuenta_corriente;
import Bulonera.logica.detalle_remito;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tobi2
 */
public class controladoraPersistencia {

    cabecera_remitoJpaController cabecera_remitoJpa = new cabecera_remitoJpaController();
    clienteJpaController clienteJpa = new clienteJpaController();
    cuenta_corrienteJpaController cuenta_corrienteJpa = new cuenta_corrienteJpaController();
    detalle_remitoJpaController detalle_remitoJpa = new detalle_remitoJpaController();
    pagoJpaController pagoJpa = new pagoJpaController();
    productoJpaController productoJpa = new productoJpaController();
    usuarioJpaController usuarioJpa = new usuarioJpaController();
    
    public controladoraPersistencia() {
    }
    
    //CRUD CABECERA_REMITO
    
     public void crearcabecremito(cabecera_remito idcabec) {
           cabecera_remitoJpa.create(idcabec); 
    }

    public void eliminarcabecremit(int id) {
       try{
          cabecera_remitoJpa.destroy(id);
       } catch(NonexistentEntityException ex) {
            Logger.getLogger(controladoraPersistencia.class.getName()).log(Level.SEVERE,null, ex);
       }
    }

    public void modifcabecremito(cabecera_remito idcabec) {
        try {
            cabecera_remitoJpa.edit(idcabec);      
        } catch (Exception ex){
           Logger.getLogger(controladoraPersistencia.class.getName()).log(Level.SEVERE,null, ex);
        }
    }

    public cabecera_remito consulcabecremito(int id) {
        return cabecera_remitoJpa.findcabecera_remito(id);
    }

    public ArrayList<cabecera_remito> consultarcabecerarem() {
        List<cabecera_remito> listaC = cabecera_remitoJpa.findcabecera_remitoEntities();
        ArrayList<cabecera_remito> listacabecremi = new ArrayList<cabecera_remito>(listaC);
        return listacabecremi;
    }

    
    //CRUD CLIENTE
    
    public void crearCliente(cliente cliente1) {
        clienteJpa.create(cliente1);
    }

    public void eliminarCliente(int id) {
       try{
         clienteJpa.destroy(id);
       } catch(NonexistentEntityException ex) {
            Logger.getLogger(controladoraPersistencia.class.getName()).log(Level.SEVERE,null, ex);
       }
    }

    public void modifCliente(cliente cliente1) {
        try {
           clienteJpa.edit(cliente1);      
        } catch (Exception ex){
           Logger.getLogger(controladoraPersistencia.class.getName()).log(Level.SEVERE,null, ex);
        }
    }

    public cliente consultarcliente(int id) {
        return clienteJpa.findcliente(id);
    }

    public ArrayList<cliente> consultarClienList() {
        List<cliente> listaClient = clienteJpa.findclienteEntities();
        ArrayList<cliente> listaClientes = new ArrayList<cliente>(listaClient);
        return listaClientes;
    }
    
    //CRUD CUENTACORRIENTE

    public void crearCc(cuenta_corriente cC) {
        cuenta_corrienteJpa.create(cC);
    }

    public void eliminarCc(int id) {
        try {
            cuenta_corrienteJpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(controladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modifCc(cuenta_corriente cC1) {
        try {
            cuenta_corrienteJpa.edit(cC1);
        } catch (Exception ex) {
            Logger.getLogger(controladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public cuenta_corriente consultarCc(int id) {
        return cuenta_corrienteJpa.findcuenta_corriente(id);
    }

    public ArrayList<cuenta_corriente> consultarCcList() {
        List<cuenta_corriente> listaCuentacorr = cuenta_corrienteJpa.findcuenta_corrienteEntities();
        ArrayList<cuenta_corriente> listaCc = new ArrayList<cuenta_corriente>(listaCuentacorr);
        return listaCc;
    }

    //CRUD DETALLE REMITO
    
    public void crearDetalle(detalle_remito detalle1) {
        detalle_remitoJpa.create(detalle1);
    }

    public void eliminarDetalle(int id) {
        try {
            detalle_remitoJpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(controladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modifDetalle(detalle_remito detalle1) {
        try {
            detalle_remitoJpa.edit(detalle1);
        } catch (Exception ex) {
            Logger.getLogger(controladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public detalle_remito consultarDetalle(int id) {
      return detalle_remitoJpa.finddetalle_remito(id);
    }

    public ArrayList<detalle_remito> consultarDetalleList() {
       List<detalle_remito> listadet = detalle_remitoJpa.finddetalle_remitoEntities();
       ArrayList<detalle_remito> listadetalle = new ArrayList<detalle_remito>(listadet);
       return listadetalle;
    }
}
