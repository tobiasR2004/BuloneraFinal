<%-- 
    Document   : remitos
    Created on : 6 nov 2024, 21:24:20
    Author     : tobi2
--%>
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
        <input type="text" class="form-control" id="exampleInputEmail1"  value="<%= session.getAttribute("idCabec") %>">
            </div>


        <div class="cabecRemito">
        <label class="form-label">Razon Social</label>
        <input type="text" class="form-control" id="exampleInputEmail1" value="<%=cli.getRazon_social()%>">
        </div>

        <div class="cabecRemito">
        <label class="form-label">CUIT</label>
        <input type="text" class="form-control" id="exampleInputEmail1" value="<%=cli.getCuit_cliente()%>">
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
