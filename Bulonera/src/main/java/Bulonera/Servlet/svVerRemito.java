/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Bulonera.Servlet;

import Bulonera.logica.cabecera_remito;
import Bulonera.logica.cliente;
import Bulonera.logica.controladoraLogica;
import Bulonera.logica.cuenta_corriente;
import Bulonera.logica.detalle_remito;
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
        String action = request.getParameter("action");
        
        if ("eliminar".equals(action)) {
        
        String[] remitosSeleccionados = request.getParameterValues("remitosSeleccionados");
        HttpSession misesion = request.getSession();
        String nombCli = (String) misesion.getAttribute("clienteIdSeleccionado");

        if (remitosSeleccionados != null) {
            for (String idRemito : remitosSeleccionados) {
                try {
                    int idCabecera = Integer.parseInt(idRemito);
                    // Obtener el objeto cabecera_remito
                    cabecera_remito cabecera = ctrl.obtenerCabeceraRemitoPorId(idCabecera);
                    if (cabecera != null) {
                        // Eliminar los detalles asociados a esta cabecera
                        System.out.println("SI SI se encontró la cabecera_remito con ID: " + idCabecera);
                        ctrl.eliminarDetallesPorCabecera(cabecera);
                        ctrl.eliminarCCPorCabecera(cabecera);
                        List<cuenta_corriente> listaCCActualizada = ctrl.consultarCcList(cabecera);
                        HttpSession session = request.getSession();
                        session.setAttribute("listaCC", listaCCActualizada);
                    } else {
                        System.out.println("No se encontró la cabecera_remito con ID: " + idCabecera);
                        request.getRequestDispatcher("sVcuentaCorrienteRemito?buscarCli=" + nombCli).forward(request, response);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("ID de remito inválido: " + idRemito);
                }
            }
            } else {
                System.out.println("No se seleccionaron remitos.");
            }

        // Redirigir al usuario de nuevo a la página de cuentas corrientes
        response.sendRedirect("sVcuentaCorrienteRemito?buscarCli=" + nombCli);
        }
              
        else if ("ver".equals(action)) {
        
            HttpSession misesion = request.getSession();
            String idCabec = (String) misesion.getAttribute("clienteIdSeleccionado");   
            misesion.setAttribute("idCabec", idCabec);
            // Obtener los remitos seleccionados desde el formulario (checklist)
                String[] remitosSeleccionados = request.getParameterValues("verRemitoSelecc[]");

            // Validar si se ha seleccionado un cliente
            if (idCabec == null || "".equals(idCabec) || "Elegir...".equals(idCabec)) {
                request.setAttribute("error", "Seleccione un Cliente");
                request.getRequestDispatcher("cuentaCorriente.jsp").forward(request, response);
                return;
            }

            if (remitosSeleccionados != null && remitosSeleccionados.length > 0) {
                List<Integer> remitosList = new ArrayList<>();
                for (String id : remitosSeleccionados) {
                    remitosList.add(Integer.parseInt(id));  // Convertir cada ID de remito a entero
                }

                // Obtener los detalles de los remitos seleccionados
                List<detalle_remito> detalleList = ctrl.consultarDetalleListCabec(remitosList);
                misesion.setAttribute("DetallesList", detalleList);  // Guardar en la sesión

                for (detalle_remito detalle : detalleList) {
                    System.out.println(detalle.toString());
                }

                int idCli = Integer.parseInt(idCabec);
                cliente cliente1 = ctrl.consultarCliente(idCli);
                misesion.setAttribute("clientCabec", cliente1);

                // Redirigir a la página de detalles de remitos
                response.sendRedirect("remito.jsp");
            } else {
                // Si no se seleccionaron remitos, mostrar mensaje de error
                request.setAttribute("error", "Debe seleccionar al menos un remito.");
                request.getRequestDispatcher("cuentaCorriente.jsp").forward(request, response);
            }}
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
