/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    $('select[name=tipoVeh]').on('change', function () {
        

        
        
        $.ajax({
            type: 'GET',
            url: 'controlTrans?action=residuoListaTransGenera',
            data: 'codigoveh=' + $('select[name=tipoVeh]').val(),
            statusCode: {
                404: function () {
                    alert('pagina no encontrada');
                },
                500: function () {
                    alert('Error servidor');
                }
            },
            success: function (datos) {
                $('select[name=tipDesig] option').remove(); // limpia el valor del comboBox
                
                var proDatos = datos.split("|");

                for (var i = 0; i < proDatos.length - 1; i++) { // inicia ciclo para extraer los datos del arreglo

                    var id_tipo_v = proDatos[i].split("-")[0];
                    var nombre_desig = proDatos[i].split("-")[1];
                    $('select[name=tipDesig]').append('<option value= "' + id_tipo_v + '">' + nombre_desig + '</option>') // imprimir datos en el comboBox
                }
            }

        });

    });

});

