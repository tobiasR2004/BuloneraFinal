/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Bulonera.Servlet;

import Bulonera.logica.cabecera_remito;
import Bulonera.logica.controladoraLogica;
import Bulonera.logica.detalle_remito;
import Bulonera.logica.producto;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author tobi2
 */
@WebServlet(name = "svActualizarDetalle", urlPatterns = {"/svActualizarDetalle"})
public class svActualizarDetalle extends HttpServlet {
    controladoraLogica ctrl = new controladoraLogica();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         processRequest(request, response);

    // Obtener la lista de detalles de remitos
    List<detalle_remito> detalleList = ctrl.consultarDetalleList();
    
    // Arreglos para almacenar los productos e ids
    producto[] idprod = new producto[detalleList.size()];
    int[] intIdProd = new int[detalleList.size()];
    
    int i = 0;
    boolean actexitosa = false;  // Se inicializa como false

    // Recorrer cada detalle de remito
    for (detalle_remito detalle : detalleList) {
        if (detalle.getProducDetalle() != null) {  // Verificar si tiene un producto asociado
            producto prod = detalle.getProducDetalle();
            cabecera_remito cabec = detalle.getCabecdetalleremito();
            
            int cantprod = detalle.getCant_prod();
            
            // Obtener el id y precio del producto
            int intIdCabec = cabec.getIdRemito();
            intIdProd[i] = prod.getId_prod();
            Double precio = prod.getPrecio_venta();
            Double importenuevo = precio * cantprod;

            
            // Llamar al controlador para actualizar el precio
            ctrl.actPrecioDetalle(intIdProd[i], precio, importenuevo);
            ctrl.actimportetotal( intIdCabec);
            
            // Indicar que se ha realizado una actualización exitosa
            actexitosa = true;
        } else {
            // Si no tiene producto asociado, mostrar mensaje en consola
            System.out.println("No se encontró producto para el detalle de remito ID: " + detalle.getId_remito());
        }
        
        i++;  // Avanzar al siguiente índice del arreglo
    }

    // Dependiendo si hubo actualizaciones exitosas, establecer el mensaje adecuado
    if (actexitosa) {
        request.setAttribute("error", "Actualización exitosa");
    } else {
        request.setAttribute("error", "No se encontraron remitos con productos para actualizar");
    }
    
    // Redirigir a la página correspondiente
    request.getRequestDispatcher("cuentaCorriente.jsp").forward(request, response);
}

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
