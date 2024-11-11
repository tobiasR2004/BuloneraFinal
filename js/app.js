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

// Función para mostrar el modal
function mostrarconfirmmodif() {
    modal.style.display = 'block';
}

// Función para cerrar el modal
function confirmodif() {
    modal.style.display = 'none';
}

// Asignación de eventos al botón y al cierre del modal
document.getElementById('boton2').addEventListener('click', mostrarconfirmmodif);
cerrarBtn.addEventListener('click', confirmodif);

// Evento para el botón de modificar
btnmodificar.addEventListener('click', habilitarmodif);

// Función para habilitar y deshabilitar edición
function habilitarmodif() {
    const columclient = document.getElementById('columclient');
    const isEditable = columclient.getAttribute('contenteditable') === 'true';

    if (isEditable) {
        columclient.setAttribute('contenteditable', 'false');
        this.textContent = 'Activar Edición';
    } else {
        columclient.setAttribute('contenteditable', 'true');
        this.textContent = 'Desactivar Edición';
    }

    confirmodif();  // Cierra el modal al terminar
}

// Función para cambiar la visibilidad de los botones según la opción seleccionada
function cambiarBotones(opcion) {
    const botones = [
        "boton1", "boton2", "boton3", 
        "boton4", "boton5", "boton6", 
        "boton7", "boton8"
    ];

    // Ocultar todos los botones
    botones.forEach(id => {
        const boton = document.getElementById(id);
        if (boton) boton.style.display = 'none';
    });

    // Mostrar los botones según la opción seleccionada
    switch (opcion) {
        case "opcion1":
            mostrarBotones(["boton1", "boton2"]);
            break;
        case "opcion2":
            mostrarBotones(["boton4", "boton5", "boton6", "boton8"]);
            break;
        case "opcion3":
            mostrarBotones(["boton7"]);
            break;
        default:
            console.warn("Opción no reconocida: " + opcion);
    }
}

// Función auxiliar para mostrar botones específicos
function mostrarBotones(ids) {
    ids.forEach(id => {
        const boton = document.getElementById(id);
        if (boton) boton.style.display = 'block';
    });
}
