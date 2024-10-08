/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Bulonera.logica;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Alumno
 */
@Entity
public class usuario implements Serializable
{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int idUsuario;
    @Basic
    private String nombUsuario;
    private String contraseña;

    public usuario()
    {
    
    }
    
    public usuario(String nombUsuario, String contraseña, int idUsuario)
    {
        this.nombUsuario = nombUsuario;
        this.contraseña = contraseña;
        this.idUsuario = idUsuario;
    }

    public String getNombUsuario() {
        return nombUsuario;
    }

    public void setNombUsuario(String nombUsuario) {
        this.nombUsuario = nombUsuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    
    
}
