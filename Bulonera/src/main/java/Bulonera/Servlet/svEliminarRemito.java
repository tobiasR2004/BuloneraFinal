package Bulonera.Servlet;

import Bulonera.logica.controladoraLogica;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "svEliminarRemito", urlPatterns = {"/svEliminarRemito"})
public class svEliminarRemito extends HttpServlet {
    controladoraLogica ctrl = new controladoraLogica();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String[] remitosSeleccionados = request.getParameterValues("remitosSeleccionados");

        if (remitosSeleccionados != null) {
            for (String idRemito : remitosSeleccionados) {
                // Llamar a tu método de la controladora lógica/persistencia para eliminar el remito
                System.out.println("Eliminando remito con ID: " + idRemito); // LOG para verificar los valores
                ctrl.eliminarDetallePorIdCabecera(Integer.parseInt(idRemito));
            }
        }
        else {
            System.out.println("No se seleccionaron remitos."); // LOG si no hay checkboxes seleccionados
        }

        // Redirigir al usuario de nuevo a la página de cuentas corrientes
        response.sendRedirect("cuentaCorriente.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
