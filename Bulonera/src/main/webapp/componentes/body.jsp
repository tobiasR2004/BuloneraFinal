<%-- 
    Document   : body
    Created on : 23 oct 2024, 20:59:14
    Author     : tobi2
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<section id="MenuPrincipal">  
</section>

<body class="bodymenu" id="MenuPrincipal">
    <!--Dashboard  Romani Tobias-->
    <div class="menu-dashboard">
        <div class="top-menu">
            <div class="logo">
            </div>
            <div class="toggle">
                <i class="bi bi-list"></i>
            </div>
        </div>
        <div class="elementmenu">
            <form action="sVcliente" method="Get">
                    <div class="enlace">
                        <i class="bi bi-file-earmark-person"></i>
                        <button type="submit" class="onlyTXT"><a href="clientes.jsp#client" style="text-decoration:none;"
                                onclick="cambiarBotones('opcion1')"><span>Clientes </span></a></button>
                    </div>
            </form>
            
            
            <form action="sVcuentaCorrienteRemito" method="get">
                <div class="enlace">
                    <i class="bi bi-cart"></i>
                    <button type="submit" class="onlyTXT"><a href="cuentaCorriente.jsp" style="text-decoration: none;"
                               onclick="cambiarBotones('opcion2')"><span>C/corrientes</span></a></button>
                </div>
            </form>

            <div class="enlace">
                <i class="bi bi-screwdriver"></i>
                <a href="productos.jsp" style="text-decoration: none;" onclick="cambiarBotones('opcion3')"><span>Productos</span></a>

            </div>

            <div class="enlace">
                <i class="bi bi-gear "></i>
                <a href="configCuenta.jsp" style="text-decoration: none;"
                   onclick="cambiarBotones('opcion4')"><span>Ajustes</span></a>
            </div>

            <div class="enlace">
                <i class="bi bi-box-arrow-left"></i>
                <a href="index.jsp" style="text-decoration: none;"><span>Salir</span></a>
            </div>
        </div>
    </div>

    <!--Navbar  Romani Tobias-->
    <nav class="navbar navbar-expand-lg bg-body-tertiary">
        <div class="container-fluid">
            <h4 class="titulonavbar">Bulonera Romani</h4>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbar">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">




