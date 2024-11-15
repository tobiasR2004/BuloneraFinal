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

<form action="svCabeceraRem" method="GET">
    <button type="submit" class="btnaux btn-primary" >
      Launch demo modal
    </button>
</form>

<!-- Modal -->
<form action="svCabeceraRem" method="POST"></form>
        <div class="modal fade" id="modalcabec" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
          <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <% 
                   cliente cli = (cliente) request.getSession().getAttribute("clienteCabec");
                %>
                <div class="modal-header">
                  <h1 class="modal-title fs-5" id="exampleModalLabel">Cabecera de Remito</h1>
                  <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
                </div>
                <div class="modal-bodyCabec">
                  <div class="divCabec">
                      <label for="numero cliente" class="form-label">Número de cliente</label>
                      <% if (cli != null) { %>
                      <input type="text" name="NroClientCabec" class="form-control" disabled="disabled" value="<%= cli.getNro_client() %>">
                      <% } else { %>
                          <input type="text" name="NroClientCabec" class="form-control" disabled="disabled" placeholder="Cliente no disponible">
                      <% } %>
                  </div>
                  <div class="divCabec">
                      <label for="Razon Social" class="form-label">Razón Social</label>
                      <% if (cli != null) { %>
                          <input type="text" name="nombCabec" class="form-control" disabled="disabled" value="<%= cli.getRazon_social() %>">
                      <% } else { %>
                          <input type="text" name="nombCabec" class="form-control" disabled="disabled" placeholder="Cliente no disponible">
                      <% } %>
                  </div>
                  <div class="divCabec">
                      <label for="Cuit" class="form-label">CUIT</label>
                      <% if (cli != null) { %>
                          <input type="text" name="cuitCabec" class="form-control" disabled="disabled" value="<%= cli.getCuit_cliente()%>">
                      <% } else { %>
                          <input type="text" name="cuitCabec" class="form-control" disabled="disabled" placeholder="Cliente no disponible">
                      <% } %>
                  </div>
                  <div class="divCabec">
                      <label for="Fecha" class="form-label" >Fecha</label>
                      <input type="Date" name="FechaCabec" class="form-control" disabled="disabled" value="<%= new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()) %>">
                  </div>
                </div>
               </form>
                <div class="modal-footer">
                  <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                  <button type="submit" class="btn btn-primary">Guardar cambios</button>
                </div>
                </div>
            </div>
        </div>
</form>

<!-- SELECT - COMBOBOX -->
<div class="comboBox">
    <form action="sVcuentaCorrienteRemito" method="get">
        <label class="lblCli">CLIENTE: </label>
        <select name="buscarCli" class="form-select" aria-label="Default select example">
            <option value="-1" selected>Elegir...</option>
            <c:forEach var="clie" items="${listaClientes}">
                <option value="${clie.nro_client}"<c:if test="${clienteIdSeleccionado == clie.nro_client}">selected</c:if>>
                ${clie.razon_social}
            </option>
            </c:forEach>
        </select>
        
        <button class="btnSel" type="submit">Seleccionar</button>
    </form>
</div>

<!-- TABLA CUENTA CORRIENTE -->
<div id="cuentaCorrienteTabla">
<TABLE class="table tablaCC">
    <tr class="Columnas ">
        <th class="Columnas">Fecha operación</th>
        <th class="Columnas">Debe</th>
        <th class="Columnas">Haber</th>
        <th class="Columnas">Saldo</th>
    </tr>
    
    <%
        List<cuenta_corriente> listaCC = (List<cuenta_corriente>) request.getSession().getAttribute("listaCC");
        if (listaCC != null) {
            for (cuenta_corriente cc : listaCC) {
    %>

    <tr style="text-align: center">
        <td><fmt:formatDate value="<%= cc.getFecha_cc()%>" pattern="dd/MM/yyyy" /></td>
        <td><%= cc.getDebe_cc()%></td>
        <td><%= cc.getHaber_cc()%></td>
        <td><%= cc.getSaldo_cc()%></td>
    </tr>
            <%
                    }
                }
            %>
</TABLE>    
</div>



<!-- Botón para abrir el modal -->
<form action="sVcuentaCorrienteRemito" method="get">
    <button type="button" class="btn btn-outline-secondary btnremito" 
            tabindex="0" data-bs-target="#remito" data-bs-toggle="modal">
        <i class="bi bi-plus-circle"></i>
    </button>
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
                        <label for="numero-cliente" class="form-label">Número de cliente</label>
                        <c:forEach var="clie" items="${listaClientes}">
                            <c:if test="${clienteIdSeleccionado == clie.nro_client}">
                                <input type="text" id="razon-social" class="form-control" aria-label="Razón social"
                                       disabled value="${clie.nro_client}">
                            </c:if>
                        </c:forEach>
                        
                    </div>
                    
                    <div class="col">
                        <label for="razon-social" class="form-label">Razón social</label>
                        <c:forEach var="clie" items="${listaClientes}">
                            <c:if test="${clienteIdSeleccionado == clie.nro_client}">
                                <input type="text" id="razon-social" class="form-control" aria-label="Razón social"
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
                            <tr>
                                <td><input class="sinBorde" type="number" name="idProd" value="300" readonly></td>
                                <td><input class="sinBorde" type="text" name="nombreProd" value="TORNILLO"></td>
                                <td><input class="sinBorde" id="cantProd" type="number" name="cantProd" oninput="calcularImporte()"></td>
                                <td><input class="sinBorde" id="precioProd" type="number" name="precioProd" oninput="calcularImporte()"></td>
                                <td><input class="sinBorde" type="number" name="importeProd" id="importeProd" oninput="calcularImporte()"></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
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
   
<script>
function calcularImporte() {
    // Obtener los valores de cantidad y precio, convirtiéndolos a números
    const cantProd = parseFloat(document.getElementById("cantProd").value) || 0;
    const precioProd = parseFloat(document.getElementById("precioProd").value) || 0;

    // Calcular el importe total de la línea actual
    const importeProd = cantProd * precioProd;

    // Mostrar el resultado en el campo importeProd
    document.getElementById("importeProd").value = importeProd;

    // Llamar a la función para actualizar el importe total
    calcularImporteTotal();
}

function calcularImporteTotal() {
    // Seleccionar todos los elementos de importeProd en las filas
    const importeProdElements = document.querySelectorAll("input.importeProd");

    // Sumar todos los valores de importeProd
    let total = 0;
    importeProdElements.forEach(input => {
        total += parseFloat(input.value) || 0;
    });

    // Mostrar el total en el campo importe-total
    document.getElementById("importe-total").value = total.toFixed(2);
}
</script>

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

<!-- BOTON CANCELAR DEUDA -->
<div class="modal fade" id="CancelarDeuda" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="exampleModalLabel" style="margin-left: 10%;">IMPORTE A INGRESAR</h1>
            </div>
            <div class="modal-body">
                <span class="currency-symbol">$</span>
                <input class="importe" type="text" placeholder="0.00" minlength="3" required pattern="^\d+(\.\d{1,2})?$">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">CANCELAR</button>
                <button type="button" class="btn btn-primary" style="margin-left: 10%;">CONFIRMAR</button>
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
window.onload = function () {
    const error = '<%= request.getAttribute("error") != null ? "true" : "false" %>';
    const errorModal = new bootstrap.Modal(document.getElementById('errorModal'));

    if (error === "true") {
        errorModal.show();
    }
};
</script>

</body>
</html>
