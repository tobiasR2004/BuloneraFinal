<%-- 
    Document   : configCuenta
    Created on : 23 oct 2024, 21:45:06
    Author     : tobi2
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="componentes/head.jsp"%>
<%@include file="componentes/body.jsp"%>

<div class="mb-3config" style="top: 20%;">
    <label for="formGroupExampleInput" class="label2">USUARIO</label>
    <input type="text" class="form-control" id="formGroupExampleInput" placeholder="Nombre de usuario actual">
</div>
<div class="mb-3config" style="top: 45%;">
    <label for="formGroupExampleInput2" class="label2">CONTRASEÑA</label>
    <input type="password" class="form-control" id="formGroupExampleInput2" placeholder="Contraseña actual"
           minlength="3" required pattern="[a-zA-Z0-9]+">
    <button type="button" class="btn btn-outline-success" data-bs-toggle="modal" data-bs-target="#Modal2"
            id="btnAcept">ACEPTAR</button>
</div>
</section>
<!-- Modal de la configuracion -->
<div class="modal fade" id="Modal2" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="exampleModalLabel">Modificacion de usuario</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body body-modal-config">
                <label class="labelConfig">Nuevo nombre de usuario</label>
                <input class="inputConfig" type="text" placeholder="Usuario" minlength="5" maxlength="25" required
                       pattern="[^\s]+">
                <br>
                <label class="labelConfig">Nueva Contraseña</label>
                <input class="inputConfig" type="text" placeholder="Contraseña" minlength="3" required pattern="[a-zA-Z0-9]+">
                <br>
                <label class="labelConfig">Confirmar Contraseña</label>
                <input class="inputConfig" type="text" placeholder="Contraseña" minlength="3" required pattern="[a-zA-Z0-9]+">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                <button type="button" class="btn btn-primary">Guardar cambios</button>
            </div>
        </div>
    </div>
</body>
</html>
