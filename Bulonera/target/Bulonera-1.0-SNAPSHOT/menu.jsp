<<<<<<< HEAD
=======
<%-- 
    Document   : menu
    Created on : 15 oct 2024, 20:01:44
    Author     : tobi2
--%>
>>>>>>> 06cc52cb53c1bd20ff7f3a6cb786593a9db83414
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="componentes/head.jsp"%>
<%@include file="componentes/body.jsp"%>



       


        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
        <!--Mallarino Lisandro  CLIENTES-->

        <!--BTN MODIFICACION-->

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
