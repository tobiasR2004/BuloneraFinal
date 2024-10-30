/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Bulonera.Servlet;

import Bulonera.logica.controladoraLogica;
import Bulonera.logica.usuario;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "svModifUs", urlPatterns = {"/svModifUs"})
public class svModifUs extends HttpServlet {
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
        
                
    String usNombre = request.getParameter("usModif");
    String usContra = request.getParameter("usContra");
    String usContra1 = request.getParameter("usContra1");
    HttpSession miSesion = request.getSession();
    
    // Recuperar el atributo de la sesión
    Object idUsuarioObj =  miSesion.getAttribute("idUsuario");
    
    int idUs = 0;
     if (idUsuarioObj instanceof Integer) { 
        idUs = (Integer) idUsuarioObj; 
     }
     
     usuario usuario1 = new usuario(usNombre, usContra1, idUs);

    // Verificar si usNombre, usContra o usContra1 son null o vacíos
    if (usNombre == null || usNombre.isEmpty() || usContra == null || usContra.isEmpty() || usContra1 == null || usContra1.isEmpty()) {
        request.setAttribute("error", "Complete todos los campos");
        request.getRequestDispatcher("configCuenta.jsp").forward(request, response);
    }

    if (usContra1.equals(usContra)) {
        
        if (usuario1 != null) {
            ctrl.modifUsuario(usuario1);
          
            
            request.getRequestDispatcher("configCuenta.jsp").forward(request, response);
        } else {
            
            request.setAttribute("error", "Usuario no encontrado");
            request.getRequestDispatcher("configCuenta.jsp").forward(request, response);
        }
        } else {
            // Si las contraseñas no coinciden
            request.setAttribute("error", "Las contraseñas no coinciden.");
            request.getRequestDispatcher("configCuenta.jsp").forward(request, response);
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
