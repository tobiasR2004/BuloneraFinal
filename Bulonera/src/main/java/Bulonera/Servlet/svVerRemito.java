/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Bulonera.Servlet;

import Bulonera.logica.cliente;
import Bulonera.logica.controladoraLogica;
import Bulonera.logica.detalle_remito;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "svVerRemito", urlPatterns = {"/svVerRemito"})
public class svVerRemito extends HttpServlet {
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
                        
        HttpSession misesion = request.getSession();
        String idCabec = (String) misesion.getAttribute("clienteIdSeleccionado");   
        misesion.setAttribute("idCabec", idCabec);
        
        int Cabec = Integer.parseInt(idCabec);
        
        List<detalle_remito> detalleList = (List<detalle_remito>) ctrl.consultarDetalleListCabec(Cabec);
        misesion.setAttribute("Detalles", detalleList);
                
       if(idCabec == null  || "".equals(idCabec) || "Elegir...".equals(idCabec)){
           
        request.setAttribute("error", "seleccione un Cliente");
        request.getRequestDispatcher("cuentaCorriente.jsp").forward(request, response);
        
       } else if (idCabec instanceof String){
        int idCabecint = Integer.parseInt(idCabec);
        
        cliente cliente1 = ctrl.consultarCliente(idCabecint);
        misesion.setAttribute("clientCabec", cliente1);     
        
        response.sendRedirect("remito.jsp");
        
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
