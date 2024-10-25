<%@page import="java.util.List"%>
<%@page import="Bulonera.logica.cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="componentes/head.jsp"%>
<%@include file="componentes/body.jsp"%>

<ul class="navbar-nav me-auto mb-2 mb-lg-0">

    <li class="nav-item">
        <button type="button" class="btn btn-navbar" id="boton2">Modificacion</button>
    </li>

    <li>
        <button type="button" class="btn btn-navbar" data-bs-toggle="modal" data-bs-target="#alta"
                data-bs-whatever="@mdo" id="boton1" onclick="mostraralta">Alta</button>
    </li>

</ul>
<form action="svModifclient" method="GET" class="d-flex" role="search">
    <input class="form-control me-2" type="search" placeholder="Ingrese el dni" aria-label="Search" name="buscarCl">
    <button class="btn btn-outline-success" type="submit">BUSCAR</button>
</form>
</div>
</div>
</nav>


CLIENTES REGISTRADOS
            <br>
            <table class="table tablita">
                <tr class="Columnas">
                    <th class="Columnas">Nro Cliente</th>
                    <th class="Columnas">DNI</th>
                    <th class="Columnas">Razon Social</th>
                    <th class="Columnas">Fecha de ingreso</th>
                    <th class="Columnas">CUIT</th>
                    <th class="Columnas">Domicilio</th>
                </tr>
                    <%
                List<cliente> listaCliente = (List<cliente>) request.getSession().getAttribute("listaCliente");
                if (listaCliente != null) {
                    for (cliente Cli : listaCliente) {
                    %>
                <tr>
                    <td><%= Cli.getNro_client() %></td>
                    <td><%= Cli.getDni_cliente() %></td>
                    <td><%= Cli.getRazon_social() %></td>
                    <td>xd</td>
                    <td><%= Cli.getCuit_cliente() %></td>
                    <td><%= Cli.getDomicilio_cliente() %></td>
                </tr>
                    <%
                    }
                } else {
                    %>
            <%
                }
            %>
            </table>      

<div class="modal fade" id="alta" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="sVcliente" method="POST">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalLabel">Altas Clientes</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="input-group input-group-sm mb-3">
                        <span class="input-group-text" id="inputGroup-sizing-sm">DNI</span>
                        <input type="text" name ="dniCl" class="form-control" aria-label="Sizing example input"
                               aria-describedby="inputGroup-sizing-sm" minlength="8" maxlength="8" required pattern="[0-9]+">
                    </div>
                    <div class="input-group input-group-sm mb-3">
                        <span class="input-group-text" id="inputGroup-sizing-sm">Razon Social</span>
                        <input type="text" name ="razonSocCl" class="form-control" aria-label="Sizing example input"
                               aria-describedby="inputGroup-sizing-sm" maxlength="35" required pattern="[a-zA-Z ]+">
                    </div>
                    <div class="input-group input-group-sm mb-3">
                        <span class="input-group-text" id="inputGroup-sizing-sm">Fecha de ingreso</span>
                        <input type="date" name ="fechaCl" class="form-control" aria-label="Sizing example input"
                               aria-describedby="inputGroup-sizing-sm">
                    </div>
                    <div class="input-group input-group-sm mb-3">
                        <span class="input-group-text" id="inputGroup-sizing-sm">CUIT</span>
                        <input type="text" name ="cuitCl" class="form-control" aria-label="Sizing example input"
                               aria-describedby="inputGroup-sizing-sm" minlength="11" maxlength="11" required pattern="[0-9-]+">
                    </div>
                    <div class="input-group input-group-sm mb-3">
                        <span class="input-group-text" id="inputGroup-sizing-sm">Domicilio</span>
                        <input type="text" name ="domicilioCl" class="form-control" aria-label="Sizing example input"
                               aria-describedby="inputGroup-sizing-sm" required pattern="[a-zA-Z0-9 ]+">
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                        <button type="submit" class="btn btn-primary" id="btnAlta">Cargar</button>
                    </div>

            </form>
        </div>
    </div>
</div>  
</div>


       <%
    cliente cliente1 = (cliente) request.getSession().getAttribute("clienModif");
%>
<!-- BTN MODIFICACION -->
    <div id="confirmodif" class="modal" tabindex="-1">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Modificar Cliente</h5>
                    <button type="button" class="btn-close close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <input type="text" class="form-control" placeholder="Ingrese su contraseña para poder modificar"
                           aria-label="Username" aria-describedby="addon-wrapping" minlength="3" required pattern="[a-zA-Z0-9]+">
                </div>
                <form action="svModifclient" method="GET">
                    <div class="input-group mb-3">
                        <input type="text" class="form-control" placeholder="DNI del cliente que desea modificar" aria-label="DNI" aria-describedby="button-addon2" name="buscarCl">
                        <button class="btn btn-outline-secondary" type="submit" id="button-addon2">Buscar</button>
                    </div>
                </form>
                <form action="svModifclient" method="POST">
                    <div class="input-group input-group-sm mb-3">
                        <span class="input-group-text" id="inputGroup-sizing-sm">DNI</span>
                        <input type="text" name="dniClModif" class="form-control" aria-label="Sizing example input"
                               aria-describedby="inputGroup-sizing-sm" minlength="8" maxlength="8" required pattern="[0-9]+" 
                               value="<%=cliente1 != null ? cliente1.getDni_cliente() : "" %>">
                    </div>
                    <div class="input-group input-group-sm mb-3">
                        <span class="input-group-text" id="inputGroup-sizing-sm">Razon Social</span>
                        <input type="text" name="razonSocClModif" class="form-control" aria-label="Sizing example input"
                               aria-describedby="inputGroup-sizing-sm" maxlength="35" required pattern="[a-zA-Z ]+" 
                               value="<%=cliente1 != null ? cliente1.getRazon_social() : "" %>">
                    </div>
                    <div class="input-group input-group-sm mb-3">
                        <span class="input-group-text" id="inputGroup-sizing-sm">Fecha de ingreso</span>
                        <input type="date" name="fechaClModif" class="form-control" aria-label="Sizing example input"
                               aria-describedby="inputGroup-sizing-sm" value="<%=cliente1 != null ? cliente1.getFecha_ingreso() : "" %>">
                    </div>
                    <div class="input-group input-group-sm mb-3">
                        <span class="input-group-text" id="inputGroup-sizing-sm">CUIT</span>
                        <input type="text" name="cuitClModif" class="form-control" aria-label="Sizing example input"
                               aria-describedby="inputGroup-sizing-sm" minlength="11" maxlength="11" required pattern="[0-9-]+" 
                               value="<%=cliente1 != null ? cliente1.getCuit_cliente() : "" %>">
                    </div>
                    <div class="input-group input-group-sm mb-3">
                        <span class="input-group-text" id="inputGroup-sizing-sm">Domicilio</span>
                        <input type="text" name="domicilioClModif" class="form-control" aria-label="Sizing example input"
                               aria-describedby="inputGroup-sizing-sm" required pattern="[a-zA-Z0-9 ]+" 
                               value="<%=cliente1 != null ? cliente1.getDomicilio_cliente() : "" %>">
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-primary" id="btnmodificar">Modificar</button>  
                    </div>
                </form>                   
            </div>
        </div>
    </div>

                
       <script>
                document.addEventListener("DOMContentLoaded", function() {
                // Verificar si hay datos en la sesión
                var cliente = <%= request.getAttribute("clienModif") != null ? "true" : "false" %>;
                if (cliente) {
                    var clienteData = <%= request.getAttribute("clienModif") %>; // Asumiendo que puedes serializar el objeto
                    document.querySelector('input[name="dniClModif"]').value = clienteData.dni_cliente;
                    document.querySelector('input[name="razonSocClModif"]').value = clienteData.razon_social;
                    document.querySelector('input[name="fechaClModif"]').value = clienteData.fecha_ingreso;
                    document.querySelector('input[name="cuitClModif"]').value = clienteData.cuit_cliente;
                    document.querySelector('input[name="domicilioClModif"]').value = clienteData.domicilio_cliente;

                    // Mostrar el modal
                    var modal = new bootstrap.Modal(document.getElementById('confirmodif'));
                    modal.show();
                }
            });
     </script>

</body>
</html>
