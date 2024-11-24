/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Bulonera.Servlet;

import Bulonera.logica.cliente;
import Bulonera.logica.controladoraLogica;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "sVeliminarCliente", urlPatterns = {"/sVeliminarCliente"})
public class sVeliminarCliente extends HttpServlet {
    controladoraLogica ctrl = new controladoraLogica();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        int idBorrar = Integer.parseInt(request.getParameter("idCliente"));
        ctrl.eliminarCliente(idBorrar);

        cliente cli = ctrl.consultarCliente(idBorrar);
        if (cli != null){
            ctrl.eliminarCliente(idBorrar);
            List<cliente> listaClientesActualizada = ctrl.consultarClienteList();
            HttpSession session = request.getSession();
            session.setAttribute("listaCliente", listaClientesActualizada);
            response.sendRedirect("clientes.jsp#client");
            
        } else {
            request.setAttribute("error", "No se encontro el cliente que desea eliminar");
            request.getRequestDispatcher("clientes.jsp#client").forward(request, response);
        }
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
