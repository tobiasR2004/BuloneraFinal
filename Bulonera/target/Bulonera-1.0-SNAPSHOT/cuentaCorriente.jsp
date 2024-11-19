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
                <button type="button" class="btn btn-navbar" id="boton4">Eliminar</button>
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

<!-- <form action="svCrearCabeceraRem" method="GET">
    <button type="submit" class="btnaux btn-primary" >
      Launch demo modal
    </button>
</form>



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
                        <c:forEach var="clie" items="${listaCliente}">
                            <c:if test="${clienteIdSeleccionado == clie.nroClient}">
                                <!-- Número de cliente -->
                                <div class="col">
                                    <label for="numero-cliente" class="form-label">Número de cliente</label>
                                    <input type="text" id="numero-cliente" class="form-control" aria-label="Número de cliente"
                                           disabled value="${clie.nroClient}">
                                </div>
                                <!-- Razón social -->
                                <div class="col">
                                    <label for="razon-social" class="form-label">Razón social</label>
                                    <input type="text" id="razon-social" class="form-control" aria-label="Razón social"
                                           disabled value="${clie.razon_social}">
                                </div>
                            </c:if>
                        </c:forEach>
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
                                <td><input class="sinBorde ancho" type="number" name="idProd" onchange="completarProducto(this)"></td>
                                <td><input class="sinBorde" type="text" name="nombreProd" readonly></td>
                                <td><input class="sinBorde ancho cantProd" type="number" name="cantProd" oninput="calcularImporte()"></td>
                                <td><input class="sinBorde ancho precioProd" type="number" name="precioProd" readonly></td>
                                <td><input class="sinBorde importeProd" type="number" name="importeProd" readonly></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="modal-footer">
                <div class="col importefinal">
                    <label for="importe-total" class="form-label">Importe total</label>
                    <input type="text" id="importe-total" name="importeTotal" class="form-control" aria-label="Importe total" disabled>
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
</div>                    
                
                
<script>
    function completarProducto(input) {
    const idProducto = input.value.trim;

    if (idProducto) {
        fetch(`http://localhost:8080/Bulonera/svRemito?idProd=${idProd}`)
            .then(response => response.json())
            .then(data =>{
                console.log("Datos recibidos del servidor:", data);  // Verifica los datos recibidos
                if (data.nombre && data.precio) {
                    // Actualiza los campos del modal con los datos del producto
                    document.getElementsByName('nombreProd')[0].value = data.nombre;
                    document.getElementsByName('precioProd')[0].value = data.precio;
                } else {
                    alert("Datos del producto no traidos");
                }
            })
                .catch(error => {
                // Si ocurre un error en la solicitud, lo muestra
                console.error("Error al obtener los datos:", error);
                alert("Hubo un error al cargar los datos del producto.");
            });
        }else{
            alert("Por favor ingrese un ID de producto.");
    }
}
</script>            

<!-- CALCULAR IMPORTES TOTALES -->
<script>
function calcularImporte() {
    const filas = document.querySelectorAll('#tabla-remito tbody tr');
    filas.forEach(fila => {
        const cantidad = fila.querySelector('.cantProd').value || 0;
        const precio = fila.querySelector('.precioProd').value || 0;
        const importe = fila.querySelector('.importeProd');
        importe.value = (precio * cantidad);
    });
    calcularImporteTotal();
}

function calcularImporteTotal() {
    // Seleccionar todos los elementos con el atributo name="importeProd"
    const importeProdElements = document.querySelectorAll("[name='importeProd']");

    // Sumar todos los valores de importeProd
    let total = 0;
    importeProdElements.forEach(input => {
        total += parseFloat(input.value) || 0;
    });

    // Mostrar el total en el campo importe-total
    document.getElementById("importe-total").value = total;
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

  <!--
<script>
      //CALCULAR SALDO Total.
    document.addEventListener("DOMContentLoaded", function () {
        let tabla = document.getElementById("tablaCC");
        let filas = tabla.querySelectorAll("tbody tr");
        let saldoAcumulado = 0;

        filas.forEach(fila => {
            let debe = parseDouble(fila.querySelector(".debe").textContent) || 0;
            let haber = parseDouble(fila.querySelector(".haber").textContent) || 0;
            
            // Calcular saldo acumulado
            saldoAcumulado += (debe - haber);
            
            // Mostrar saldo en la columna correspondiente
            fila.querySelector(".saldo").textContent = saldoAcumulado.toFixed(2);
        });
    });
</script>
        -->


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
