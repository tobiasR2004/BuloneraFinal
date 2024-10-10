/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Bulonera.logica;

import Bulonera.Persistence.controladoraPersistencia;
import java.util.ArrayList;

/**
 *
 * @author Alumno
 */
public class controladoraLogica {
    controladoraPersistencia ctrlpersis = new controladoraPersistencia();
    
    
    public void crearcabecremito(cabecera_remito cabecera1){
        ctrlpersis.crearcabecremito(cabecera1);
    }
    
    public void eliminarcabecremito(int id){
        ctrlpersis.eliminarcabecremit(id);
    }
    
    public void modifcabecremito(cabecera_remito cabecera1){
        ctrlpersis.modifcabecremito(cabecera1);
    }
    
    public cabecera_remito consultarCabecremito(int id){
        return ctrlpersis.consulcabecremito(id);
    }
    
    public ArrayList<cabecera_remito> cosultarCabeceraRemList(){
        return ctrlpersis.consultarcabecerarem();
    }
    
    //CRUD PAGO
    public void crearPago(pago pago1){
        ctrlpersis.crearPago(pago1);
    }
    
    public void eliminarPago(int id){
        ctrlpersis.eliminarPago(id);
    }
    
    public void modifPago(pago pago1){
        ctrlpersis.modifPago(pago1);
    }
    
    public pago consultarPago(int id){
        return ctrlpersis.consultarPago(id);
    }
    
    public ArrayList<pago> cosultarPagoList(){
        return ctrlpersis.consultarPagoList();
    }
    
    
    //CRUD PRODUCTO
    public void crearProducto(producto prod1){
        ctrlpersis.crearProducto(prod1);
    }
    
    public void eliminarProducto(int id){
        ctrlpersis.eliminarProducto(id);
    }
    
    public void modifProducto(producto prod1){
        ctrlpersis.modifProducto(prod1);
    }
    
    public producto consultarProducto(int id){
        return ctrlpersis.consultarProducto(id);
    }
    
    public ArrayList<producto> consultarProductosList(){
        return ctrlpersis.consultarProductosList();
    }
    
    //CRUD USUARIO
    public void crearUsuario(usuario user1){
        ctrlpersis.crearUsuario(user1);
    }
    
    public void eliminarUsuario(int id){
        ctrlpersis.eliminarUsuario(id);
    }
    
    public void modifUsuario(usuario user1){
        ctrlpersis.modifUsuario(user1);
    }
    
    public usuario consultarUsuario(int id){
        return ctrlpersis.consultarUsuario(id);
    }
    
    public ArrayList<usuario> consultarUsuariosList(){
        return ctrlpersis.consultarUsuariosList();
    }
    
}
