
// funcion para cambiar NO / SI
function btnCambInterna() {
    var sino;
    sino = document.getElementById("btnInterna").value;
    if ( sino == "NO"){
        document.getElementById("btnInterna").value = "SI";
        document.getElementById('ocultar').style.display = 'none';
        
        }
    else{
        document.getElementById("btnInterna").value = "NO";
        document.getElementById('ocultar').style.display = 'block';

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

// fecha actual en input date

window.onload = function(){

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


function sede_transps() {
    
    $("#f_opc").val("2");
    
    $.post("controlGenerador?action=residuoCreaGenera",$("#data").serialize(),function(data){$("#sedeTransp").html(data);});
    
    
    alert("Aqui llego ");

}

function interna(val) {
	if (val == true) {
		document.getElementById('traz').style.display = 'none';
		document.getElementById('txtPrueba').removeAttribute("disabled");
		document.getElementById('txtResi').removeAttribute("disabled");


	}
	else {
		document.getElementById('traz').removeAttribute("style");
		document.getElementById('txtPrueba').setAttribute("disabled", "disabled");
		document.getElementById('txtResi').setAttribute("disabled", "disabled");
	}
}

//empresa generador 

$(document).ready(function () {
    $('select[name=chekTrans]').on('change', function () {
        $.ajax({
            type: 'GET',
            url: 'controlGenerador?action=listaGestorProg',
            data: 'codiTrans=' + $('select[name=chekTrans]').val(),
            statusCode: {
                404: function () {
                    alert('pagina no encontrada');
                },
                500: function () {
                    alert('Error servidor');
                }
            },
            success: function (datos) {
                $('select[name=txtGes] option').remove(); // limpia el valor del comboBox
                //console.log('entrando a funcion succes');
                var proDatos = datos.split("|");
					$('select[name=txtGes]').append('<option >  ----------  SELECCIONE GESTOR  --------  </option>')
                for (var i = 0; i < proDatos.length - 1; i++) { // inicia ciclo para extraer los datos del arreglo

                    var id_STG = proDatos[i].split("-")[0];
                    var nombre_STG = proDatos[i].split("-")[1];
                    $('select[name=txtGes]').append('<option value= "' + id_STG + '">' + nombre_STG + '</option>') // imprimir datos en el comboBox
                }
            }

        });

    });

});


//Residuos segun Generador y Transportador 

$(document).ready(function () {
    $('select[name=txtGes]').on('change', function () {
        $.ajax({
            type: 'GET',
            url: 'controlGenerador?action=listaResiGenTran',
            data: {'idTre': $('#chekTrans').val(), 'idGes':$('#txtGes').val()},
            statusCode: {
                404: function () {
                    alert('pagina no encontrada');
                },
                500: function () {
                    alert('Error servidor');
                }
            },
            success: function (datos) {
            document.getElementById('txtPrueba').removeAttribute("disabled");
                $('#txtPrueba').empty(); // limpia el valor del comboBox
                //console.log('entrando a funcion succes');
                var proDatos = datos.split("|");

                for (var i = 0; i < proDatos.length - 1; i++) { // inicia ciclo para extraer los datos del arreglo

                    var id_STG = proDatos[i].split("-")[0];
                    var nombre_STG = proDatos[i].split("-")[1];
                    $('#txtPrueba').append('<option value= "' + id_STG + '">' + nombre_STG + '</option>') // imprimir datos en el comboBox
                }
            }

        });

    });

});

// funcion de prueba para validar contraseñas similares
function comprobarClave(){
    var clave1 = document.f1.nueva1.value;
    var clave2 = document.f1.nueva2.value;
    
    if (clave1 == clave2)
        alert("Claves Validas");
    else
        alert("Las nuevas claves no son iguales");
}

//
//$(document).ready(function () {
//    $("#grid").kendoGrid({
//        
//        height: 450,
//
//        
//        pageable: {
//            pageSize: 6
//        },
//        sortable: true
//
//    });
//});


    