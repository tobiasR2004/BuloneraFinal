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
    
    public cabecera_remito consulcabecremito(int id){
        return ctrlpersis.consulcabecremito(id);
    }
    
    public ArrayList<cabecera_remito> cosultarcabeceraremlist(){
        return ctrlpersis.consultarcabecerarem();
    }
    
}
