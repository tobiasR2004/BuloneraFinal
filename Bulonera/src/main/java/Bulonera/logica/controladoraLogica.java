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
    
    //CRUD CABECERA REMITO
    
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
   
    // CRUD CLIENTE
    
    public void crearCliente(cliente cliente1){
        ctrlpersis.crearCliente(cliente1);
    }
    
    public void eliminarCliente(int id){
        ctrlpersis.eliminarCliente(id);
    }
    
    public void modifCliente(cliente cliente1){
        ctrlpersis.modifCliente(cliente1);
    }
    
    public cliente consultarCliente(int id){
        return ctrlpersis.consultarcliente(id);
    }
    
    public ArrayList<cliente> consultarClienteList(){
        return ctrlpersis.consultarClienList();
    }
    
    //CRUD CUENTA_CORRIENTE
    
    public void crearCc(cuenta_corriente cC1){
        ctrlpersis.crearCc(cC1);  
    }
    
    public void eliminarCc(int id){
        ctrlpersis.eliminarCc(id);
    }
    
    public void modifCc(cuenta_corriente cC1) {
        ctrlpersis.modifCc(cC1);
    }
    
    public cuenta_corriente consultarCc(int id){
        return ctrlpersis.consultarCc(id);
    }
    
    public ArrayList<cuenta_corriente> consultarCcList(){
        return ctrlpersis.consultarCcList();
    }
    
    //CRUD DETALLE
    
    public void crearDetalle(detalle_remito detalle1){
        ctrlpersis.crearDetalle(detalle1);
    }
    
    public void eliminarDetalle(int id){
        ctrlpersis.eliminarDetalle(id);
    }
    
    public void modifDetalle(detalle_remito detalle1){
        ctrlpersis.modifDetalle(detalle1);
    }
    
    public detalle_remito verDetalle(int id){
        return ctrlpersis.consultarDetalle(id);
    }
    
    public ArrayList<detalle_remito> consultarDetalleList(){
        return ctrlpersis.consultarDetalleList();
    }
    
} 
