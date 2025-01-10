/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Bulonera.Servlet;

import Bulonera.logica.controladoraLogica;
import Bulonera.logica.producto;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import service.excelService;
import javax.servlet.annotation.MultipartConfig;



/**
 *
 * @author tobi2
 */
@WebServlet(name = "svCargarProductos", urlPatterns = {"/svCargarProductos"})
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2, // 2 MB
    maxFileSize = 1024 * 1024 * 500,      // 500 MB
    maxRequestSize = 1024 * 1024 * 600   // 600 MB
)
public class svCargarProductos extends HttpServlet {
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
        EntityManagerFactory emf = null;
        EntityManager em = null;
        
        try {
            emf = Persistence.createEntityManagerFactory("buloneraPU");
            em = emf.createEntityManager();
            ctrl.vaciarProd();
            
            response.sendRedirect("productos.jsp");
        } catch (Exception e) {
            request.setAttribute("error", "Intente nuevamente");
            request.getRequestDispatcher("productos.jsp").forward(request, response);
        } finally {
            if (em != null) {
                em.close();
            }
            if (emf != null) {
                emf.close();
            }
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
        Part filePart = request.getPart("file"); // Recoge el archivo enviado desde el JSP
        if (filePart != null && filePart.getSize() > 0) {
            InputStream fileContent = filePart.getInputStream();
            excelService excelService = new excelService();

            try {
                // Lee los productos desde el archivo Excel
                List<producto> productos = excelService.leerProductosDesdeExcel(fileContent);

                // Guarda los productos en la base de datos
                ctrl.guardarProduct(productos);

                // Responde al usuario
                request.getRequestDispatcher("productos.jsp").forward(request, response);
                
            } catch (Exception e) {
                response.getWriter().write("Error al cargar productos: " + e.getMessage());
            }
        } else {
            request.setAttribute("error", "Cargue un excel con el formato adecuado");
            request.getRequestDispatcher("productos.jsp").forward(request, response);
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
