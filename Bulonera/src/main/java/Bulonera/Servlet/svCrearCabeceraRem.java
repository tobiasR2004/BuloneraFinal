/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Bulonera.Servlet;

import Bulonera.logica.cabecera_remito;
import Bulonera.logica.cliente;
import Bulonera.logica.controladoraLogica;
import Bulonera.logica.detalle_remito;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tobi2
 */
@WebServlet(name = "svCrearCabeceraRem", urlPatterns = {"/svCrearCabeceraRem"})
public class svCrearCabeceraRem extends HttpServlet {
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
        response.setContentType("text/html;charset=UTF-8");
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
            HttpSession misesion = request.getSession();
            String idCabec = (String) misesion.getAttribute("clienteIdSeleccionado");   
            misesion.setAttribute("idCabec", idCabec);
            

            if (idCabec == null || "".equals(idCabec) || "Elegir...".equals(idCabec)) {
                request.setAttribute("errorCabec", "Por favor... seleccione un Cliente");
                request.getRequestDispatcher("cuentaCorriente.jsp").forward(request, response);
            } else {
                try {
                    int idCabecint = Integer.parseInt(idCabec);
                    // Guarda el booleano en la sesión para abrir el modal
                    misesion.setAttribute("abrirModal", true);

                    // Ejecuta la consulta del cliente
                    cliente cli = ctrl.consultarCliente(idCabecint);
                    misesion.setAttribute("clienteCabec", cli);
                    
                    // Redirige a cuentaCorriente.jsp para abrir el modal
                    misesion.setAttribute("abrirModal", true);
                    response.sendRedirect("sVcuentaCorrienteRemito?buscarCli=" + idCabec);
                    
                } catch (NumberFormatException e) {
                    request.setAttribute("errorCabec", "El ID de cliente no es válido.");
                    request.getRequestDispatcher("cuentaCorriente.jsp").forward(request, response);
                }
            }
        
        
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
        try {
            processRequest(request, response);
            HttpSession misesion = request.getSession();
            String nroClientStr = (String) misesion.getAttribute("clienteIdSeleccionado");
            Date fechaStr = new java.util.Date();
            
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            Date fechaFormateada = formato.parse(formato.format(fechaStr));
            
            misesion.setAttribute("nroClientStr", nroClientStr);
            
            int nroClient = Integer.parseInt(nroClientStr);
           
            
            cabecera_remito cabec = new cabecera_remito();
            cliente cli = ctrl.consultarCliente(nroClient);
            
            List<detalle_remito> listaCabec = null;
            
            cabec.setCuit_cliente(cli.getCuit_cliente());
            cabec.setListadetalles((ArrayList<detalle_remito>) listaCabec);
            cabec.setRazon_social(cli.getRazon_social());
            cabec.setFecha_Rem(fechaFormateada);
            cabec.setImporte_total(0);
            cabec.setClienteCabecera(cli);
            
            ctrl.crearcabecremito(cabec);
            
            response.sendRedirect("sVcuentaCorrienteRemito?buscarCli=" + nroClientStr);
        } catch (Exception ex) {
            Logger.getLogger(svCrearCabeceraRem.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
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
