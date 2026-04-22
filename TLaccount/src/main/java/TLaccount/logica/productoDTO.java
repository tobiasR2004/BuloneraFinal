/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Bulonera.logica;

/**
 *
 * @author Lichi
 */
public class productoDTO {
    private String cod_prod;
    private String nomb_prod;

    public productoDTO(String cod_prod, String nomb_prod) {
        this.cod_prod = cod_prod;
        this.nomb_prod = nomb_prod;
    }

    public String getCod_prod() {
        return cod_prod;
    }

    public String getNomb_prod() {
        return nomb_prod;
    }
}
