/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Bulonera.Servlet;

import Bulonera.logica.cabecera_remito;
import Bulonera.Persistence.controladoraPersistencia;
import Bulonera.logica.cliente;
import Bulonera.logica.producto;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;

@WebServlet("/svRemito")
public class svRemito extends HttpServlet {
    controladoraPersistencia ctrl = new controladoraPersistencia();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String codProducto = request.getParameter("idProd");

        try {
            producto prod1 = ctrl.buscarProductoPorCodProd(Integer.parseInt(codProducto));

            if (prod1 != null) {
                // Crear el JSON de respuesta con los datos del producto
                JSONObject json = new JSONObject();
                json.put("nombre", prod1.getNomb_prod());
                json.put("precio", prod1.getPrecio_venta());

                response.getWriter().write(json.toString());
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("{\"error\":\"Producto no encontrado\"}");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\":\"Error al obtener el producto\"}");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("{\"error\":\"Error al procesar la solicitud\"}");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession misesion = request.getSession();
        cliente cliente1 = (cliente) misesion.getAttribute("clienteCabec");
        
        
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
