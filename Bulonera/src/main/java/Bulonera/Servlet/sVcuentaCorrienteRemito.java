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
        
        if (nombCli == null || nombCli.equals("-1")) {
            request.setAttribute("errorCabec", "Seleccione un cliente");
      
          request.getRequestDispatcher("cuentaCorriente.jsp").forward(request, response);
            return;
        }
        else{
            cliente cliente1 = ctrl.buscarNombCliente(nombCli);
            misesion.setAttribute("clienteCC", cliente1);
            misesion.setAttribute("clienteIdSeleccionado", nombCli);
        }
       
        //TRAER LISTA DE CUENTAS CORRIENTES
        if (nombCli != null && !nombCli.equals("-1")) {
            int clienteId = Integer.parseInt(nombCli);
            
            List<cabecera_remito> cabecList = (List<cabecera_remito>) ctrl.consultarCabecNroClient(clienteId);
            
            cabecera_remito cabecdetalleremito = cabecList.get(cabecList.size() - 1);
            
            List<cuenta_corriente> listaCC = ctrl.consultarCcList(cabecdetalleremito);
            System.out.println("Número de cuentas corrientes obtenidas: " + listaCC.size());
            misesion.setAttribute("listaCC", listaCC);
        }

        // Reenvía la solicitud al JSP
        request.getRequestDispatcher("cuentaCorriente.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] cantidades = request.getParameterValues("cantProd");
        String[] precios = request.getParameterValues("precioProd");
        String[] importes = request.getParameterValues("importeProd");
        double importe_total = Double.parseDouble(request.getParameter("importeTotal"));
        String[] nombres = request.getParameterValues("nombreProd");
        String[] idsProd = request.getParameterValues("idProd");

        HttpSession misesion = request.getSession();
        List<cuenta_corriente> listaCC = (List<cuenta_corriente>)misesion.getAttribute("listaCC");
     
        
        
        String nombCli = (String) misesion.getAttribute("clienteIdSeleccionado");
        int idCli = Integer.parseInt(nombCli);
        
         List<cabecera_remito> cabecList = (List<cabecera_remito>) ctrl.consultarCabecNroClient(idCli);
               
        cabecera_remito cabecdetalleremito =  cabecList.get(cabecList.size() - 1);
        
        for (int i = 0; i < cantidades.length; i++) {
        int cant_prod = Integer.parseInt(cantidades[i]);
        double precio_unit = Double.parseDouble(precios[i]);
        double importe = Double.parseDouble(importes[i]);
        String nomb_prod = nombres[i];
        String productoId = idsProd[i];
            
        producto producDetalle = ctrl.consultarProductoStr(productoId);

            
        detalle_remito detalleRem = new detalle_remito();
        detalleRem.setCant_prod(cant_prod);
        detalleRem.setPrecio_unit(precio_unit);
        detalleRem.setImporte(importe);
        detalleRem.setImporte_total(importe_total);
        detalleRem.setNomb_prod(nomb_prod);
        detalleRem.setCabecdetalleremito(cabecdetalleremito);
        detalleRem.setProducDetalle(producDetalle);
        
        ctrl.crearDetalle(detalleRem);
        }
        
        LocalDate fechaActual = LocalDate.now();
        java.sql.Date fechaSQL = java.sql.Date.valueOf(fechaActual);
        cuenta_corriente cuentaCorr = new cuenta_corriente();
        cuentaCorr.setCabeceraremito(cabecdetalleremito);
        cuentaCorr.setDebe_cc(importe_total);
        cuentaCorr.setFecha_cc(fechaSQL);
        cuentaCorr.setHaber_cc(0.0);
        
        if (listaCC.size() <= 0 ) {
         cuentaCorr.setSaldo_cc(importe_total);
        } else {
         cuenta_corriente ultimoElemento = listaCC.get(listaCC.size() - 1);
         double ultimoSaldo = ultimoElemento.getSaldo_cc();
         
         double saldototal = ultimoSaldo + importe_total;
         cuentaCorr.setSaldo_cc(saldototal);
        }
        ctrl.crearCc(cuentaCorr);

        misesion.removeAttribute("clienteIdSeleccionado");
        response.sendRedirect("menu.jsp");
    }

}
