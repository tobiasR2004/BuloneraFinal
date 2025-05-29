<%-- 
    Document   : remitos
    Created on : 6 nov 2024, 21:24:20
    Author     : tobi2
--%>
<%@page import="Bulonera.logica.detalle_remito"%>
<%@page import="java.util.List"%>
<%@page import="Bulonera.logica.cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="componentes/head.jsp"%>
<%@include file="componentes/body.jsp"%>
<li>
    <form action="svPdfdetalle" method="POST">
        <button type="submit" class="btn btn-navbar" id="boton12" style="margin-left: 50px">
            Imprimir detalle  </button>  
    </form>
</li>
<li class="nav-item">
    <button type="button" class="btn btn-navbar" id="boton11" style="margin-left: 50px">Modificar</button>
</li>
<li class="nav-item">
    <button type="button" class="btn btn-navbar" id="boton14" style="display: none">Cancelar</button>
</li>
</ul>
</div>
</div>
</nav>
<% cliente cli = (cliente) request.getSession().getAttribute("clientCabec");%>
<form action="svRemito" method="GET" class="fondoRem">
    <div class="cabecRemito">
        <label class="form-label">Numero Cliente</label>
        <input type="text" class="form-control" id="exampleInputEmail1"  disabled="disabled" value="<%= session.getAttribute("idCabec")%>">
    </div>

    <div class="cabecRemito">
        <label class="form-label">Razon Social</label>
        <input type="text" class="form-control" id="exampleInputEmail1" disabled="disabled" value="<%=cli.getRazon_social()%>">
    </div>

    <div class="cabecRemito">
        <label class="form-label">CUIT</label>
        <input type="text" class="form-control" id="exampleInputEmail1" disabled="disabled" value="<%=cli.getCuit_cliente()%>">
    </div>

</form>
    
<form action="svModifRemito" method="get">
    
    <li class="nav-item">
        <button type="submit" class="btn btn-success" id="boton13"  style="display: none">Confirmar</button>
    </li>    
    <div class="table-container" style="margin-top: 15%">    
        <div id="cuentaCorrienteTabla">
            <TABLE class="table tablita" id="tablaDr" >
                <tr class="Columnas ">
                    <th class="Columnas">Producto</th>
                    <th class="Columnas">cantidad</th>
                    <th class="Columnas">Precio Unitario</th>
                    <th class="Columnas">Importe</th>
                    <th style="display: none; width: 120px" id="checkboxHeader">Seleccionar</th>
                </tr>

                <%
                    List<detalle_remito> listaDr = (List<detalle_remito>) request.getSession().getAttribute("DetallesList");
                    if (listaDr != null) {

                        double saldoAcumulado = 0;
                        for (detalle_remito dr : listaDr) {
                %>

                <tr style="text-align: center">
                    <td><%= dr.getNomb_prod()%></td>
                    <td>
                        <input 
                            type="number" 
                            name="cantidad_<%= dr.getId_remito()%>" 
                            value="<%= String.format(java.util.Locale.US, "%.2f", dr.getCant_prod()) %>"
                            disabled 
                            class="cantidadInput" 
                            data-id="<%= dr.getId_remito()%>"
                            step="0.01">
                    </td>
                    <td><%= String.format("%.2f", dr.getPrecio_unit())%></td>
                    <td><%= String.format("%.2f", dr.getImporte())%></td>
                    <td style="display: none;" class="checkboxRemito">
                        <input 
                            type="checkbox" 
                            name="remitosSeleccionados" 
                            class="checkboxModificar" 
                            value="<%= dr.getId_remito()%>" 
                            data-id="<%= dr.getId_remito()%>">
                    </td>
                </tr>
                <%
                        }
                    }
                %>
            </TABLE>
        </div>    
    </div>

</form>
<!-- Modal de Error -->
<div class="modal fade" id="errorModal" tabindex="-1" aria-labelledby="errorModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="errorModalLabel">Error de Ingreso</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <%= request.getAttribute("error") != null ? request.getAttribute("error") : ""%>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
            </div>
        </div>
    </div>
</div>


<script>
    document.getElementById("boton11").addEventListener("click", function () {
        // Mostrar la columna de checkboxes
        const checkboxes = document.querySelectorAll(".checkboxRemito");
        const checkboxHeader = document.getElementById("checkboxHeader");

        checkboxes.forEach(checkbox => checkbox.style.display = "table-cell");
        checkboxHeader.style.display = "table-cell";

        // Mostrar los botónes de confirmación y cancelacion
        document.getElementById("boton13").style.display = "inline-block";
        document.getElementById("boton11").disabled = true;
        document.getElementById("boton14").style.display = "inline-block";
        document.getElementById("boton8").style.display = "none";
    });

    document.getElementById("boton14").addEventListener("click", function () {
        // Ocultar la columna de checkboxes y los botones
        const checkboxes = document.querySelectorAll(".checkboxRemito");
        const checkboxHeader = document.getElementById("checkboxHeader");
        
        checkboxes.forEach(checkbox => checkbox.style.display = "none");
        checkboxHeader.style.display = "none";

        document.getElementById("boton11").disabled = false;
        document.getElementById("boton13").style.display = "none";
        document.getElementById("boton14").style.display = "none";
        document.getElementById("boton5").disabled = false;
    });
</script>

<script>
document.querySelectorAll('.checkboxModificar').forEach(function(checkbox) {
    checkbox.addEventListener('change', function() {
        const id = this.getAttribute('data-id');
        const input = document.querySelector('.cantidadInput[data-id="' + id + '"]');
        
        if (this.checked) {
            input.disabled = false;
        } else {
            input.disabled = true;
        }
    });
});

//Deshabilitar los input cuando apreta el boton cancelar
document.getElementById('boton14').addEventListener('click', function() {
    document.querySelectorAll('.cantidadInput').forEach(function(input) {
        input.disabled = true;
    });
});
</script>

<script>
    window.onload = function () {
        // Verificar si hay un mensaje de error
        const error = '<%= request.getAttribute("error") != null ? "true" : "false"%>';
        const errorModal = new bootstrap.Modal(document.getElementById('errorModal'));

        if (error === "true") {
            errorModal.show();
        }
    };
</script>


</body>


</html>
