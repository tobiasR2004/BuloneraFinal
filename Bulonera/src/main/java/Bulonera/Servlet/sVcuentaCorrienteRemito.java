package Bulonera.Servlet;

import Bulonera.logica.cliente;
import Bulonera.logica.controladoraLogica;
import Bulonera.logica.cuenta_corriente;
import Bulonera.logica.detalle_remito;
import java.io.IOException;
import java.util.ArrayList;
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
        
        //TRAER LISTA DE CLIENTES
        List<cliente> listaClientes = ctrl.obtenerClientes();
        if (listaClientes != null && !listaClientes.isEmpty()) {
            System.out.println("Clientes en el Servlet: " + listaClientes);
        } else {
            System.out.println("No se encontraron clientes.");
        }
        request.setAttribute("listaClientes", listaClientes);
        
        //TRAER LISTA DE CUENTAS CORRIENTES
        List<cuenta_corriente> listaCC = ctrl.consultarCcList();
        request.setAttribute("listaCC", listaCC);

        //TRAER NOMBRE DEL CLIENTE
        String nombCli = request.getParameter("buscarCli");
        cliente cliente1 = ctrl.buscarNombCliente(nombCli);
        
        HttpSession misesion = request.getSession();
        misesion.setAttribute("clienteCC", cliente1);
        misesion.setAttribute("clienteIdSeleccionado", nombCli);
        
        // Reenvía la solicitud al JSP
        request.getRequestDispatcher("cuentaCorriente.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
        String nombreProd = request.getParameter("nombreProd");
        int cantProd = Integer.parseInt(request.getParameter("cantProd"));
        int precioProd = Integer.parseInt(request.getParameter("precioProd"));
        int importeProd = Integer.parseInt(request.getParameter("importeProd"));
        int importeTotal = Integer.parseInt(request.getParameter("importeTotal"));
        
        detalle_remito detalleRem = new detalle_remito();
        
        detalleRem.setNomb_prod(nombreProd);
        detalleRem.setCant_prod(cantProd);
        detalleRem.setPrecio_unit(precioProd);
        detalleRem.setImporte(importeProd);
        detalleRem.setImporte_total(importeTotal);
        
        ctrl.crearDetalle(detalleRem);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
