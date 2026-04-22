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
     HttpSession miSesion = request.getSession(false);

    String contra = request.getParameter("confirmContraElim");
    String contraIng = (String) miSesion.getAttribute("contraValida");

    String idClienteStr = (request.getParameter("idCliente"));
    
     if (idClienteStr == null || idClienteStr.isEmpty()) {
    // Manejar el caso de parámetro faltante o vacío
    HttpSession session = request.getSession();
    session.setAttribute("errorModif", "Debe ingresar un Nro de cliente");
    response.sendRedirect("clientes.jsp#client");
    } else {
    int idBorrar = Integer.parseInt(idClienteStr);
    
    cliente cli = ctrl.consultarCliente(idBorrar);

    if (contra == null || contraIng == null) {
        HttpSession sesion = request.getSession();
        System.out.println("su contraseña es :"+ contra);
        sesion.setAttribute("errorModif", "Ingrese su contraseña");
        sesion.setAttribute("validac", false);
        response.sendRedirect("clientes.jsp#client");
        return;
    }

    if (contra.equals(contraIng)) {
        if (cli != null) {

            ctrl.eliminarCliente(idBorrar);

            List<cliente> listaClientesActualizada = ctrl.consultarClienteList();
            HttpSession session = request.getSession();
            session.setAttribute("listaCliente", listaClientesActualizada);
            session.setAttribute("validac", true);
            response.sendRedirect("clientes.jsp#client");
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("errorModif", "No se encontró el cliente que desea eliminar");
            session.setAttribute("validac", false);
            response.sendRedirect("clientes.jsp#client");
        }
    } else {
        HttpSession session = request.getSession();
        session.setAttribute("errorModif", "Contraseña incorrecta");
        session.setAttribute("validac", false);
        response.sendRedirect("clientes.jsp#client");
    }
    }
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
