<%-- 
    Document   : modifCliente
    Created on : 24 oct 2024, 22:56:43
    Author     : tobi2
--%>

<%@page import="Bulonera.logica.cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="componentes/head.jsp"%>
<%@include file="componentes/body.jsp"%>
      <%
    cliente cliente1 = (cliente) request.getSession().getAttribute("clienModif");
%>
                <form action="svModifclient" method="POST">
                    <div class="modifclient input-group input-group-sm mb-3">
                        <span class="input-group-text" id="inputGroup-sizing-sm">DNI</span>
                        <input type="text" name="dniClModif" class="form-control" aria-label="Sizing example input"
                               aria-describedby="inputGroup-sizing-sm" minlength="8" maxlength="8" required pattern="[0-9]+" 
                               value="<%=(cliente1 != null) ? cliente1.getDni_cliente(): "" %>">
                    </div>
                    <div class="modifclient input-group input-group-sm mb-3">
                        <span class="input-group-text" id="inputGroup-sizing-sm">Razon Social</span>
                        <input type="text" name="razonSocClModif" class="form-control" aria-label="Sizing example input"
                               aria-describedby="inputGroup-sizing-sm" maxlength="35" required pattern="[a-zA-Z ]+" 
                               value="<%= (cliente1 != null) ? cliente1.getRazon_social(): ""%>">
                    </div>
                    <div class="modifclient input-group input-group-sm mb-3">
                        <span class="input-group-text" id="inputGroup-sizing-sm">Fecha de ingreso</span>
                        <input type="date" name="fechaClModif" class="form-control" aria-label="Sizing example input"
                               aria-describedby="inputGroup-sizing-sm" value="<%= (cliente1 != null) ? cliente1.getFecha_ingreso(): "" %>">
                    </div>
                    <div class="modifclient input-group input-group-sm mb-3">
                        <span class="input-group-text" id="inputGroup-sizing-sm">CUIT</span>
                        <input type="text" name="cuitClModif" class="form-control" aria-label="Sizing example input"
                               aria-describedby="inputGroup-sizing-sm" minlength="11" maxlength="11" required pattern="[0-9-]+" 
                               value="<%= (cliente1 != null) ? cliente1.getCuit_cliente() : ""%>">
                    </div>
                    <div class="modifclient input-group input-group-sm mb-3">
                        <span class="input-group-text" id="inputGroup-sizing-sm">Domicilio</span>
                        <input type="text" name="domicilioClModif" class="form-control" aria-label="Sizing example input"
                               aria-describedby="inputGroup-sizing-sm" required pattern="[a-zA-Z0-9 ]+" 
                               value="<%= (cliente1 != null) ?  cliente1.getDomicilio_cliente() : ""%>">
                    </div>
                    <div>
                        <button type="submit" class="btn btn-primary" id="btnmodificar">Modificar</button>  
                    </div>
                </form>
    </body>
</html>
