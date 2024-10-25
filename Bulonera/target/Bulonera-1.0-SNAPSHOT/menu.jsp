<%-- 
    Document   : menu
    Created on : 15 oct 2024, 20:01:44
    Author     : tobi2
--%>

<%@page import="Bulonera.logica.cliente"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Menu</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <!--CSS-->
        <link rel="stylesheet" href="estilos.css">
        <!--JS-->
        <script src="./js/app.js" defer></script>
    </head>
    <body class="bodymenu">

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
                        <li class="nav-item">
                            <button type="button" class="btn btn-navbar" data-bs-toggle="modal" data-bs-target="#alta"
                                    data-bs-whatever="@mdo" id="boton1" style="display: none;">Alta</button>
                        </li>

                        <li class="nav-item">
                            <button type="button" class="btn btn-navbar" id="boton2" style="display: none;">Modificacion</button>
                        </li>

                        <li class="nav-item">
                            <button type="button" class="btn btn-navbar" id="boton3" style="display: none;">Agregar</button>
                        </li>
                        <form action="sVeliminarCliente" method="post">
                            <li class="nav-item">
                            <button type="submit" class="btn btn-navbar" id="boton4" style="display: none;">Eliminar</button>
                        </li>
                        </form>

                        <li class="nav-item">
                            <button type="button" class="btn btn-navbar" id="boton5" style="display: none;">Imprimir deuda</button>
                        </li>

                        <li class="nav-item">
                            <button type="button" class="btn btn-navbar" id="boton6" data-bs-target="#CancelarDeuda"
                                    data-bs-toggle="modal" style="display: none;">Cancelar deuda</button>
                        </li>

                        <li class="nav-item">
                            <button type="button" class="btn btn-navbar" id="boton7" style="display: none;">Importar productos</button>
                        </li>

                        <button type="button" class="btn btn-outline-secondary" id="boton8" style="display: none;"><i class="bi bi-eye"></i></button>

                    </ul>
                    <form class="d-flex" role="search">
                        <input class="form-control me-2" type="search" placeholder="Escribe..." aria-label="Search">
                        <button class="btn btn-outline-success" type="submit">BUSCAR</button>
                    </form>
                </div>
            </div>
        </nav>

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
                        <button type="submit"><a href="#client" style="text-decoration: none;" onclick="cambiarBotones('opcion1')"><span>Clientes </span></a></button>
                    </div>
                </form>
                
                <div class="enlace">
                    <i class="bi bi-cart"></i>
                    <a href="#cuentcorr" style="text-decoration: none;"
                       onclick="cambiarBotones('opcion2')"><span>C/corrientes</span></a>
                </div>

                <div class="enlace">
                    <i class="bi bi-screwdriver"></i>
                    <a href="#prod" style="text-decoration: none;" onclick="cambiarBotones('opcion3')"><span>Productos</span></a>

                </div>

                <div class="enlace">
                    <i class="bi bi-gear "></i>
                    <a href="#configcuenta" style="text-decoration: none;"
                       onclick="cambiarBotones('opcion4')"><span>Ajustes</span></a>
                </div>

                <div class="enlace">
                    <i class="bi bi-box-arrow-left"></i>
                    <a href="index.jsp" style="text-decoration: none;"><span>Salir</span></a>
                </div>
            </div>
        </div>

        <!--Remito-->
        <div class="modal fade" id="remito" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <h1 class="modal-title fs-5" id="exampleModalLabel">REMITO</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
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
                            <div class="col">
                                <label for="cuit" class="form-label">CUIT</label>
                                <input type="text" id="cuit" class="form-control" aria-label="CUIT" disabled>
                            </div>
                        </div>

                        <!-- Tabla -->
                        <div class="table-responsive">
                            <table class="table table-bordered">
                                <thead>
                                    <tr>
                                        <th scope="col">Nombre de producto</th>
                                        <th scope="col">Cantidad</th>
                                        <th scope="col">Precio/u</th>
                                        <th scope="col">importe</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr contenteditable="true">
                                        <td contenteditable="true">Tornillo</td>
                                        <td contenteditable="true">5</td>
                                        <td contenteditable="true">50</td>
                                        <td contenteditable="true">250</td>
                                    </tr>
                                    <tr contenteditable="true">
                                        <td contenteditable="true">Tuerca</td>
                                        <td contenteditable="true">5</td>
                                        <td contenteditable="true">15</td>
                                        <td contenteditable="true">75</td>
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


        <!--Altas clientes TobiasRomani-->

        <div class="modal fade" id="alta" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="sVcliente" method="post">
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
                        <button type="submit" class="btn btn-primary">Cargar</button>
                        </div>
                            
                        </form>
                    </div>

                </div>
            </div>
        </div>

        <!-- baja clientes Tobias Romani -->


        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
        <!--Mallarino Lisandro  CLIENTES-->

        <!--BTN MODIFICACION-->

        <div id="confirmodif" class="modal" tabindex="-1">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Confirme su contraseña</h5>
                        <button type="button" class="btn-close close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <input type="text" class="form-control" placeholder="Ingrese su contraseña para poder modificar"
                               aria-label="Username" aria-describedby="addon-wrapping" minlength="3" required pattern="[a-zA-Z0-9]+">
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" id="btnmodificar">Modificar</button>
                    </div>
                </div>
            </div>
        </div>



        <section id="client">
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
        </section>

        <section id="MenuPrincipal">
        </section>

        <section id="cuentcorr">CLIENTE Nº 0056
            <br>
            MALLARINO LISANDRO
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

            <button type="buttons" class="btn btn-outline-secondary btnremito" tabindex="0" data-bs-target="#remito" data-bs-toggle="modal"><i
                    class="bi bi-plus-circle"></i></button>
        </section>

        <section id="prod">
            <br>
            <TABLE class="table tablita">
                <tr class="Columnas ">
                    <th class="Columnas">Código</th>
                    <th class="Columnas">Categoría</th>
                    <th class="Columnas">Descripcion</th>
                    <th class="Columnas">Precio compra</th>
                    <th class="Columnas">Precio venta</th>
                </tr>
                <tr>
                    <td class="Columnas" contenteditable="true">01.AN18</td>
                    <td class="Columnas" contenteditable="true">Arandela pulida 1/8</td>
                    <td class="Columnas" contenteditable="true">Arandela</td>
                    <td class="Columnas" contenteditable="true">7.680,88</td>
                    <td class="Columnas" contenteditable="true">7.707,90</td>
                </tr>
                <tr>
                    <td class="Columnas" contenteditable="true"></td>
                    <td class="Columnas" contenteditable="true"></td>
                    <td class="Columnas" contenteditable="true"></td>
                    <td class="Columnas" contenteditable="true"></td>
                    <td class="Columnas" contenteditable="true"></td>
                </tr>
                <tr>
                    <td class="Columnas" contenteditable="true"></td>
                    <td class="Columnas" contenteditable="true"></td>
                    <td class="Columnas" contenteditable="true"></td>
                    <td class="Columnas" contenteditable="true"></td>
                    <td class="Columnas" contenteditable="true"></td>
                </tr>
            </TABLE>

        </section>

        <section id="configcuenta">
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
        </div>

        <section id="Salir">

        </section>


    </body>

    <script>
                  document.getElementById('client').addEventListener('input', function (event) {
                      var fila = event.target.parentNode.rowIndex;
                      var columna = event.target.cellIndex;
                      var nuevoValor = event.target.innerText;
                      console.log('Fila:', fila, 'Columna:', columna, 'Nuevo valor:', nuevoValor);
                  });
    </script>
</html>
