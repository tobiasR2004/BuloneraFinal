/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Bulonera.Persistence;

import Bulonera.Persistence.exceptions.NonexistentEntityException;
import Bulonera.logica.cabecera_remito;
import Bulonera.logica.pago;
import Bulonera.logica.producto;
import Bulonera.logica.usuario;
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

    //CRUD PAGO
    public void crearPago(pago pago1) {
        pagoJpa.create(pago1); 
    }

    public void eliminarPago(int id) {
        try {
            pagoJpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(controladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modifPago(pago pago1) {
        try {
            pagoJpa.edit(pago1);
        } catch (Exception ex) {
            Logger.getLogger(controladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public pago consultarPago(int id) {
        return pagoJpa.findpago(id);
    }

    public ArrayList<pago> consultarPagoList() {
        List<pago> listaP = pagoJpa.findpagoEntities();
        ArrayList<pago> listaPagos = new ArrayList<pago>(listaP);
        return listaPagos;
    }

    
    //CRUD PRODUCTO
    public void crearProducto(producto prod1) {
        productoJpa.create(prod1);
    }

    public void eliminarProducto(int id) {
        try {
            productoJpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(controladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modifProducto(producto prod1) {
        try {
            productoJpa.edit(prod1);
        } catch (Exception ex) {
            Logger.getLogger(controladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public producto consultarProducto(int id) {
        return productoJpa.findproducto(id);
    }

    public ArrayList<producto> consultarProductosList() {
        List<producto> listaPr = productoJpa.findproductoEntities();
        ArrayList<producto> listaProductos = new ArrayList<producto>(listaPr);
        return listaProductos;
    }

    public void crearUsuario(usuario user1) {
        usuarioJpa.create(user1);
    }

    public void eliminarUsuario(int id) {
        try {
            usuarioJpa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(controladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modifUsuario(usuario user1) {
        try {
            usuarioJpa.edit(user1);
        } catch (Exception ex) {
            Logger.getLogger(controladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public usuario consultarUsuario(int id) {
        return usuarioJpa.findusuario(id);
    }

    public ArrayList<usuario> consultarUsuariosList() {
        List<usuario> listaUs = usuarioJpa.findusuarioEntities();
        ArrayList<usuario> listaUsuarios = new ArrayList<usuario>(listaUs);
        return listaUsuarios;
    }

    
}
