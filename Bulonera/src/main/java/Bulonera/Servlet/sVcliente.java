/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Bulonera.Servlet;

import Bulonera.logica.cabecera_remito;
import Bulonera.logica.cliente;
import Bulonera.logica.controladoraLogica;
import Bulonera.logica.detalle_remito;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "sVcliente", urlPatterns = {"/sVcliente"})
public class sVcliente extends HttpServlet {
    controladoraLogica ctrl = new controladoraLogica();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
    
    public sVcliente() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

            //MUESTRA
            List<cliente> listaCliente = new ArrayList<>();
            listaCliente = (List<cliente>) ctrl.consultarClienteList();
            
            HttpSession sessionMuestraCli = request.getSession();
            sessionMuestraCli.setAttribute("listaCliente", listaCliente);
            
            response.sendRedirect("clientes.jsp#client");   
            processRequest(request, response);
       
        
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
            int dni = Integer.parseInt(request.getParameter("dniCl"));
            String razonSoc = request.getParameter("razonSocCl");
            String fechaStr = request.getParameter("fechaCl");
            String cuit = request.getParameter("cuitCl");
            String domicilio = request.getParameter("domicilioCl");
            
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaing = format.parse(fechaStr);
            
            cliente client = new cliente();
            
            client.setDni_cliente(dni);
            client.setRazon_social(razonSoc);
            client.setFecha_ingreso(fechaing);
            client.setCuit_cliente(cuit);
            client.setDomicilio_cliente(domicilio);
            
            ctrl.crearCliente(client);
            
            client = ctrl.buscarDniCliente(dni);
            Date fechaCabec = new java.util.Date(); 
            cabecera_remito cabec = new cabecera_remito();
            List<detalle_remito> listaCabec = new ArrayList<>();
            listaCabec = (List<detalle_remito>) ctrl.consultarDetalleList();
            
            cabec.setListadetalles((ArrayList<detalle_remito>) listaCabec);
            cabec.setCuit_cliente(cuit);
            cabec.setRazon_social(razonSoc);
            cabec.setClienteCabecera(client);
            cabec.setFecha_Rem(fechaCabec);
         
            ctrl.crearcabecremito(cabec);
            
            List<cliente> listaClientesActualizada = ctrl.consultarClienteList();
            HttpSession session = request.getSession();
            session.setAttribute("listaCliente", listaClientesActualizada);
            
        } catch (ParseException ex) {
            Logger.getLogger(sVcliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        response.sendRedirect("clientes.jsp#client");
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
