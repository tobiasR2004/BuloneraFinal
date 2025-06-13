/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Bulonera.Servlet;

import Bulonera.logica.cabecera_remito;
import Bulonera.logica.controladoraLogica;
import Bulonera.logica.cuenta_corriente;
import Bulonera.logica.detalle_remito;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
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
    // ✅ Ordenar los IDs en orden ascendente
    Arrays.sort(seleccionados, Comparator.comparingInt(Integer::parseInt));

    for (String idStr : seleccionados) {
        try {
            int id = Integer.parseInt(idStr);

            // Nombre del parámetro: cantidad_ + id
            String cantidadParam = request.getParameter("cantidad_" + id);

            if (cantidadParam != null && !cantidadParam.isEmpty()) {
                double nuevaCantidad = Double.parseDouble(cantidadParam);

                // Buscar el detalle_remito desde la base de datos
                detalle_remito dr = ctrl.verDetalle(id);
                cabecera_remito idCabecdr = dr.getCabecdetalleremito();
                cuenta_corriente idCc = ctrl.consultarCcporCabec(idCabecdr);
                
                double nuevoImporte = dr.getPrecio_unit() * nuevaCantidad;
                double nuevoImporteTotal = dr.getImporte_total() - dr.getImporte();
                nuevoImporteTotal = nuevoImporte + nuevoImporteTotal;

                if (dr != null) {
                    // Actualizar detalle
                    dr.setImporte(nuevoImporte);
                    dr.setCant_prod(nuevaCantidad);
                    ctrl.modifDetalle(dr);

                    // Actualizar cabecera (importe total)
                    ctrl.actimportetotal(idCabecdr.getIdRemito());

                    // ✅ Actualizar cuenta corriente (orden ya garantizado)
                    ctrl.actualizarImportesCc(idCc.getId_cc());
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace(); // Manejo simple, podés mejorarlo
        }
    }
}
        
        // 🔄 ACTUALIZAR la lista antes de mostrarla en el JSP
        List<detalle_remito> listaDet = (List<detalle_remito>) request.getSession().getAttribute("DetallesList");
        List<Integer> idsDetalle = new ArrayList<>();
        List<detalle_remito> listaDetalles = new ArrayList<>();

        if (listaDet != null && !listaDet.isEmpty()) {
            for (detalle_remito detalle : listaDet) {
                idsDetalle.add(detalle.getId_remito());
            }
            for (Integer id : idsDetalle) {
                detalle_remito detalle = ctrl.verDetalle(id);
                if (detalle != null) {
                    listaDetalles.add(detalle);
                } else {
                    // Opcional: log o manejar IDs no encontrados
                    System.out.println("No se encontró detalle con ID: " + id);
                }
            }
        } else {
            System.out.println("La lista recuperada de sesión está vacía o es nula.");
        }

        // Enviar a JSP con los datos cargados
        request.getSession().setAttribute("DetallesList", listaDetalles);
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
