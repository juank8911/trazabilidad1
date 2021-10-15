<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html class="no-js" lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>sdstrp - Crear Establecimiento</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" type="image/png"
	href="assets/images/icon/favicon.ico">
<link rel="stylesheet" href="assets/css/bootstrap.min.css">
<link rel="stylesheet" href="assets/css/font-awesome.min.css">
<link rel="stylesheet" href="assets/css/themify-icons.css">
<link rel="stylesheet" href="assets/css/metisMenu.css">
<link rel="stylesheet" href="assets/css/owl.carousel.min.css">
<link rel="stylesheet" href="assets/css/slicknav.min.css">
<link rel="stylesheet" href="assets/css/checkbox.css">
<link rel="stylesheet" href="assets/css/coloresInst.css">
<!-- amchart css -->
<link rel="stylesheet"
	href="https://www.amcharts.com/lib/3/plugins/export/export.css"
	type="text/css" media="all" />
<!-- others css -->
<link rel="stylesheet" href="assets/css/typography.css">
<link rel="stylesheet" href="assets/css/default-css.css">
<link rel="stylesheet" href="assets/css/styles.css">
<link rel="stylesheet" href="assets/css/responsive.css">
<!-- modernizr css -->
<script src="assets/js/vendor/modernizr-2.8.3.min.js"></script>
<!-- jquery latest version -->
<script src="assets/js/vendor/jquery-2.2.4.min.js"></script>
<script src="assets/js/listSTG.js"></script>
</head>

<body>
	<%@include file="../sidebarNavbar.jsp"%>
	<!-- main content area start -->
	<div class="main-content">

		<!-- page title area start -->
		<div>
			<div class="row align-items-center">
				<div class="col-sm-7">
					<div class="breadcrumbs-area">
						<h4 class="page-title pull-left">Crear de Establecimiento</h4>
						<ul class="breadcrumbs pull-left">
							<li><span> en el sistema de información</span></li>
						</ul>
					</div>
				</div>
				<!-- FIN DE MENU  -->
			</div>
		</div>
		<!-- page title area end -->
		<!-- main content area inicio -->
		<div class="main-content-inner">
			<div class="row">
				<!-- Server side start -->
				<div class="main-content-inner">
					<div class="row">
						<form method="post" action="control?"
							class="needs-validation form-control" novalidate="">

							<div class="form-row">
								<div class="col-md-12 form-row">
									<div class="form-group col-md-2">
										<label for="validationCustom01">Nombre
											Establecimiento: </label>
									</div>
									<div class="form-group col-md-10">
										<input type="text" name="txtNombre"
											class="form-control is-valid" id="validationCustom02"
											placeholder="" value="" required="">
									</div>
								</div>

								<div class="col-md-12 form-row">
									<div class="form-group col-md-2">
										<label for="validationCustom01">Empresa:</label>
									</div>
									<div class="form-group col-md-10|">
									
										<select data-live-search="true" name="txtEmpr" id="txtEmpr"
											data-live-search-style="startsWith"
											class="form-control is-valid">
											<c:forEach items="${listaEmpresasAu}" var="emp">
												<option value="${emp.get(3)}">${emp.get(1)} : ${emp.get(0)}</option>
											</c:forEach>
										</select>
									</div>

									<div class="col-md-12 form-row">
									<div class="form-group col-md-2">
										<label for="validationCustom01">Departamento:</label>
									</div>
									<div class="form-group col-md-5">
										<select class="form-control is-valid" name="txtDepar"
											id="txtDepar">
											<option>----SELECT----</option>
											<c:forEach items="${listaDepar}" var="dep">
												<option value="${dep.id_dept}">${dep.dept_nombre}</option>
											</c:forEach>
										</select>
									</div>
								</div>

								<div class="col-md-12 form-row">
									<div class="form-group col-md-2">
										<label class="my-1 mr-2" for="validationCustom01">Fecha
											Inicio Actividad:</label>
									</div>
									<div class="form-group col-md-2">
										<input class="form-control is-valid" type="date"
											name="txtInicio">
									</div>

									<div class="form-group col-md-2">
										<label for="example-text-input" class="col-form-label">Municipio:</label>
									</div>
									<div class="form-group col-md-6">
										<select class="form-control is-valid" name="txtMun"
											id="txtMun">
											<option>SELECIONE UN DEPARTAMENO</option>
										</select>
									</div>
								</div>
								<div class="col-md-12 form-row">
									<div class="form-group col-md-2">

										<label for="validationCustom01">Numero de Empleados: </label>
									</div>
									<div class="form-group col-md-2">
										<input type="number" name="txtNuEmpl"
											class="form-control is-valid" id="txtNuEmpl" placeholder=""
											value="" required="">
									</div>



									<div class="form-group col-md-2">
										<label for="validationCustom01">Direccion:</label>
									</div>
									<div class="form-group col-md-4">
										<input type="text" name="txtDirec"
											class="form-control is-valid" id="validationCustom02"
											value="" required="">
									</div>
									<div class="col-md-12 form-row">

										<div class="col-md-12 form-row">
											<div class="form-group col-md-1">
												<label for="example-text-input" class="col-form-label">Telefono:</label>
											</div>
											<div class="form-group col-md-2">
												<input type="number" name="txtTel"
													class="form-control is-valid" id="validationCustom02"
													value="" required="">
											</div>
											<div class="form-group col-md-1">
												<label for="example-text-input" class="col-form-label">Ext:</label>
											</div>

											<div class="form-group col-md-2">
												<input type="number" name="txtExt"
													class="form-control is-valid" id="validationCustom02"
													value="" required="">
											</div>
										</div>

<!-- 										<div class="col-md-12 form-row"> -->
<!-- 											<div class="form-row"> -->
<!-- 												<div -->
<!-- 													class="form-check form-check-inline col-md-10 form-group"> -->
<!-- 													<div class="form-group col-md-2"> -->
<!-- 														<label for="validationCustom01">CIUU:</label> -->
<!-- 													</div> -->
<!-- 													<div class="form-group col-md-10"> -->
<!-- 														<select class="form-control is-valid" name="txtCiiu"> -->
<%-- 															<c:forEach items="${listCiiu}" var="ciiu"> --%>
<%-- 																<option value="${ciiu.cii_id}">${ciiu.cii_id}/ --%>
<%-- 																	${ciiu.cii_nombre}</option> --%>
<%-- 															</c:forEach> --%>
<!-- 														</select> -->
														
<!-- 													</div> -->
<!-- 												</div> -->
<!-- 											</div> -->
<!-- 										</div> -->

										<div class="form-row">
											<div
												class="form-check form-check-inline col-md-12 form-group">
												<div
													class="custom-control custom-checkbox checkbox-xl col-md-4 form-group">
													<input type="checkbox" class="custom-control-input"
														name="btnGene" id="btnGene"> <label
														class="custom-control-label" for="btnGene">Generador</label>
												</div>

												<div
													class="custom-control custom-checkbox checkbox-xl col-md-4 form-group">
													<input type="checkbox" class="custom-control-input"
														name="btnTrans" id="btnTrans"> <label
														class="custom-control-label" for="btnTrans">Transportador</label>
												</div>

												<div
													class="custom-control custom-checkbox checkbox-xl col-md-3 form-group">
													<input type="checkbox" class="custom-control-input"
														name="btnGes" id="btnGes"> <label
														class="custom-control-label" for="btnGes">Gestor</label>
												</div>
											</div>
											
											
																					<div class="col-md-12 form-row">
											<div class="form-group col-md-1">
												<label for="example-text-input" class="col-form-label">Usuario:</label>
											</div>
											<div class="form-group col-md-4">
												<input type="text" name="txtUsu" class="form-control is-valid" id="inUsu"
													value="" required="">
											</div>
											<div class="form-group col-md-1">
												<label for="example-text-input" class="col-form-label">Contraseña:</label>
											</div>

											<div class="form-group col-md-4">
												<input type="text" name="txtContra"
													class="form-control is-valid" id="inContra" value="" required="">
											</div>
										</div>
											
											
											
											<div class="form-group col-md-2">
												<button class="btn btn-success form-control" type="submit"
													name="action" id="inUsu" value="sedeRegista">Registrar</button>
											</div>
											<div class="form-group col-md-2">
												<button class="btn btn-success" type="submit" name="action"
													value="sedeLee">Cancelar</button>
											</div>
										</div>
									</div>
						</form>
<script type="text/javascript">
$(document).ready(function () {
    $('select[name=txtEmpr]').on('change', function () {

        $.ajax({
            type: 'GET',
            url: 'control?action=nuevoUsu',
            data: 'idEmp=' + $('select[name=txtEmpr]').val(),
            statusCode: {
                404: function () {
                    alert('pagina no encontrada');
                },
                500: function () {
                    alert('Error servidor');
                }
            },
            success: function (datos) {
                // limpia el valor del comboBox
                //console.log('entrando a funcion succes');
                var proDatos = datos.split("|");
					
					$('input[name=txtContra].sitebg').val(datos);
					$('#inUsu').attr('value', datos);
					$('#inContra').attr('value', datos);
//                 for (var i = 0; i < proDatos.length - 1; i++) { // inicia ciclo para extraer los datos del arreglo

//                     var id_STG = proDatos[i].split("-")[0];
//                     var nombre_STG = proDatos[i].s1plit("-")[1];
//                     $('select[name=subTipGest]').append('<option value= "' + id_STG + '">' + nombre_STG + '</option>') // imprimir datos en el comboBox
//                 }
            }

        });
        
    })
    });
</script>
					</div>
				</div>
				<!-- Server side end -->

			</div>
			<!-- main content area end -->
		</div>
		<!-- footer area start-->
		<footer>
			<div class="footer-area">
				<p>
					Copyright ® 2020 <a href="http://www.ideam.gov.co/">IDEAM.</a>Todos
					los derechos reservados.
				</p>
			</div>
		</footer>
		<!-- footer area end-->
	</div>
	<!-- page container area end -->

	<!-- jquery latest version -->
	<script src="assets/js/vendor/jquery-2.2.4.min.js"></script>
	<!-- bootstrap 4 js -->
	<script src="assets/js/popper.min.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>
	<script src="assets/js/owl.carousel.min.js"></script>
	<script src="assets/js/metisMenu.min.js"></script>
	<script src="assets/js/jquery.slimscroll.min.js"></script>
	<script src="assets/js/jquery.slicknav.min.js"></script>

	<!-- start chart js -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.min.js"></script>
	<!-- start highcharts js -->
	<script src="https://code.highcharts.com/highcharts.js"></script>
	<!-- start zingchart js -->
	<script src="https://cdn.zingchart.com/zingchart.min.js"></script>
	<script>
                zingchart.MODULESDIR = "https://cdn.zingchart.com/modules/";
                ZC.LICENSE = ["569d52cefae586f634c54f86dc99e6a9", "ee6b7db5b51705a13dc2339db3edaf6d"];
            </script>
	<!-- all line chart activation -->
	<script src="assets/js/line-chart.js"></script>
	<!-- all pie chart -->
	<script src="assets/js/pie-chart.js"></script>
	<!-- others plugins -->
	<script src="assets/js/plugins.js"></script>
	<script src="assets/js/scripts.js"></script>
</body>

</html>
