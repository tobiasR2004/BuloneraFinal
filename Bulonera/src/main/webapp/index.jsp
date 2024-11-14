<%-- 
    Document   : INDEX
    Created on : 10 oct 2024, 21:13:38
    Author     : tobi2
--%>

<%@page import="Bulonera.Persistence.controladoraPersistencia"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <meta charset="UTF-8">
        
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/estilos.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        <title>Inicio de sesión</title>
    </head>
   <body class="bodyIndex">
       
       <% 
           controladoraPersistencia controlPersis = new controladoraPersistencia();
       %>
       
    <form class="MenuPrincipal" action="svLogin" method="POST">
        <img id="Logo" src="img/LogoBulonera.jpg" alt="Logo Bulonera">
        <div class="mb-3">
            <label for="exampleInputUsuario" class="form-label">USUARIO</label>
            <input type="text" class="form-control" id="exampleInputUsuario" aria-describedby="UsuarioHelp" minlength="5" maxlength="25" required pattern="[^\s]+" name="nombreUs">
        </div>
        <div class="mb-3">
            <label for="exampleInputPassword1" class="form-label">CONTRASEÑA</label>
            <input type="password" class="form-control" id="exampleInputPassword1" minlength="3" name="contrasenia">
        </div>
        <div class="d-grid gap-2">
            <button type="submit" class="btn btn-danger">INGRESAR</button>
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

<script>
    window.onload = function() {
        // Verificar si hay un mensaje de error
        const error = '<%= request.getAttribute("error") != null ? "true" : "false" %>';
        const errorModal = new bootstrap.Modal(document.getElementById('errorModal'));

        if (error === "true") {
            errorModal.show();
        }
    };
</script>
</html>