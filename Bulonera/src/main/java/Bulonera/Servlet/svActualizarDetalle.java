/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Bulonera.Servlet;

import Bulonera.logica.cabecera_remito;
import Bulonera.logica.controladoraLogica;
import Bulonera.logica.cuenta_corriente;
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
// 1. Actualizar las referencias de productos en detalle_remito
    ctrl.actrefDetalle();
    
    // 2. Obtener la lista de detalles de remito
    List<detalle_remito> detalleList = ctrl.consultarListaDetalles();
    boolean actexitosa = false;

    if (detalleList == null || detalleList.isEmpty()) {
        request.setAttribute("error", "No hay detalles de remito para actualizar.");
        request.getRequestDispatcher("cuentaCorriente.jsp").forward(request, response);
        return;
    }

    for (detalle_remito detalle : detalleList) {
        if (detalle.getCod_prod() != null) {
            producto prod = ctrl.consultarProductoStr(detalle.getCod_prod());

            if (prod == null) {
                System.out.println("Producto no encontrado para código: " + detalle.getCod_prod());
                continue; // Salta al siguiente detalle
            }

            cabecera_remito cabec = detalle.getCabecdetalleremito();
            if (cabec == null) {
                System.out.println("Cabecera remito es null para detalle ID: " + detalle.getId_remito());
                continue;
            }

            cuenta_corriente cC1 = ctrl.consultarCcporCabec(cabec);
            if (cC1 == null) {
                System.out.println("Cuenta corriente no encontrada para remito ID: " + cabec.getIdRemito());
                continue;
            }

            double cantprod = detalle.getCant_prod();
            String codProducto = prod.getCod_prod();
            Double precio = prod.getPrecio_venta();
            Double importenuevo = precio * cantprod;

            // Actualizar precios
            ctrl.actPrecioDetalle(codProducto, precio, importenuevo);

            // Actualizar el importe total del remito
            ctrl.actimportetotal(cabec.getIdRemito());

            // Actualizar los importes en cuenta corriente
            ctrl.actualizarImportesCc(cC1.getId_cc());

            actexitosa = true;
        }
    }

    // Mensaje para la respuesta
    if (actexitosa) {
        request.setAttribute("error", "Actualización exitosa");
    } else {
        request.setAttribute("error", "No se encontraron remitos con productos para actualizar.");
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
