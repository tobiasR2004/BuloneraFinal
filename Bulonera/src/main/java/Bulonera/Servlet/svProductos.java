/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Bulonera.Servlet;

import Bulonera.Persistence.controladoraPersistencia;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Lichi
 */
@WebServlet(name = "svProductos", urlPatterns = {"/svProductos"})
public class svProductos extends HttpServlet {
    controladoraPersistencia ctrl = new controladoraPersistencia();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //MUESTRA
            List<producto> listaProducto = new ArrayList<>();
            listaProducto = (List<producto>) ctrl.consultarProductosList();
            
            HttpSession sessionMuestraProd = request.getSession();
            sessionMuestraProd.setAttribute("listaProducto", listaProducto);
            
            response.sendRedirect("productos.jsp#produc");   
            processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
