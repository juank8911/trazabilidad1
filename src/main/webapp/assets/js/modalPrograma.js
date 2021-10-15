/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// funcion para cambiar NO / SI
function btnCambInterna() {
    var sino;
    sino = document.getElementById("btnInterna").value;
    if ( sino == "NO"){
        document.getElementById("btnInterna").value = "SI";
        
        }
    else{
        document.getElementById("btnInterna").value = "NO";

    }
    }
    
    
     
 function btnCambDiaL() {
    var lunes;
    lunes = document.getElementById("btnLunes").value;
    if ( lunes == "NO"){
        document.getElementById("btnLunes").value = "SI";
        
        }
    else{
        document.getElementById("btnLunes").value = "NO";

    }
     
}
function btnCambDiaM() {
    var martes;
    martes = document.getElementById("btnMartes").value;
    if ( martes == "NO"){
        document.getElementById("btnMartes").value = "SI";
        
        }
    else{
        document.getElementById("btnMartes").value = "NO";

    }
     
}
function btnCambDiaMi() {
    var miercoles;
    miercoles = document.getElementById("btnMiercoles").value;
    if ( miercoles == "NO"){
        document.getElementById("btnMiercoles").value = "SI";
        
        }
    else{
        document.getElementById("btnMiercoles").value = "NO";

    }
     
}
function btnCambDiaJ() {
    var jueves;
    jueves = document.getElementById("btnJueves").value;
    if ( jueves == "NO"){
        document.getElementById("btnJueves").value = "SI";
        
        }
    else{
        document.getElementById("btnJueves").value = "NO";

    }
     
}
function btnCambDiaV() {
    var viernes;
    viernes = document.getElementById("btnViernes").value;
    if ( viernes == "NO"){
        document.getElementById("btnViernes").value = "SI";
        
        }
    else{
        document.getElementById("btnViernes").value = "NO";

    }
     
}
function btnCambDiaS() {
    var sabado;
    sabado = document.getElementById("btnSabado").value;
    if ( sabado == "NO"){
        document.getElementById("btnSabado").value = "SI";
        
        }
    else{
        document.getElementById("btnSabado").value = "NO";

    }  
}
function btnCambDiaD() {
    var domingo;
    domingo = document.getElementById("btnDomingo").value;
    if ( domingo == "NO"){
        document.getElementById("btnDomingo").value = "SI";
        
        }
    else{
        document.getElementById("btnDomingo").value = "NO";

    }
     
}
    
 // funcion para cocultar campo fecha
function mostrarF (id){
    if (id == "1") {
        
        $("#hastaFL").hide();
        $("#fechaActual2").hide();
    }else {
        $("#hastaFL").show();
        $("#fechaActual2").show();
    }
    if (id =="3"){
        $('#diasSemana').show();
    }else{
        $('#diasSemana').hide();
        document.getElementById("btnLunes").value = "NO";
        document.getElementById("btnMartes").value = "NO";
        document.getElementById("btnMiercoles").value = "NO";
        document.getElementById("btnJueves").value = "NO";
        document.getElementById("btnViernes").value = "NO";
        document.getElementById("btnSabado").value = "NO";
        document.getElementById("btnDomingo").value = "NO";
        
    }
    
}

$('#exampleModal').on('show.bs.modal', function (event) {
  var button = $(event.relatedTarget) // Button that triggered the modal
  var recipient = button.data('whatever') // Extract info from data-* attributes
  // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
  // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
  var modal = $(this)
  modal.find('.modal-title').text('New message to ' + recipient)
  modal.find('.modal-body input').val(recipient)
})
