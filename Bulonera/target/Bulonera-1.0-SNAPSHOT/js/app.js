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
