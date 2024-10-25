<%-- 
    Document   : cuentaCorriente
    Created on : 23 oct 2024, 21:39:29
    Author     : tobi2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="componentes/head.jsp"%>
<%@include file="componentes/body.jsp"%>

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
<li><button type="button" class="btn btn-outline-secondary" id="boton8"><i class="bi bi-eye"></i></button></li>

</ul>
</div>
</div>
</nav>
<br>
<TABLE class="table tablita">
    <tr class="Columnas ">
        <th class="Columnas">Fecha operación</th>
        <th class="Columnas">Debe</th>
        <th class="Columnas">Haber</th>
        <th class="Columnas">Saldo</th>
    </tr>
    <tr>
        <td class="Columnas" contenteditable="true">15/05/2024</td>
        <td class="Columnas" contenteditable="true">1000</td>
        <td class="Columnas" contenteditable="true"></td>
        <td class="Columnas" contenteditable="true"></td>
    </tr>
    <tr>
        <td class="Columnas" contenteditable="true">16/05/2024</td>
        <td class="Columnas" contenteditable="true"></td>
        <td class="Columnas" contenteditable="true">600</td>
        <td class="Columnas" contenteditable="true"></td>
    </tr>
    <tr>
        <td class="Columnas" contenteditable="true">17/05/2024</td>
        <td class="Columnas" contenteditable="true"></td>
        <td class="Columnas" contenteditable="true">300</td>
        <td class="Columnas" contenteditable="true"></td>
    </tr>
    <tr>
        <td class="Columnas" contenteditable="true">18/05/2024</td>
        <td class="Columnas" contenteditable="true">500</td>
        <td class="Columnas" contenteditable="true"></td>
        <td class="Columnas" contenteditable="true"></td>
    </tr>
    <tr>
        <td class="Columnas" contenteditable="true">19/05/2024</td>
        <td class="Columnas" contenteditable="true"></td>
        <td class="Columnas" contenteditable="true">100</td>
        <td class="Columnas" contenteditable="true"></td>
    </tr>
</TABLE>

<!-- Botón para abrir el modal -->
<button type="button" class="btn btn-outline-secondary btnremito" 
        tabindex="0" data-bs-target="#remito" data-bs-toggle="modal">
    <i class="bi bi-plus-circle"></i>
</button>

<!-- Estructura del modal -->
<div class="modal fade" id="remito" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="exampleModalLabel">REMITO</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
            </div>
            <div class="modal-body">
                <!-- Formularios en línea -->
                <div class="row mb-3">
                    <div class="col">
                        <label for="numero-cliente" class="form-label">Número de cliente</label>
                        <input type="text" id="numero-cliente" class="form-control" aria-label="Número de cliente" disabled>
                    </div>
                    <div class="col">
                        <label for="razon-social" class="form-label">Razón social</label>
                        <input type="text" id="razon-social" class="form-control" aria-label="Razón social" disabled>
                    </div>
                </div>

                <!-- Tabla -->
                <div class="table-responsive">
                    <table class="table table-bordered" id="tabla-remito">
                        <thead>
                            <tr>
                                <th scope="col">Nombre de producto</th>
                                <th scope="col">Cantidad</th>
                                <th scope="col">Precio/u</th>
                                <th scope="col">Importe</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr contenteditable="true">
                                <td contenteditable="true">Tornillo</td>
                                <td contenteditable="true" class="cantidad">5</td>
                                <td contenteditable="true" class="precio">50</td>
                                <td contenteditable="false" class="importe">250</td>
                            </tr>
                            <tr contenteditable="true">
                                <td contenteditable="true">Tuerca</td>
                                <td contenteditable="true" class="cantidad">5</td>
                                <td contenteditable="true" class="precio">15</td>
                                <td contenteditable="false" class="importe">75</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="modal-footer">
                <div class="col importefinal">
                    <label for="importe-total" class="form-label">Importe total</label>
                    <input type="text" id="importe-total" class="form-control" aria-label="Importe total" disabled>
                </div>
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                <button type="button" class="btn btn-primary">Guardar Remito</button>
            </div>
        </div>
    </div>
</div>

<script>
    document.addEventListener("DOMContentLoaded", () => {
    calcularimportetotal();
    
    // Agregar evento input a cada fila de la tabla
    const rows = document.querySelectorAll("#tabla-remito tbody tr");
    rows.forEach(row => {
        row.addEventListener("input", calcularimportetotal);
    });
});

function calcularimportetotal() {
    let total = 0;
    const rows = document.querySelectorAll("#tabla-remito tbody tr");

    rows.forEach(row => {
        const cantidad = parseFloat(row.children[1].innerText) || 0; // Obtener cantidad
        const precio = parseFloat(row.children[2].innerText) || 0; // Obtener precio
        const importe = cantidad * precio; // Calcular importe
        row.children[3].innerText = importe.toFixed(2); // Actualizar la celda de importe (formateado a 2 decimales)
        total += importe; // Sumar al total
    });

    document.getElementById("importe-total").value = total.toFixed(2); // Actualizar el campo total (formateado)
}
</script>   


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




</body>
</html>
