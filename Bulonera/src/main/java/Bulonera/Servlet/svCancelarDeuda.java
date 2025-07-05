/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Bulonera.Servlet;

import Bulonera.logica.cabecera_remito;
import Bulonera.logica.cliente;
import Bulonera.logica.controladoraLogica;
import Bulonera.logica.cuenta_corriente;
import Bulonera.logica.pago;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
@WebServlet(name = "svCancelarDeuda", urlPatterns = {"/svCancelarDeuda"})
public class svCancelarDeuda extends HttpServlet {
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
        
        
        HttpSession misesion = request.getSession();
        String idCabec = (String) misesion.getAttribute("clienteIdSeleccionado");
        
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        
        if(idCabec == null){
            request.setAttribute("error", "por favor, seleccione un cliente");
           request.getRequestDispatcher("cuentaCorriente.jsp").forward(request, response);
        } else {
        
        int nroClient = Integer.parseInt(idCabec);
        List<cabecera_remito> cabecList = (List<cabecera_remito>) ctrl.consultarCabecNroClient(nroClient);
        cabecera_remito cabecdetalleremito = cabecList.get(cabecList.size() - 1);
        List<cuenta_corriente> listaCC = ctrl.consultarCcList(cabecdetalleremito);
        
        if(listaCC.isEmpty()){
           request.setAttribute("error", "No se encontro deuda para cancelar");
           request.getRequestDispatcher("cuentaCorriente.jsp").forward(request, response);
        } else 
        {
        String importePago = request.getParameter("cancelDeuda");
        String formaPago = request.getParameter("formaPago");


        double importepago = Double.parseDouble(importePago);
        double saldoAcumulado = 0;
        
        
        
        if (importePago == null || importePago.trim().isEmpty()) {
             // Manejar el caso de valor no válido
            request.setAttribute("error", "Seleccione un cliente");
            request.getRequestDispatcher("cuentaCorriente.jsp").forward(request, response);
        } else if (idCabec == null || "".equals(idCabec) || "Elegir...".equals(idCabec)) {
            request.setAttribute("error", "Por favor... seleccione un Cliente");
            request.getRequestDispatcher("cuentaCorriente.jsp").forward(request, response);
            
        } else if (cabecList.isEmpty()) {
           
        request.setAttribute("error", "Por favor... seleccione un Cliente");
        request.getRequestDispatcher("cuentaCorriente.jsp").forward(request, response);
            
        } else {

            
            LocalDate fechaActual = LocalDate.now();
            java.sql.Date fechaSQL = java.sql.Date.valueOf(fechaActual);
            
            
            cliente client1 = ctrl.consultarCliente(nroClient);
            
            cabecera_remito newcc = new cabecera_remito();
            
            newcc.setCuit_cliente(client1.getCuit_cliente());
            newcc.setFecha_Rem(fechaSQL);
            newcc.setImporte_total(importepago);
            newcc.setRazon_social(client1.getRazon_social());
            newcc.setListadetalles(null);
            newcc.setClienteCabecera(client1);
            ctrl.crearcabecremito(newcc);

            cliente cliente1 = ctrl.consultarCliente(nroClient);
            cuenta_corriente cC1 = new cuenta_corriente();
            
            cC1.setSaldo_cc(saldoAcumulado);
            cC1.setCabeceraremito(newcc);
            cC1.setFecha_cc(fechaSQL);
            cC1.setDebe_cc(0.0);
            cC1.setHaber_cc(importepago);
            
              if (listaCC.size() <= 0 ) {
                cC1.setSaldo_cc(importepago);
               } else {
                cuenta_corriente ultimoElemento = listaCC.get(listaCC.size() - 1);
                double ultimoSaldo = ultimoElemento.getSaldo_cc();

                double saldototal = (ultimoSaldo - importepago);
                cC1.setSaldo_cc(saldototal);
               }
            
            ctrl.crearCc(cC1);
            
            int formaPagoInt = Integer.parseInt(formaPago);
            
            pago pago1 = new pago();
            System.out.println("forma pago:" + formaPago);
            
            
            pago1.setCc_pago(cC1);
            pago1.setCliente_pago(cliente1);
            pago1.setFecha_pago(fechaSQL);
            pago1.setImporte_pago(importepago);
            switch (formaPagoInt) {
                case 3:
                    String bancoCheque = request.getParameter("Banco");
                    String nroChequeStr = request.getParameter("nroCheque");
                    int nroCheque = Integer.parseInt(nroChequeStr);
                    String fechaPagoChequeStr = request.getParameter("fechaDePago");
                    try {
                        Date fechaPagoCheque = format.parse(fechaPagoChequeStr);
                        pago1.setFechaPagoCheque(fechaPagoCheque);
                    } catch (ParseException ex) {
                        Logger.getLogger(svCancelarDeuda.class.getName()).log(Level.SEVERE, null, ex);
                    }   pago1.setBancoCheque(bancoCheque);
                    pago1.setNroCheque(nroCheque);
                    pago1.setFormaPago("Cheque");
                    break;
                case 2:
                    pago1.setBancoCheque(null);
                    pago1.setNroCheque(0);
                    pago1.setFormaPago("Transferencia");
                    pago1.setFechaPagoCheque(null);
                    break;
                case 1:
                    pago1.setBancoCheque(null);
                    pago1.setNroCheque(0);
                    pago1.setFormaPago("Efectivo");
                    pago1.setFechaPagoCheque(null);
                    break;
                default:
                    request.setAttribute("error", "Por favor... seleccione un metodo de Pago");
                    request.getRequestDispatcher("cuentaCorriente.jsp").forward(request, response);
                    break;
            }
            
            
            cabecera_remito cabec1 = new cabecera_remito();
            cabec1.setClienteCabecera(cliente1);
            cabec1.setCuit_cliente(cliente1.getCuit_cliente());
            cabec1.setFecha_Rem(fechaSQL);
            cabec1.setImporte_total(0);
            cabec1.setRazon_social(cliente1.getRazon_social());
            
            ctrl.crearcabecremito(cabec1);
            ctrl.crearPago(pago1);
            
            
            response.sendRedirect("sVcuentaCorrienteRemito?buscarCli=" + idCabec);
        }  
        }
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
