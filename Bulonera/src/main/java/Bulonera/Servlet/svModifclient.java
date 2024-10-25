/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Bulonera.Servlet;

import Bulonera.logica.cliente;
import Bulonera.logica.controladoraLogica;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "svModifclient", urlPatterns = {"/svModifclient"})
public class svModifclient extends HttpServlet {

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
        int dniModif = Integer.parseInt(request.getParameter("buscarCl"));
        
        cliente cliente1 = ctrl.buscarDniCliente(dniModif);
        
        HttpSession misesion = request.getSession();
        misesion.setAttribute("clienModif", cliente1);
        
        response.sendRedirect("modifCliente.jsp");
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
            int dni = Integer.parseInt(request.getParameter("dniClModif"));
            String razonSoc= request.getParameter("razonSocClModif");
            String fechaStr= request.getParameter("fechaClModif");
            String cuit= request.getParameter("cuitClModif");
            String domicilio= request.getParameter("domicilioClModif");
           

            cliente cliente1 = (cliente) request.getSession().getAttribute("clienModif");
            
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaing = format.parse(fechaStr);
                
            cliente1.setDni_cliente(dni);
            cliente1.setRazon_social(razonSoc);
            cliente1.setFecha_ingreso(fechaing);
            cliente1.setCuit_cliente(cuit);
            cliente1.setDomicilio_cliente(domicilio);
            
            ctrl.modifCliente(cliente1);
            
        } catch (ParseException ex) {
            Logger.getLogger(svModifclient.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.sendRedirect("menu.jsp#MenuPrincipal");
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
