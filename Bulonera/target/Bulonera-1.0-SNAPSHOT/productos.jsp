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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<form action="svCargarProductos" method="post" enctype="multipart/form-data">
    <input class="selecExcel" type="file" id="file" name="file" accept=".xlsx">
    <button type="submit" class="btn btn-navbar" id="botonImportar">Importar productos</button>
</form>

<button type="submit" class="btn btn-navbar" id="boton10" data-bs-toggle="modal" data-bs-target="#vaciarProd">vaciar productos</button>

<form method="get" action="svProductosPaginados">
    <input type="text" name="busqueda" value="${busqueda != null ? busqueda : ''}" class="inputBusquedaProd" placeholder="Buscar producto...">
    <button type="submit">Buscar</button>
</form>
    
</ul>
</div>
</div>
</nav>

<div id="produc">
    <div class="table-container">
        <table class="table tablita">
            <thead>
            <tr class="Columnas ">
                <th class="Columnas">Código</th>
                <th class="Columnas">Categoría</th>
                <th class="Columnas">Descripcion</th>
                <th class="Columnas">Precio compra</th>
                <th class="Columnas">Precio venta</th>
            </tr>
            </thead>
            <tbody>
            <c:choose>
                <c:when test="${empty productos}">
                    <tr><td colspan="5">No se encontraron productos.</td></tr>
                </c:when>
                <c:otherwise>
                    <c:forEach var="prod" items="${productos}">
                        <tr>
                            <td>${prod.cod_prod}</td>
                            <td>${prod.categoria_prod}</td>
                            <td>${prod.nomb_prod}</td>
                            <td><fmt:formatNumber value="${prod.precio_compra}" type="number" minFractionDigits="2"/></td>
                        <td><fmt:formatNumber value="${prod.precio_venta}" type="number" minFractionDigits="2"/></td>
                        </tr>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
            </tbody>
        </table>
    </div>
    
    <div class="paginacion">
    <!-- Botón primera página -->
    <c:if test="${paginaActual > 1}">
        <a href="svProductosPaginados?pagina=1&busqueda=${busqueda}">«</a>
        <a href="svProductosPaginados?pagina=${paginaActual - 1}&busqueda=${busqueda}">‹</a>
    </c:if>

    <!-- Números de página dinámicos -->
    <c:set var="startPage" value="${paginaActual - 4}" />
    <c:set var="endPage" value="${paginaActual + 4}" />
    <c:if test="${startPage < 1}">
        <c:set var="endPage" value="${endPage + (1 - startPage)}" />
        <c:set var="startPage" value="1" />
    </c:if>
    <c:if test="${endPage > totalPaginas}">
        <c:set var="endPage" value="${totalPaginas}" />
    </c:if>

    <c:forEach begin="${startPage}" end="${endPage}" var="i">
        <c:choose>
            <c:when test="${i == paginaActual}">
                <span class="pagina-activa">${i}</span>
            </c:when>
            <c:otherwise>
                <a href="svProductosPaginados?pagina=${i}&busqueda=${busqueda}">${i}</a>
            </c:otherwise>
        </c:choose>
    </c:forEach>

    <!-- Botón siguiente/última -->
    <c:if test="${paginaActual < totalPaginas}">
        <a href="svProductosPaginados?pagina=${paginaActual + 1}&busqueda=${busqueda}">›</a>
        <a href="svProductosPaginados?pagina=${totalPaginas}&busqueda=${busqueda}">»</a>
    </c:if>
</div>
</div>
        
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
            function buscarProd() {
                // Obtén el valor ingresado por el usuario
                let input = document.getElementById("searchProd").value.toLowerCase();

                // Obtén todas las filas de la tabla, excepto la de encabezado
                let table = document.querySelector(".tablita");
                let rows = table.getElementsByTagName("tr");

                // Recorre todas las filas y oculta las que no coincidan con la búsqueda
                for (let i = 1; i < rows.length; i++) { // Empieza en 1 para saltar el encabezado
                    let razonSocialCell = rows[i].getElementsByTagName("td")[2]; // Columna de Razon Social
                    if (razonSocialCell) {
                        let razonSocialText = razonSocialCell.textContent || razonSocialCell.innerText;
                        rows[i].style.display = razonSocialText.toLowerCase().includes(input) ? "" : "none";
                    }
                }
            }
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
