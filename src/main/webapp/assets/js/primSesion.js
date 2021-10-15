/**
 * 
 */
	

$(document).ready(function () {

	document.getElementById("pageConta").classList.add("sbar_collapsed");
	
	var actual = document.getElementById('actual');
	if(actual!=null)
	{
		if(actual == false)
			{
				document.getElementById("errorAc").classList.remove("mostrar");	
			}
		else if(actual == true)
			{
				document.getElementById("errorAc").classList.add("mostrar");	
			}
	}	
	
	
	var nSesion = $("body").attr("data-sesion");
			
			
	
		if(nSesion==0 && nSesion != null)
			{
			$('#firstSession').modal('toggle');	
			document.getElementById("navBtn").classList.add("ocultar");
			}
			
	var nSesion = $("body").attr("data-sesionCl");
		if(nSesion==0 && nSesion != null)
			{
			$('#firstSessionCl').modal('toggle');
			document.getElementById("navBtn").classList.add("ocultar");			
			}
			
	});
	


function pssValidate()
{
// Obtenemos los valores de los campos de contraseñas 
pass1 = document.getElementById('txtPassNuev1');
pass2 = document.getElementById('txtPassNuev2');
// Verificamos si las constraseñas no coinciden
document.getElementById("error").classList.remove("mostrar");
if (pass1.value != pass2.value) {
 
    // Si las constraseñas no coinciden mostramos un mensaje
    document.getElementById("error").classList.add("mostrar");
 
    return false;
}
 
else {
 
    // Si las contraseñas coinciden ocultamos el mensaje de error
    document.getElementById("error").classList.remove("mostrar");
 
    // Mostramos un mensaje mencionando que las Contraseñas coinciden
    document.getElementById("ok").classList.remove("ocultar");
 
    // Desabilitamos el botón de login
    document.getElementById("login").disabled = true;
 
    // Refrescamos la página (Simulación de envío del formulario)
    document.getElementById("navBtn").classList.remove("ocultar");
		$('#formContra').submit();
}
}

