/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Bulonera.Servlet;


import Bulonera.Persistence.controladoraPersistencia;
import Bulonera.logica.producto;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lichi
 */
@WebServlet(name = "svProductosPaginados", urlPatterns = {"/svProductosPaginados"})
public class svProductosPaginados extends HttpServlet {
    controladoraPersistencia ctrl = new controladoraPersistencia();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Definicion de variables
        int pagina = 1;
        int limite = 50;
        String busqueda = request.getParameter("busqueda");
        
        //Condicion de busqueda
        if (request.getParameter("pagina") != null) {
            try {
                pagina = Integer.parseInt(request.getParameter("pagina"));
            } catch (NumberFormatException e) {
                pagina = 1;
            }
        }
        
        if (busqueda == null) {
            busqueda = ""; // <-- Esto es importante
        }
        
        int offset = (pagina - 1) * limite;
        
        List<producto> productos = ctrl.obtenerProductosPaginados(offset, limite, busqueda);
        int totalProductos = ctrl.contarProductos(busqueda);
        int totalPaginas = (int) Math.ceil((double) totalProductos / limite);
        
        request.setAttribute("productos", productos);
        request.setAttribute("paginaActual", pagina);
        request.setAttribute("totalPaginas", totalPaginas);
        request.setAttribute("busqueda", busqueda);

        request.getRequestDispatcher("/productos.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
