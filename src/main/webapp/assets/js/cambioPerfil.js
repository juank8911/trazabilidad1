/**
 * 
 */


		 $(document).ready(function () {
				$('select[name=selPerfil]').on('change', function () {
		 						 $.ajax({
		            type: 'GET',
		            url: 'control?action=cargaPerfil',
		            data: 'perfilSel=' + $('select[name=selPerfil]').val(),
		            statusCode: {
		                404: function () {
		                    alert('pagina no encontrada');
		                },
		                500: function () {
		                    alert('Error servidor');
		                }
		            },
		            success: function (datos) {
		            	$(location).attr('href','control?action=ingresar');
					}
		    });       
		     
		 			});
		});


