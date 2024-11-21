<%-- 
    Document   : cuentaCorriente
    Created on : 23 oct 2024, 21:39:29
    Author     : tobi2
--%>

            <%@page import="Bulonera.logica.cuenta_corriente"%>
            <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
            <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
            <%@page import="Bulonera.logica.cliente"%>
            <%@page import="java.util.List"%>
            <%@page contentType="text/html" pageEncoding="UTF-8"%>
            <%@include file="componentes/head.jsp"%>
            <%@include file="componentes/body.jsp"%>
            
            <!--BOTONES NAVBAR-->
            <li class="nav-item">
            <form action="svModifclient" method="GET" class="d-flex" role="search">
                <input class="form-control me-2" type="search" placeholder="Ingrese el dni" aria-label="Search" name="buscarCl">
                <button class="btn btn-outline-success" type="submit">BUSCAR</button>
            </form>    
            </li>
            <li class="nav-item">
                <button type="button" class="btn btn-navbar" id="boton4">Eliminar</button>
            </li>
            <li class="nav-item">
                <button type="button" class="btn btn-navbar" id="boton5">Imprimir deuda</button>
            </li>
            <li class="nav-item">
                <button type="button" class="btn btn-navbar" id="boton6" data-bs-target="#CancelarDeuda"
                        data-bs-toggle="modal">Cancelar deuda</button>
            </li>
            <li>
                <button type="button" class="btn btn-outline-secondary" id="boton8"><i class="bi bi-eye"></i></button>
            </li>
            </ul>
        </div>
    </div>
</nav>


<!-- SELECT - COMBOBOX -->
<div class="comboBox">
    <form action="sVcuentaCorrienteRemito" method="get">
        <label class="lblCli">CLIENTE: </label>
        <select name="buscarCli" class="form-select" aria-label="Default select example">
            <option value="-1" selected>Elegir...</option>
            <c:forEach var="clie" items="${listaClientes}">
                <option value="${clie.nroClient}"<c:if test="${clienteIdSeleccionado == clie.nroClient}">selected</c:if>>
                ${clie.razon_social}
            </option>
            </c:forEach>
        </select>
        
        <button class="btnSel" type="submit">Seleccionar</button>
    </form>
</div>

<!-- TABLA CUENTA CORRIENTE -->
<div id="cuentaCorrienteTabla">
    <TABLE class="table tablaCC" id="tablaCC">
    <tr class="Columnas ">
        <th class="Columnas">Fecha operación</th>
        <th class="Columnas">Debe</th>
        <th class="Columnas">Haber</th>
        <th class="Columnas">Saldo</th>
    </tr>
    
    <%
        List<cuenta_corriente> listaCC = (List<cuenta_corriente>) request.getSession().getAttribute("listaCC");
        if (listaCC != null) {
        double saldoAcumulado = 0;
            for (cuenta_corriente cc : listaCC) {
            double debe = cc.getDebe_cc() != null ? cc.getDebe_cc() : 0; 
            double haber = cc.getHaber_cc() != null ? cc.getHaber_cc() : 0; 
            saldoAcumulado += (debe - haber);
    %>
    
    <tr style="text-align: center">
        <td><fmt:formatDate value="<%= cc.getFecha_cc()%>" pattern="dd/MM/yyyy" /></td>
        <td><%= cc.getDebe_cc()%></td>
        <td><%= cc.getHaber_cc()%></td>
        <td class="saldo" name="saldoCc"><%=saldoAcumulado%></td>
    </tr>
            <%
                    }
                }
            %>
</TABLE>    
</div>

<!--Botón para abrir el modal -->
<form action="svCrearCabeceraRem" method="GET">
    <button type="submit" class="btn btn-outline-secondary btnremito" 
            tabindex="0">
        <i class="bi bi-plus-circle"></i>
    </button>
</form>

<!-- Modal -->
<form action="svCrearCabeceraRem" method="POST">
        <div class="modal fade" id="modalcabec" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
          <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <% 
                   cliente cli = (cliente) request.getSession().getAttribute("clienteCabec");
                   if (cli!=null) {
                %>
                <div class="modal-header">
                  <h1 class="modal-title fs-5" id="exampleModalLabel">Cabecera de Remito</h1>
                  <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
                </div>
                <div class="modal-bodyCabec">
                  <div class="divCabec">
                      <label for="numero cliente" class="form-label">Número de cliente</label> 
                      <input type="text" name="nroClientCabec" class="form-control" disabled="disabled" value="<%= cli.getNroClient() %>">

                  </div>
                  <div class="divCabec">
                      <label for="Razon Social" class="form-label">Razón Social</label>
                      <input type="text" name="nombCabec" class="form-control" disabled="disabled" value="<%= cli.getRazon_social() %>">
                  </div>
                  <div class="divCabec">
                      <label for="Cuit" class="form-label">CUIT</label>
                      <input type="text" name="cuitCabec" class="form-control" disabled="disabled" value="<%= cli.getCuit_cliente()%>">
                  </div>
                  <div class="divCabec">
                      <label for="Fecha" class="form-label" >Fecha</label>
                      <input type="Date" name="fechaCabec" class="form-control" disabled="disabled" value="<%= new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()) %>">
                  </div>
                </div>
                <div class="modal-footer">
                  <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                  <button type="submit" class="btn btn-primary">Crear Cabecera</button>
                  </form>
                  <form action="sVcuentaCorrienteRemito" method="GET">
                    <button type="button" class="btn btn-primary" data-bs-target="#remito" data-bs-toggle="modal">Cargar Remito</button>
                </form>
                  <%} else {}%>
                </div>
                </div>
            </div>
        </div>
</form>


<!-- Estructura del modal -->
<div class="modal fade" id="remito" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="exampleModalLabel">REMITO</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
            </div>
            <form action="sVcuentaCorrienteRemito" method="post">
            <div class="modal-body">
                
                
                <!-- Formularios en línea -->
                <%
                List<cliente> listaCliente = (List<cliente>) request.getSession().getAttribute("listaCliente");
                
                    %>
                <div class="row mb-3">
                    <div class="col">
                        <label for="numero-cliente" class="form-label">Numero de cliente</label>
                        <c:forEach var="clie" items="${listaClientes}">
                            <c:if test="${clienteIdSeleccionado == clie.nroClient}">
                                <input type="text" id="razon-social" class="form-control" aria-label="RazÃ³n social"
                                       disabled value="${clie.nroClient}">
                            </c:if>
                        </c:forEach>
                        
                    </div>
                    
                    <div class="col">
                        <label for="razon-social" class="form-label">Razon social</label>
                        <c:forEach var="clie" items="${listaClientes}">
                            <c:if test="${clienteIdSeleccionado == clie.nroClient}">
                                <input type="text" id="razon-social" class="form-control" aria-label="RazÃ³n social"
                                       disabled value="${clie.razon_social}">
                            </c:if>
                        </c:forEach>
                    </div>
                </div>

                <!-- Tabla -->
                <div class="table-responsive">  
                        <table class="table table-bordered" id="tabla-remito">
                        <thead>
                            <tr>
                                <th scope="col">ID-Producto</th>
                                <th scope="col">Nombre de producto</th>
                                <th scope="col">Cantidad</th>
                                <th scope="col">Precio/u</th>
                                <th scope="col">Importe</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr id="fila-producto">
                                <td><input class="sinBorde ancho" minlength="1" type="text" name="idProd" onchange="completarProducto(this)"></td>
                                <td><input class="sinBorde" type="text" minlength="1" name="nombreProd" readonly></td>
                                <td><input class="sinBorde ancho cantProd" minlength="1" required pattern="[0-9]+" type="number" name="cantProd" oninput="calcularImporte()"></td>
                                <td><input class="sinBorde ancho precioProd" minlength="1" type="number" name="precioProd" readonly></td>
                                <td><input class="sinBorde importeProd" minlength="1" type="number" name="importeProd" readonly></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <button type="button" id="agregarFila" class="btn btn-outline-secondary" style="margin-left: 50%">
                    <i class="bi bi-plus-circle"></i>
                </button>
            </div>
                
            <div class="modal-footer">
                <div class="col importefinal">
                    <label for="importe-total" class="form-label">Importe total</label>
                    <input type="number" id="importe-total" name="importeTotal" class="form-control" aria-label="Importe total" value="0" readonly>
                </div>
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                <button type="button submit" class="btn btn-primary">Guardar Remito</button>
            </div>
            </form>
        </div>
    </div>
</div>    
                    
                    <!-- Modal CANCELAR DEUDA -->
<div class="modal fade" id="CancelarDeuda" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="exampleModalLabel" style="margin-left: 10%;">IMPORTE A INGRESAR</h1>
            </div>
            <form action="svCancelarDeuda" method="POST">
            <div class="modal-body">
                <span class="currency-symbol">$</span>
                <input class="importe" name="cancelDeuda" type="text" placeholder="0.00" minlength="3" required pattern="^\d+(\.\d{1,2})?$">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">CANCELAR</button>
                <button type="Submit" class="btn btn-primary" style="margin-left: 10%;">CONFIRMAR</button>
            </form>
            </div>
        </div>
    </div>
</div>
                    
<div class="modal fade" id="errorModal" tabindex="-1" aria-labelledby="errorModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="errorModalLabel">Error</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <%= request.getAttribute("errorCabec") != null ? request.getAttribute("errorCabec") : "" %>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                </div>
            </div>
        </div>
    </div>    
                
                

<!-- Modal de Error -->
    <div class="modal fade" id="errorModal" tabindex="-1" aria-labelledby="errorModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="errorModalLabel">¡Atencion!</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <%= request.getAttribute("error") != null ? request.getAttribute("error") : "" %>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                </div>
            </div>
        </div>
    </div>
</body>

<script>
    window.onload = function() {
        // Verificar si hay un mensaje de error
        const error = '<%= request.getAttribute("errorCabec") != null ? "true" : "false" %>';
        const errorModal = new bootstrap.Modal(document.getElementById('errorModal'));

        if (error === "true") {
            errorModal.show();
        }
    };
</script>

<% 
    Boolean abrirModal = (Boolean) session.getAttribute("abrirModal");
    // Elimina el atributo después de leerlo para que no persista
    session.removeAttribute("abrirModal");
%>
<script>
    // Función que abre el modal automáticamente si abrirModal es true
    document.addEventListener("DOMContentLoaded", function() {
        let abrirModal = <%= (abrirModal != null && abrirModal ? "true" : "false") %>;
        if (abrirModal) {
            var modalElement = new bootstrap.Modal(document.getElementById('modalcabec'));
            modalElement.show();
        }
    });
</script>
    

<script>
    //Agregar fila al modal de remito
    document.getElementById('agregarFila').addEventListener('click', function() {
        var tabla = document.getElementById('tabla-remito');
        var fila = document.getElementById('fila-producto');
        var nuevaFila = fila.cloneNode(true);

        // Resetear los valores de los campos para la nueva fila
        var inputs = nuevaFila.getElementsByTagName('input');
        for (var i = 0; i < inputs.length; i++) {
            inputs[i].value = '';
        }
        
        var tablaCuerpo = document.getElementById("tabla-remito").getElementsByTagName("tbody")[0];
        tablaCuerpo.appendChild(nuevaFila);
    });
</script>   
     

<script>
    //Enviar error para mostrar el modal
document.addEventListener("DOMContentLoaded", function () {
    const error = '<%= request.getAttribute("error") != null ? "true" : "false" %>';
    if (error === "true") {
        const errorModal = new bootstrap.Modal(document.getElementById('errorModal'));
        errorModal.show();
    }
</script>
</body>
</html>
