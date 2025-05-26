const toggle = document.querySelector(".toggle");
const menudashboard = document.querySelector(".menu-dashboard");
const iconomenu = document.querySelector("i");
const enlacemenu = document.querySelectorAll(".enlace");
const modal = document.getElementById('confirmodif');
const cerrarBtn = document.getElementsByClassName('close')[0];
const btnmodificar = document.getElementById('btnmodificar');

/* Romani Tobias */

// Evento para el botón de menú
toggle.addEventListener("click", () => {
    menudashboard.classList.toggle("open");

    if (iconomenu.classList.contains("bx-menu")) {
        iconomenu.classList.replace("bx-menu", "bx-x");
    } else {
        iconomenu.classList.replace("bx-x", "bx-menu");
    }
});

// Evento para los enlaces del menú
enlacemenu.forEach(enlace => {
    enlace.addEventListener("click", () => {
        menudashboard.classList.add("open");
        iconomenu.classList.replace("bx-menu", "bx-x");
    });
});

// Función auxiliar para mostrar botones específicos
function mostrarBotones(ids) {
    ids.forEach(id => {
        const boton = document.getElementById(id);
        if (boton) boton.style.display = 'block';
    });
}

        document.getElementById('btnAlta').addEventListener('click', function() {
            const tablaClientes = document.querySelector('#tablaClientes tbody');
            const fila = document.createElement('tr');

            // Crear celdas vacías
            fila.innerHTML = `
                <td class="Columnas"></td>
                <td class="Columnas"></td>
                <td class="Columnas"></td>
                <td class="Columnas"></td>
                <td class="Columnas"></td>
            `;

            tablaClientes.appendChild(fila);
        });
        
document.addEventListener("DOMContentLoaded", function() {
    const errorModal = document.getElementById('errorModal');
    if (errorModal) {
        const button = errorModal.querySelector('.btn-close');
        button.addEventListener('click', function() {
        });
    }
});
    
    function calcularImporte() {
    const filas = document.querySelectorAll('#tabla-remito tbody tr');
    filas.forEach(fila => {
        const cantidad = fila.querySelector('.cantProd').value || 0;
        const precio = fila.querySelector('.precioProd').value || 0;
        const importe = fila.querySelector('.importeProd');
        importe.value = (precio * cantidad).toFixed(2);
    });
    calcularImporteTotal();
}

function calcularImporteTotal() {
    // Seleccionar todos los elementos de importeProd en las filas
    const importeProdElements = document.querySelectorAll("input.importeProd");

    // Sumar todos los valores de importeProd
    let total = 0;
    importeProdElements.forEach(input => {
        total += parseFloat(input.value) || 0;
    });

    // Mostrar el total en el campo importe-total
    document.getElementById("importe-total").value = total.toFixed(2);
}

function completarProducto(input) {    
    const idProd = input.value.trim();
    if (idProd !== "") {
        fetch("http://localhost:8080/Bulonera/svRemito?idProd=" + idProd)
            .then(response => response.json())
            .then(data => {
                if (data.nombre && data.precio) {
                    const fila = input.closest('tr');
                    fila.querySelector('input[name="nombreProd"]').value = data.nombre;
                    fila.querySelector('input[name="precioProd"]').value = data.precio;
                } else {
                    alert(data.error || "Producto no encontrado.");
                }
            })
            .catch(error => {
                console.error("Error al obtener los datos:", error);
                alert("Error al obtener los datos del producto.");
            });
    } else {
        alert("Por favor ingresa un ID de producto válido.");
    }
    }
    
    
document.addEventListener("DOMContentLoaded", function() {
    document.getElementById("boton5").addEventListener("click", function() {
        const checkboxes = document.querySelectorAll(".checkboxRemito");
        checkboxes.forEach(column => {
            column.style.display = column.style.display === "none" ? "" : "none";
        });
    });
});
