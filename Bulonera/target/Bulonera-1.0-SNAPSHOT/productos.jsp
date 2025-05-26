<%-- 
    Document   : productos
    Created on : 23 oct 2024, 21:35:46
    Author     : tobi2
--%>


<%@page import="java.math.BigDecimal"%>
<%@page import="java.util.List"%>
<%@page import="Bulonera.logica.producto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="componentes/head.jsp"%>
<%@include file="componentes/body.jsp"%>


<form action="svCargarProductos" method="post" enctype="multipart/form-data">
    <li class="nav-item">
        <input class="selecExcel" type="file" id="file" name="file" accept=".xlsx">
        <button type="submit" class="btn btn-navbar" id="boton7">Importar productos</button>
</form>

<button type="submit" class="btn btn-navbar" id="boton10" data-bs-toggle="modal" data-bs-target="#vaciarProd">vaciar productos</button>

</li>
</ul>
</div>
</div>
</nav>
<section id="produc">
    <div class="table-container">
        <table id="tablaProd" class="table tablaProd">
            <thead>
                <tr class="Columnas">
                    <th class="ColumnaCod">Código</th>
                    <th class="ColumnaCat">Categoría</th>
                    <th class="ColumnaNomb">Descripción</th>
                    <th class="Columnas">Precio compra</th>
                    <th class="Columnas">Precio venta</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<producto> listaProducto = (List<producto>) request.getSession().getAttribute("listaProducto");
                    if (listaProducto != null) {
                        for (producto prod : listaProducto) {
                            double precioCompraTruncado = new BigDecimal(prod.getPrecio_compra())
                                    .setScale(2, java.math.RoundingMode.DOWN)
                                    .doubleValue();
                            double precioVentaTruncado = new BigDecimal(prod.getPrecio_venta())
                                    .setScale(2, java.math.RoundingMode.DOWN)
                                    .doubleValue();
                %>
                <tr>
                    <td><%= prod.getCod_prod()%></td>
                    <td><%= prod.getCategoria_prod()%></td>
                    <td><%= prod.getNomb_prod()%></td>
                    <td><%= precioCompraTruncado%></td>
                    <td><%= precioVentaTruncado%></td>
                </tr>
                <%
                        }
                    }
                %>
            </tbody>
        </table>
    </div>
</section>
        
        <!-- Modal -->
<div class="modal fade" id="vaciarProd" tabindex="-1" aria-labelledby="vaciarProd" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="exampleModalLabel">ATENCION!</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
          <p>¿Seguro que desea vaciar la lista de productos?</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
        <form action="svCargarProductos" method="GET">
            <button type="submit" class="btn btn-primary">Aceptar</button>
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
                    <h5 class="modal-title" id="errorModalLabel">Atencion</h5>
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
             
    <script>
            $(document).ready(function() {
             $('#tablaProd').DataTable({
                 pageLength: 10, // Filas por página
                 language: {
                     // Usa la traducción base en español
                     url: '//cdn.datatables.net/plug-ins/1.13.6/i18n/es-ES.json',
                     // Sobrescribe solo los textos que quieras cambiar
                     search: "🔍 Buscar producto:",
                     lengthMenu: "Mostrar _MENU_ productos por página",
                     paginate: {
                         first: "Primera",
                         last: "Última",
                         next: "Siguiente →",
                         previous: "← Anterior"
                     },
                     info: "Mostrando _START_ a _END_ de _TOTAL_ productos",
                     emptyTable: "No hay productos disponibles",
                     zeroRecords: "No se encontraron productos coincidentes"
                 }
             });
         });
     </script>
             
    <script>
    window.onload = function() {
        // Verificar si hay un mensaje de error
        const error = "<%= request.getAttribute("error") != null ? "true" : "false" %>";

        const errorModalEl = document.getElementById('errorModal');
        const errorModal = new bootstrap.Modal(errorModalEl);

        // Mostrar modal si hay error
        if (error === "true") {
            errorModal.show();
        }

        // Solución para evitar foco en un modal oculto (accesibilidad)
        errorModalEl.addEventListener('hidden.bs.modal', () => {
            if (document.activeElement && errorModalEl.contains(document.activeElement)) {
                document.activeElement.blur(); // quita el foco si sigue dentro del modal
            }
        });
    };
    </script>
</body>
</html>
