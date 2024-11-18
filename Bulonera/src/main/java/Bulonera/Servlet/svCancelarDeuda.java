/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Bulonera.Servlet;

import Bulonera.logica.cabecera_remito;
import Bulonera.logica.cliente;
import Bulonera.logica.controladoraLogica;
import Bulonera.logica.cuenta_corriente;
import Bulonera.logica.pago;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author tobi2
 */
@WebServlet(name = "svCancelarDeuda", urlPatterns = {"/svCancelarDeuda"})
public class svCancelarDeuda extends HttpServlet {
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
        
         HttpSession misesion = request.getSession();
        String idCabec = (String) misesion.getAttribute("clienteIdSeleccionado");   
        String importePago = request.getParameter("cancelDeuda");
        int nroClient = Integer.parseInt(idCabec);
        List<cabecera_remito> cabecList = (List<cabecera_remito>) ctrl.consultarCabecNroClient(nroClient);
        double importepago = Double.parseDouble(importePago); 
        
        if (importePago == null || importePago.trim().isEmpty()) {
             // Manejar el caso de valor no válido
            request.setAttribute("errorCabec", "Seleccione un cliente");
            request.getRequestDispatcher("cuentaCorriente.jsp").forward(request, response);
        } else if (idCabec == null || "".equals(idCabec) || "Elegir...".equals(idCabec)) {
            request.setAttribute("errorCabec", "Por favor... seleccione un Cliente");
            request.getRequestDispatcher("cuentaCorriente.jsp").forward(request, response);
            
        } else if (cabecList.isEmpty()) {
           
        request.setAttribute("errorCabec", "Por favor... seleccione un Cliente");
         request.getRequestDispatcher("cuentaCorriente.jsp").forward(request, response);
            
        } else {
            cabecera_remito ultimaCabecera = cabecList.get(cabecList.size() - 1);

            LocalDate fechaActual = LocalDate.now();
            java.sql.Date fechaSQL = java.sql.Date.valueOf(fechaActual);

            cliente cliente1 = ctrl.consultarCliente(nroClient);
            cuenta_corriente cC1 = new cuenta_corriente();
            pago pago1 = new pago();
            
            

            cC1.setCabeceraremito(ultimaCabecera);
            cC1.setFecha_cc(fechaSQL);
            cC1.setHaber_cc(importepago);
            ctrl.crearCc(cC1);
            
            pago1.setCc_pago(cC1);
            pago1.setCliente_pago(cliente1);
            pago1.setFecha_pago(fechaSQL);
            pago1.setImporte_pago(importepago);
            ctrl.crearPago(pago1);
            
            response.sendRedirect("cuentaCorriente.jsp");
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
