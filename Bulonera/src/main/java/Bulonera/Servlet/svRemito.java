/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Bulonera.Servlet;

import Bulonera.logica.cliente;
import Bulonera.logica.controladoraLogica;
import Bulonera.logica.producto;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
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
@WebServlet(name = "svRemito", urlPatterns = {"/svRemito"})
public class svRemito extends HttpServlet {
    controladoraLogica ctrl = new controladoraLogica();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idProd = Integer.parseInt(request.getParameter("idProd"));
        
        producto prod = ctrl.consultarProducto(idProd);
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        // Devuelve el JSON con el nombre y precio
        try (PrintWriter out = response.getWriter()) {
            out.print("{\"nombreProd\": \"" + prod.getNomb_prod() + "\", \"precioProd\": " + prod.getPrecio_venta() + "}");
        }
    }
        

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession misesion = request.getSession();
        cliente cliente1 = (cliente) misesion.getAttribute("clienteCabec");
        
        cliente1.getCuit_cliente();
        cliente1.getRazon_social();
        
        
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>♠

}
