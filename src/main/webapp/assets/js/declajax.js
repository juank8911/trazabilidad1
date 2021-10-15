/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {

$('#submit').click(function(){
     /* when the submit button in the modal is clicked, submit the form */
    $('#formDeRe').submit();
});

});


$(document).ready(function () {
        $('#declarar').click(function() {
            
    var confirmar= confirm('Al crear la declaracion la informacion reportada no estara disponible para futuras modificaciones. Si esta seguro haga clic en "Aceptar" de lo contrario clic en "Cancelar" ');
        if (confirmar) {
                 
    $.ajax({
    url: 'controlGenerador?action=CrearDeclaResiduo',
    type: 'POST',
    data: {
        

        'txtCantEmb[]': document.getElementById("txtCantEmb").value,
        'inclu': inclusions1.toString()
    
    },
    statusCode: {
                404: function () {
                    window.alert('pagina no encontrada');
                },
                500: function () {
                    window.alert('Error servidor');
                }
            },
            success: function (datos) {
            
            window.alert('Declaracion enviada al Transportador con Exito.!');

                }
    });
            
    }else {
            window.alert('Declaracion Cancelada')
        }

    });
});


