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
            </ul>

        </div>
    </div>
</nav>
<% cliente cli = (cliente) request.getSession().getAttribute("clientCabec");%>
    <form action="svRemito" method="GET" class="fondoRem">
        <div class="cabecRemito">
        <label class="form-label">Numero Cliente</label>
        <input type="text" class="form-control" id="exampleInputEmail1"  disabled="disabled" value="<%= session.getAttribute("idCabec") %>">
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
        
     <div class="table-container" style="margin-top: 15%">    
        <div id="cuentaCorrienteTabla">
            <TABLE class="table tablita" id="tablaDr" >
             <tr class="Columnas ">
                 <th class="Columnas">Producto</th>
                 <th class="Columnas">cantidad</th>
                 <th class="Columnas">Precio Unitario</th>
                 <th class="Columnas">Importe</th>
             </tr>

             <%
                 List<detalle_remito> listaDr = (List<detalle_remito>) request.getSession().getAttribute("Detalles");
                 if (listaDr != null) {
                 double saldoAcumulado = 0;
                     for (detalle_remito dr : listaDr) {
                     String nomb = dr.getNomb_prod();
                     int prod = dr.getCant_prod();
                     double unit = dr.getPrecio_unit();
                     double importe = dr.getImporte();
             %>

             <tr style="text-align: center">
                 <td><%= dr.getNomb_prod()%></td>
                 <td><%= dr.getCant_prod()%></td>
                 <td><%=dr.getPrecio_unit()%></td>
                 <td><%=dr.getImporte()%></td>
             </tr>
                     <%
                             }
                         }
                     %>
         </TABLE>    
     </div>    
</div>
        
                <!-- Modal de Error -->
    <div class="modal fade" id="errorModal" tabindex="-1" aria-labelledby="errorModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="errorModalLabel">Error de Ingreso</h5>
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
</html>
