package Bulonera.Servlet;

import Bulonera.logica.cabecera_remito;
import Bulonera.logica.controladoraLogica;
import Bulonera.logica.cuenta_corriente;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
            throws ServletException, IOException  {
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

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
