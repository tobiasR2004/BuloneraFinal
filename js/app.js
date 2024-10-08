const toggle= document.querySelector(".toggle")
const menudashboard = document.querySelector(".menu-dashboard")
const iconomenu = document.querySelector("i")
const enlacemenu = document.querySelectorAll(".enlace")
const modal = document.getElementById('confirmodif');
const cerrarBtn = document.getElementsByClassName('close')[0];
const btnmodificar = document.getElementById(`btnmodificar`)

/*Romani Tobias*/
toggle.addEventListener("click",()=>{
 menudashboard.classList.toggle("open")

 if(iconomenu.classList.contains("bx-menu")){
    iconomenu.classList.replace("bx-menu", "bx-x")
 }
 else
 {
    iconomenu-classList.replace("bx-x","bx-menu")
 }
},

enlacemenu.forEach(enlace => {
    enlace.addEventListener("click",() =>
    {
        menudashboard.classList.add("open")
        iconomenu.classList.replace("bx-menu", "bx-x")
    })
}),

)

function mostrarconfirmmodif() {
    modal.style.display = 'block';
}

function confirmodif() {
    modal.style.display= 'none';
}

document.getElementById('boton2').addEventListener('click', mostrarconfirmmodif);
cerrarBtn.addEventListener('click', confirmodif);


btnmodificar.addEventListener(`click`, habilitarmodif);

function habilitarmodif() {
    var columclient = document.getElementById(`columclient`);
    var isEditable = columclient.getAttribute(`contenteditable`) === `true`;

    if (isEditable) {
        columclient.setAttribute('contenteditable', 'false');
        this.textContent = 'Activar Edición';
        confirmodif();
    } else {
        columclient.setAttribute('contenteditable', 'true');
        this.textContent = 'Desactivar Edición';
        confirmodif();
    }
}

function cambiarBotones(opcion)
{
    // Ocultar todos los botones
    document.getElementById("boton1").style.display = "none";
    document.getElementById("boton2").style.display = "none";
    document.getElementById("boton3").style.display = "none";
    document.getElementById("boton4").style.display = "none";
    document.getElementById("boton5").style.display = "none";
    document.getElementById("boton6").style.display = "none";
    document.getElementById("boton7").style.display = "none";
    document.getElementById("boton8").style.display = "none";


    
    // Mostrar los botones correspondientes a la opción seleccionada
    switch(opcion)
    {
    case "opcion1":
        document.getElementById("boton1").style.display = "block";
        document.getElementById("boton2").style.display = "block";
        document.getElementById("boton3").style.display = "none";
        document.getElementById("boton4").style.display = "none";
        document.getElementById("boton5").style.display = "none";
        break;
    case "opcion2":
        document.getElementById("boton4").style.display = "block";
        document.getElementById("boton5").style.display = "block";
        document.getElementById("boton6").style.display = "block";
        document.getElementById("boton8").style.display = "block";

        break;
    case "opcion3":
        document.getElementById("boton3").style.display = "none";
        document.getElementById("boton4").style.display = "none";
        document.getElementById("boton5").style.display = "none";
        document.getElementById("boton7").style.display = "block";
        break;
      }
}