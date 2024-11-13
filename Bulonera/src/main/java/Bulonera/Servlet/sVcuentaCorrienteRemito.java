package Bulonera.Servlet;

import Bulonera.logica.cabecera_remito;
import Bulonera.logica.cliente;
import Bulonera.logica.controladoraLogica;
import Bulonera.logica.cuenta_corriente;
import Bulonera.logica.detalle_remito;
import Bulonera.logica.producto;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "sVcuentaCorrienteRemito", urlPatterns = {"/sVcuentaCorrienteRemito"})
public class sVcuentaCorrienteRemito extends HttpServlet {
    controladoraLogica ctrl = new controladoraLogica();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession misesion = request.getSession();
        misesion.removeAttribute("clienteIdSeleccionado");
        
        //TRAER LISTA DE CLIENTES
        List<cliente> listaClientes = ctrl.obtenerClientes();
        if (listaClientes != null && !listaClientes.isEmpty()) {
            System.out.println("Clientes en el Servlet: " + listaClientes);
        } else {
            System.out.println("No se encontraron clientes.");
        }
        request.setAttribute("listaClientes", listaClientes);

        //TRAER CLIENTE
        String nombCli = request.getParameter("buscarCli");
        cliente cliente1 = ctrl.buscarNombCliente(nombCli);
        
        if (nombCli == null || nombCli.equals("-1")) {
            request.setAttribute("error", "Seleccione un cliente");
            request.getRequestDispatcher("cuentaCorriente.jsp").forward(request, response);
            return;
        }
        else{
            cliente cliente1 = ctrl.buscarNombCliente(nombCli);
            misesion.setAttribute("clienteCC", cliente1);
            misesion.setAttribute("clienteIdSeleccionado", nombCli);
            request.setAttribute("clienteIdSeleccionado", nombCli);
        }
       
        //TRAER LISTA DE CUENTAS CORRIENTES
        if (nombCli != null && !nombCli.equals("-1")) {
            int clienteId = Integer.parseInt(nombCli);
            cabecera_remito cabecdetalleremito = ctrl.consultarCabecremito(clienteId);
            
            List<cuenta_corriente> listaCC = ctrl.consultarCcList(cabecdetalleremito);
            System.out.println("Número de cuentas corrientes obtenidas: " + listaCC.size());
            request.getSession().setAttribute("listaCC", listaCC);
        }

        
        // Reenvía la solicitud al JSP
        request.getRequestDispatcher("cuentaCorriente.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int cant_prod = Integer.parseInt(request.getParameter("cantProd"));
        int precio_unit = Integer.parseInt(request.getParameter("precioProd"));
        int importe = Integer.parseInt(request.getParameter("importeProd"));
        int importe_total = Integer.parseInt(request.getParameter("importeTotal"));
        String nomb_prod = request.getParameter("nombreProd");

        HttpSession misesion = request.getSession();
        String nombCli = (String) misesion.getAttribute("clienteIdSeleccionado");
        int idCli = Integer.parseInt(nombCli);
        int productoId = Integer.parseInt(request.getParameter("idProd")); 
               
        cabecera_remito cabecdetalleremito = ctrl.consultarCabecremito(idCli);
        producto producDetalle = ctrl.consultarProducto(productoId);
        
        detalle_remito detalleRem = new detalle_remito();
        detalleRem.setId_remito(1);
        detalleRem.setCant_prod(cant_prod);
        detalleRem.setPrecio_unit(precio_unit);
        detalleRem.setImporte(importe);
        detalleRem.setImporte_total(importe_total);
        detalleRem.setNomb_prod(nomb_prod);
        detalleRem.setCabecdetalleremito(cabecdetalleremito);
        detalleRem.setProducDetalle(producDetalle);
        
        LocalDate fechaActual = LocalDate.now();
        java.sql.Date fechaSQL = java.sql.Date.valueOf(fechaActual);
        cuenta_corriente cuentaCorr = new cuenta_corriente();
        cuentaCorr.setCabeceraremito(cabecdetalleremito);
        cuentaCorr.setDebe_cc(importe_total);
        cuentaCorr.setFecha_cc(fechaSQL);
        
        ctrl.crearCc(cuentaCorr);
        ctrl.crearDetalle(detalleRem);

        misesion.removeAttribute("clienteIdSeleccionado");
        response.sendRedirect("menu.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
