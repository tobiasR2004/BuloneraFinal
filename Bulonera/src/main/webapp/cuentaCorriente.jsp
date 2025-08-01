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
                <button type="button" class="btn btn-navbar" id="boton4" style="margin-left: 50px">Eliminar</button>
            </li>
            <li class="nav-item">
                <button type="button" class="btn btn-navbar" id="boton5">Ver detalle</button>
            </li>
            <li class="nav-item">
                <button type="button" class="btn btn-navbar" id="boton6" data-bs-target="#CancelarDeuda"
                        data-bs-toggle="modal">Cancelar deuda</button>
            </li>
            <form action="svActualizarDetalle" method="Post">
            <li class="nav-item">
                <button type="submit" class="btn btn-navbar" id="boton7">Actualizar precios</button>
            </li>
            </form>
  
            </ul>
        </div>
    </div>
    
    
</nav><!-- SELECT - COMBOBOX -->
<div class="comboBox">
    <form action="sVcuentaCorrienteRemito" method="get">
        <label class="lblCli">CLIENTE: </label>
        <select name="buscarCli" class="form-select" aria-label="Default select example">
            <option value="-1" selected>Elegir...</option>
            <c:forEach var="clie" items="${listaClientes}">
                <option value="${clie.nroClient}"
                <c:if test="${clie.nroClient == sessionScope.clienteIdSeleccionado}">selected</c:if>>
                ${clie.razon_social}
            </option>
            </c:forEach>
        </select>
        
        <c:if test="${not empty errorCabec and desdeRemito == true}">
            <div style="color:red">${errorCabec}</div>
        </c:if>
        
        <button class="btnSel" type="submit">Seleccionar</button>
    </form>
</div>




<!-- TABLA CUENTA CORRIENTE -->
<c:if test="${not empty sessionScope.listaCC}">
<form action="svVerRemito" method="post">
    <table class="table tablaCC" id="tablaCC">
        <thead>
            <tr class="Columnas">
                <th class="Columnas">Fecha operación</th>
                <th class="Columnas">Debe</th>
                <th class="Columnas">Haber</th>
                <th class="Columnas">Saldo</th>
                <th style="display: none; width: 120px" id="checkboxHeader">Seleccionar</th>
            </tr>
        </thead>

        <tbody>
            <c:set var="saldoAcumulado" value="0" />
            <c:forEach var="cc" items="${listaCC}">
                <c:set var="saldoAcumulado" value="${cc.saldo_cc}" />
                <tr style="text-align: center">
                    <td><fmt:formatDate value="${cc.fecha_cc}" pattern="dd/MM/yyyy" /></td>
                    <td><fmt:formatNumber value="${cc.debe_cc}" type="number" maxFractionDigits="2" minFractionDigits="2" /></td>
                    <td><fmt:formatNumber value="${cc.haber_cc}" type="number" maxFractionDigits="2" minFractionDigits="2" /></td>
                    <td class="saldo" name="saldoCc">
                        <fmt:formatNumber value="${saldoAcumulado}" type="number" maxFractionDigits="2" minFractionDigits="2" />
                    </td>
                    <td style="display: none;" class="checkboxColumn">
                        <c:if test="${cc.cabeceraremito != null}">
                            <input type="checkbox" name="remitosSeleccionados" value="${cc.cabeceraremito.idRemito}">
                        </c:if>
                    </td>
                    <td style="display: none;" class="checkboxRemito">
                        <c:if test="${cc.cabeceraremito != null}">
                            <input type="checkbox" name="verRemitoSelecc[]" value="${cc.cabeceraremito.idRemito}">
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <!-- Botón para Confirmar Eliminación -->
    <button type="submit" name="action" id="confirmarEliminacion" value="eliminar" class="btn btn-danger" style="display: none"><i class="bi bi-trash3"></i></button>

    <!-- Botón para Ver Remito -->
    <button type="submit" name="action" id="boton8" value="ver" class="btn btn-outline-secondary" style="display: none">
        <i class="bi bi-eye"></i>
    </button>
</form>
</c:if>
</div>      

<button type="submit" id="cancelarEliminacion" class="btn btn-outline-success cancel" style="display: none;"><i class="bi bi-backspace"></i></button>

<!--Botón para abrir el modal -->
<form action="svCrearCabeceraRem" method="GET">
    <button type="submit" class="btn btn-outline-secondary btnremito" 
            tabindex="0">
        <i class="bi bi-plus-circle"></i>
    </button>
</form>

<!-- Modal CABECERA/DETALLE -->
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


<!-- Estructura del modal DETALLE REMITO -->
<div class="modal fade" id="remito" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content" id="cargarRem">
            <div class="modal-header cabeceraCargaRemito">
                <h1 class="modal-title fs-5" id="exampleModalLabel">REMITO</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
            </div>
            <form action="sVcuentaCorrienteRemito" method="post">
            <div class="modal-body">
                
                
                <!-- Formularios en línea -->
                <%
                List<cliente> listaCliente = (List<cliente>) request.getSession().getAttribute("listaCliente");
                
                    %>
                <div class="row mb-3 detCargaRemito">
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
                            
                        <input type="text" id="buscarProducto" placeholder="Buscar producto..." autocomplete="off">
                        <input type="text" id="codigoProducto" placeholder="Código del producto" readonly>
                        <select id="listaResultados" size="10" style="display: none;"></select>
                            
                        <thead>
                            <tr>
                                <th scope="col">ID-Producto</th>
                                <th scope="col">Nombre de producto</th>
                                <th scope="col">Cantidad</th>
                                <th scope="col">Precio/u</th>
                                <th scope="col">Importe</th>
                                <th></th> <!-- Agregamos columna para utilizar el boton de eliminar linea -->
                            </tr>
                        </thead>
                        <tbody>
                            <tr class="fila-producto">
                                <td><input class="sinBorde anchoId" minlength="1" type="text" name="idProd" onchange="completarProducto(this)"></td>
                                <td><input class="sinBorde anchoNomb" type="text" minlength="1" name="nombreProd" readonly></td>
                                <td><input class="sinBorde ancho cantProd" name="cantProd" type="number" step="any" min="0" required oninput="calcularImporte()"></td>
                                <td><input class="sinBorde ancho precioProd" minlength="1" type="number" name="precioProd" readonly></td>
                                <td><input class="sinBorde importeProd" minlength="1" type="number" name="importeProd" readonly></td>
                                <td><button type="button" class="btnEliminarLinea">❌</button></td>
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
<!-- Modal Cancelar Deuda -->
<div class="modal fade" id="CancelarDeuda" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">

      <div class="modal-header">
        <h1 class="modal-title fs-5" id="exampleModalLabel">Cancelar Deuda</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>

      <div class="modal-body">
        <form action="svCancelarDeuda" method="POST">

          <!-- CONTENEDOR HORIZONTAL -->
          <div class="d-flex justify-content-between gap-4 flex-wrap">

            <!-- COLUMNA IZQUIERDA -->
            <div class="d-flex flex-column gap-2">

              <div class="input-group input-group-sm">
                <span class="input-group-text">$</span>
                <input class="form-control cancelDeuda-importe" name="cancelDeuda" id="importePago"
                  type="text" placeholder="0.00" required pattern="^\d+(\.\d{1,2})?$">
              </div>

              <select class="form-select form-select-sm form-select-formaPago" name="formaPago" id="formaPago" required>
                <option value="">...</option>
                <option value="1">Efectivo</option>
                <option value="2">Transferencia</option>
                <option value="3">Cheque</option>
              </select>
            </div>

            <!-- COLUMNA DERECHA: Campos de Cheque -->
            <div class=" flex-column gap-2 items-body-canceldeuda-cheque" id="chequeFields">
              <input class="form-control form-control-sm cancelDeuda-cheque" name="Banco" id="Banco" type="text" placeholder="Banco">
              <input class="form-control form-control-sm cancelDeuda-cheque" name="nroCheque" id="nroCheque" type="text" placeholder="Nro. Cheque">
              <input class="form-control form-control-sm cancelDeuda-cheque" name="fechaDePago" id="fechaDePago" type="Date" placeholder="Fecha">
            </div>

          </div>

          <!-- BOTONES -->
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal"
              onclick="document.getElementById('importePago').value = '';">CANCELAR</button>
            <button type="submit" class="btn btn-primary">CONFIRMAR</button>
          </div>

        </form>
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
              
<!-- habilitar o no seccion de cheques --><
<script>
  document.addEventListener("DOMContentLoaded", function () {
    const formaPago = document.getElementById("formaPago");
    const chequeFields = document.getElementById("chequeFields");

    function toggleChequeInputs() { // 👈 depuración
      if (formaPago.value == 3) {
        chequeFields.style.display = "flex";
      } else {
        chequeFields.style.display = "none";
        document.getElementById("Banco").value = "";
        document.getElementById("nroCheque").value = "";
        document.getElementById("fechaDePago").value = "";
      }
    }

    // Escuchamos cambio en el select
    formaPago.addEventListener("change", toggleChequeInputs);

    // Ejecutamos al cargar
    toggleChequeInputs();
  });
</script>      

<script>
    const buscarInput = document.getElementById("buscarProducto");
    const listaResultados = document.getElementById("listaResultados");
    const codigoProducto = document.getElementById("codigoProducto");
    
    // Inicialmente ocultar el select
    listaResultados.style.display = "none";
    
     buscarInput.addEventListener("keyup", function () {
        let query = this.value.trim();

        if (query.length > 1) {
            fetch("sVbusquedaProductos?query=" + query)
                .then(response => response.json())
                .then(data => {
                    listaResultados.innerHTML = ""; // Limpiar resultados anteriores

                    if (data.length === 0) {
                        let option = document.createElement("option");
                        option.textContent = "Producto no encontrado";
                        option.disabled = true;
                        listaResultados.appendChild(option);
                    } else {
                        data.forEach(prod => {
                            let option = document.createElement("option");
                            option.value = prod.cod_prod;
                            option.textContent = prod.nomb_prod;
                            listaResultados.appendChild(option);
                        });
                    }

                    listaResultados.style.display = "block"; // Mostrar select con resultados
                })
                .catch(error => {
                    console.error("Error en la búsqueda:", error);
                    listaResultados.style.display = "none";
                });
        } else {
            // Si el input está vacío o es muy corto
            listaResultados.innerHTML = "";
            listaResultados.style.display = "none";
            codigoProducto.value = "";
        }
    });
    
    // Al seleccionar un producto, mostrar su código
    listaResultados.addEventListener("change", function () {
        const selectedOption = this.options[this.selectedIndex];
        if (!selectedOption.disabled) {
            codigoProducto.value = selectedOption.value;
        }
    });
</script>
                
<script>
    document.getElementById("boton4").addEventListener("click", function () {
        // Mostrar la columna de checkboxes
        const checkboxes = document.querySelectorAll(".checkboxColumn");
        const checkboxHeader = document.getElementById("checkboxHeader");
        
        checkboxes.forEach(checkbox => checkbox.style.display = "table-cell");
        checkboxHeader.style.display = "table-cell";

        // Mostrar los botónes de confirmación y cancelacion
        document.getElementById("confirmarEliminacion").style.display = "inline-block";
        document.getElementById("boton5").disabled = true;
        document.getElementById("cancelarEliminacion").style.display = "inline-block";
        document.getElementById("boton8").style.display = "none";
    });
    
    document.getElementById("cancelarEliminacion").addEventListener("click", function () {
        // Ocultar la columna de checkboxes y los botones
        const checkboxes = document.querySelectorAll(".checkboxColumn");
        const checkboxHeader = document.getElementById("checkboxHeader");

        checkboxes.forEach(checkbox => checkbox.style.display = "none");
        checkboxHeader.style.display = "none";

        document.getElementById("boton5").disabled = false; 
        document.getElementById("confirmarEliminacion").style.display = "none";
        document.getElementById("cancelarEliminacion").style.display = "none";
        document.getElementById("boton5").disabled = false;
    });
</script>

<script>
    document.getElementById("boton5").addEventListener("click", function () {
        // Mostrar la columna de checkboxes
        const checkboxes = document.querySelectorAll(".checkboxRemito");
        const checkboxHeader = document.getElementById("checkboxHeader");
        
        checkboxes.forEach(checkbox => checkbox.style.display = "table-cell");
        checkboxHeader.style.display = "table-cell";

        // Mostrar el botón de ver
        document.getElementById("boton8").style.display = "inline-block";
        document.getElementById("boton4").disabled = true;
        
        document.getElementById("cancelarEliminacion").style.display = "inline-block";
    });
    
    document.getElementById("cancelarEliminacion").addEventListener("click", function () {
        // Ocultar la columna de checkboxes y los botones
        const checkboxes = document.querySelectorAll(".checkboxRemito");
        const checkboxHeader = document.getElementById("checkboxHeader");

        checkboxes.forEach(checkbox => checkbox.style.display = "none");
        checkboxHeader.style.display = "none";

        document.getElementById("boton4").disabled = false;
        document.getElementById("boton8").style.display = "none";
        document.getElementById("cancelarEliminacion").style.display = "none";
        document.getElementById("boton4").disabled = false;
    });
</script>
        

                
<script>
    document.getElementById('agregarFila').addEventListener('click', function() {
        const tablaCuerpo = document.querySelector("#tabla-remito tbody");
        const filaBase = document.querySelector('.fila-producto'); // Usamos class en lugar de ID
        const nuevaFila = filaBase.cloneNode(true);

        // Limpiar los inputs de la nueva fila
        const inputs = nuevaFila.querySelectorAll('input');
        inputs.forEach(input => input.value = '');

        tablaCuerpo.appendChild(nuevaFila);
    });
</script>

<% 
    Boolean abrirModal = (Boolean) session.getAttribute("abrirModal");
    // Elimina el atributo después de leerlo para que no persista
    if (abrirModal != null && abrirModal) {
        session.removeAttribute("abrirModal");
    }
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
    //Enviar error para mostrar el modal
window.onload = function () {
    const error = '<%= request.getAttribute("error") != null ? "true" : "false"%>';
    const errorModal = new bootstrap.Modal(document.getElementById('errorModal'));

    if (error === "true") {
        errorModal.show();
    }
};
</script>

<script> 
//NUEVA FUNCION DE AL HACER DOBLE CLICK SE COMPLETE EL PRODUCTO
    document.getElementById('listaResultados').addEventListener('dblclick', function(e) {
        const optionSeleccionada = e.target;
        if (optionSeleccionada.tagName.toLowerCase() === 'option') {
            const id = optionSeleccionada.value;

            // Buscar todas las filas de producto
            const filas = document.querySelectorAll('.fila-producto');
            const ultimaFila = filas[filas.length - 1]; // Usa la última fila como destino

            const inputIdProd = ultimaFila.querySelector('input[name="idProd"]');
            inputIdProd.value = id;

            completarProducto(inputIdProd);
        }
    });
</script>

<script>
//FUNCION PARA ELIMINAR LINEAS DE REMITO EXCEPTO SI ES LA UNICA
    document.querySelector("#tabla-remito").addEventListener("click", function(e){
        if (e.target && e.target.classList.contains("btnEliminarLinea")){
            const todasfilas = document.querySelectorAll(".fila-producto");
            if (todasfilas.length > 1){
                e.target.closest("tr").remove();
                calcularImporteTotal();
            }else{
                alert("El remito debe contener al menos una línea");
            }
        }
    });
</script>

</body>
</html>
