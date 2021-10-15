/**
 * 
 */

     function remove(el){
	var index = $(el).closest("tr")
	var index1 = $(el).parent().parent().index();
	var detail = $(el).parent().parent().next(); 
 	var status = $(detail).css("display");
	var imgs = $(el).closest("img")
	 	
 	if(status == "none") 
 	{
 	
 	$(detail).css("display", "table-row");
 	$("img", el).attr('src',"assets/images/icon/market-value/triangle-up.png");
 	}
  
   else{
   $("img", el).attr('src',"assets/images/icon/market-value/triangle-down.png")
    $(detail).css("display", "none")
    } 

 }
 

 
 function enviar(el)
 {
		
		var table = $(el).parent().parent().parent();
		var idDeclaResIn = $(table).find('#idDeclaRes');
		var tipEmbalajeIn = $(table).find('#tipEmbalaje');
		var txtCantEmbIn = $(table).find('#txtCantEmb');	
		var txtCantEmpqIn = $(table).find('#txtCantEmpq');
		var tipEmpaqueIn = $(table).find('#tipEmpaque');
		var txtCantPesoin = $(table).find('#txtCantPeso');
		
		var idDeclaRes = new Array(); 
		var tipEmbalaje = new Array();
		var txtCantEmb = new Array(); 
		var txtCantEmpq = new Array();
		var tipEmpaque  = new Array();
		var txtCantEmpq = new Array();
		var txtCantPeso = new Array();
		

		namesValues = [].map.call(idDeclaResIn,function(dataImput){
		idDeclaRes.push(dataImput.value);
		});
		namesValues = [].map.call(tipEmbalajeIn,function(dataImput){
		tipEmbalaje.push(dataImput.value);
		});
		namesValues = [].map.call(txtCantEmbIn,function(dataImput){
		txtCantEmb.push(dataImput.value);
		});
		namesValues = [].map.call(txtCantEmpqIn,function(dataImput){
		txtCantEmpq.push(dataImput.value);
		});
		namesValues = [].map.call(tipEmpaqueIn,function(dataImput){
		tipEmpaque.push(dataImput.value);
		});
		namesValues = [].map.call(txtCantPesoin,function(dataImput){
		txtCantPeso.push(dataImput.value);
		});
		txtCantPeso
		
				
		 $.ajax({
            type: 'POST',
            url: 'controlGenerador?action=CrearDeclaResiduo',
            data: {'idDeclaRes[]':idDeclaRes,'tipEmbalaje[]':tipEmbalaje,'txtCantEmb[]':txtCantEmb ,'tipEmpaque[]': tipEmpaque,'txtCantEmpq[]':txtCantEmpq,'txtCantPeso[]':txtCantPeso},
            statusCode: {
                404: function () {
                    alert('pagina no encontrada');
                },
                500: function () {
                    alert('Error servidor');
                }
            },
            success: function (datos) {
            	$('.alert').show()
              location.reload('controlGenerador?action=generador');
            }

        });
		   
 }
 
 
 
 function editar(bt)
{
var trs = $(bt).parent().parent()
$(bt).attr('class','btn btn-warning ti-save');
$("input", trs).removeAttr("readonly");
}

 
 
   $(document).ready(function () {
   
   
		$(".saleRow td:nth-child(1)").css("text-align","center");
 	  $(".saleRow td:nth-child(1)").html("<a onclick='remove(this)'><img id='myImg' src='assets/images/icon/market-value/triangle-down.png' /></a>");
   
   $("#btnDetail").click(function()
{
var index = $(this).parent().parent().index();
 var detail = $(this).parent().parent().next(); 
 var status = $(detail).css("display");
 console.log('Prueba de impresion');
  if(status == "none") 
  $(detail).css("display", "table-row");
   else $(detail).css("display", "none") 
   });
      
 });
 
 
 
 
       $(document).ready(function () {  
        //Detectar click en el checkbox superior de la lista
        $('#brand').on('click', function () {
          //verificar el estado de ese checkbox si esta marcado o no
          var checked_status = this.checked;
 
          /*
           * asignarle ese estatus a cada uno de los checkbox
           * que tengan la clase "selectall"
          */
          $(".brand").each(function () {
            this.checked = checked_status;
          });
        });
      });
 
 function table()
 {
 var index = $(this).parent().parent().index();
 var detail = $(this).parent().parent().next(); 
 var status = $(detail).css("display");
  if(status == "none") 
  $(detail).css("display", "table-row");
   else $(detail).css("display", "none")
 }
 
 function selTodas(val)
 {
 if(val==true)
 {
 var items = document.getElementsByName('brand[]');
               for (var i = 0; i < items.length; i++) {
                   if (items[i].type == 'checkbox')
                       items[i].checked = true;
               }
 }
 else 
 {
 var items = document.getElementsByName('brand[]');
               for (var i = 0; i < items.length; i++) {
                   if (items[i].type == 'checkbox')
                       items[i].checked = false;
               }
 }
 
 }
 
