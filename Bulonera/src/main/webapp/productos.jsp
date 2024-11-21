<%-- 
    Document   : productos
    Created on : 23 oct 2024, 21:35:46
    Author     : tobi2
--%>


<%@page import="java.util.List"%>
<%@page import="Bulonera.logica.producto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="componentes/head.jsp"%>
<%@include file="componentes/body.jsp"%>

<li class="nav-item">
    <button type="button" class="btn btn-navbar" id="boton7">Importar productos</button>
</li>
</ul>
</div>
</div>
</nav>
<section id="produc">
    <div class="table-container">
        <TABLE class="table tablita">
            <tr class="Columnas ">
                <th class="Columnas">Código</th>
                <th class="Columnas">Categoría</th>
                <th class="Columnas">Descripcion</th>
                <th class="Columnas">Precio compra</th>
                <th class="Columnas">Precio venta</th>
            </tr>
            <%
                List<producto> listaProducto = (List<producto>) request.getSession().getAttribute("listaProducto");
                if (listaProducto != null) {
                    for (producto prod : listaProducto) {
            %>
                    <tr>
                        <td><%= prod.getCod_prod() %></td>
                        <td><%= prod.getCategoria_prod() %></td>
                        <td><%= prod.getNomb_prod() %></td>
                        <td><%= prod.getPrecio_compra() %></td>
                        <td><%= prod.getPrecio_venta()%></td>
                    </tr>
            <%
                    }   
                }
            %>
        </TABLE>
    </div>
</section>
</body>
</html>
