/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Bulonera.Servlet;

import Bulonera.Persistence.controladoraPersistencia;
import Bulonera.logica.cliente;
import Bulonera.logica.controladoraLogica;
import Bulonera.logica.producto;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "sVbusquedaProductos", urlPatterns = {"/sVbusquedaProductos"})
public class sVbusquedaProductos extends HttpServlet {
    controladoraLogica ctrl = new controladoraLogica();
    EntityManager em;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        String query = request.getParameter("query");
        List<producto> productosEncontrados = new ArrayList<>();
        
        try {
        if (query != null && !query.trim().isEmpty()) {
            controladoraPersistencia ctrlP = new controladoraPersistencia();
            productosEncontrados = ctrlP.buscarProductoPorNombre(query);
        }
        
        // Convertir lista a JSON y enviarla al frontend
        Gson gson = new Gson();
        String json = gson.toJson(productosEncontrados);
            try (PrintWriter out = response.getWriter()) {
                out.write(json);
            }

    } catch (Exception e) {
        e.printStackTrace(); // Registra el error en la consola del servidor
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error en la búsqueda de productos.");
    }
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
