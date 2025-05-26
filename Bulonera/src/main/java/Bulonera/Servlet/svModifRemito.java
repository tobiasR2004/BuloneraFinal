/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Bulonera.Servlet;

import Bulonera.logica.cabecera_remito;
import Bulonera.logica.controladoraLogica;
import Bulonera.logica.detalle_remito;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author tobi2
 */
@WebServlet(name = "svModifRemito", urlPatterns = {"/svModifRemito"})
public class svModifRemito extends HttpServlet {

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
        {

        }
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
        String[] seleccionados = request.getParameterValues("remitosSeleccionados");

        if (seleccionados != null) {
            for (String idStr : seleccionados) {
                try {
                    int id = Integer.parseInt(idStr);

                    // Nombre del parámetro: cantidad_ + id
                    String cantidadParam = request.getParameter("cantidad_" + id);

                    if (cantidadParam != null && !cantidadParam.isEmpty()) {
                        int nuevaCantidad = Integer.parseInt(cantidadParam);

                        // Buscar el detalle_remito desde la base de datos
                        detalle_remito dr = ctrl.verDetalle(id);
                        cabecera_remito idCabecdr = dr.getCabecdetalleremito();
                        double nuevImporte = (dr.getPrecio_unit()*nuevaCantidad);
                        double nuevImporteTot = (dr.getImporte_total() - dr.getImporte());
                        nuevImporteTot = nuevImporte + nuevImporteTot;


                        if (dr != null) {
                            
                            dr.setImporte(nuevImporte);
                            dr.setCant_prod(nuevaCantidad);
                            ctrl.modifDetalle(dr); // este método debe hacer em.merge(dr)
                            ctrl.actimportetotal( idCabecdr.getIdRemito());
                        }
                    }
                } catch (NumberFormatException e) {
                    // manejar error en id o cantidad
                    e.printStackTrace();
                }
            }
        }

        response.sendRedirect("remito.jsp");
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
