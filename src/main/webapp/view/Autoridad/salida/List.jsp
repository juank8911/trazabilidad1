<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js" lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>sdstrp - Declaracion</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="shortcut icon" type="image/png" href="assets/images/icon/favicon.ico">
        <link rel="stylesheet" href="assets/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/css/font-awesome.min.css">
        <link rel="stylesheet" href="assets/css/themify-icons.css">
        <link rel="stylesheet" href="assets/css/metisMenu.css">
        <link rel="stylesheet" href="assets/css/owl.carousel.min.css">
        <link rel="stylesheet" href="assets/css/slicknav.min.css">
        <link rel="stylesheet" href="assets/css/coloresInst.css">
        <!-- amchart css -->
        <link rel="stylesheet" href="https://www.amcharts.com/lib/3/plugins/export/export.css" type="text/css" media="all" />
        <!-- others css -->
        <link rel="stylesheet" href="assets/css/typography.css">
        <link rel="stylesheet" href="assets/css/default-css.css">
        <link rel="stylesheet" href="assets/css/styles.css">
        <link rel="stylesheet" href="assets/css/responsive.css">
        <!-- modernizr css -->
        <script src="assets/js/vendor/modernizr-2.8.3.min.js"></script>
    </head>

    <body>
        <%@include file ="../sidebarNavbar.jsp" %>
            <!-- main content area start -->
            <div class="main-content">
               

                <!-- page title area start -->
                <div>
                    
                    <form class="form-inline form-control">
                        
                        <div class="input-group col-4">
                        <div class="input-group-prepend">
                            <label class="input-group-text" for="inputGroupSelect01">Filtrar por: </label>
                        </div>
                        <select class="custom-select" id="inputGroupSelect01">
                            <option selected>Seleccione...</option>
                            <option value="1">Fecha en la que la declaración fue marcada como enviada por el generador</option>
                            <option value="2">Fecha de entrega de los residuos al transportador declarada por el generador</option>
                            <option value="3">Fecha de recogida de residuos declarada por el transportador</option>
                            <option value="4">Fecha en la que la declaración fue marcada como aprobada por el transportador</option>
                            <option value="5">Fecha de entrega de los resituos al gestor declarada por el transportador</option>
                            <option value="6">Fecha de recepción de los residuos declarada por el gestor</option>
                            <option value="7">Fecha en la que la declaración fue maracada como finalizada por el gestor</option>
                        </select>
                        </div> 
             
                        <label for="inlineFormInputGroup" class="col-1">Desde</label>
                        
                            <input class="form-control" type="date" value="2020-08-19" id="example-date-input">
                        
                        <label for="inlineFormInputGroup" class="col-1">Hasta</label>
                        
                            <input class="form-control" type="date" value="2020-08-19" id="example-date-input">
                        <button type="submit" class="btn btn-success">Buscar</button>
                        </form>
                    
                        
                    <div class="row align-items-center">
                        <div class="col-sm-6">
                            <div class="breadcrumbs-area">
                                <h4 class="page-title pull-left">Listado Salidas</h4>
                                <ul class="breadcrumbs pull-left">
                                    <li><span>de informacion.</span></li>
                                </ul>
                            </div>
                        </div>
                        <!-- FIN DE MENU  -->
                    </div>
                    
                </div>
                <!-- page title area end -->
                    <!-- table success start -->
                            <br />
                            
                            <div class="text-uppercase bg-success mb-3">
                           <button type="button" class="fa fa-newspaper-o btn btn-success"> Columnas</button>
                           <button type="button" class="fa fa-floppy-o btn btn-success">Exportar</button>
                           </div>
                            
                                    <table id="dTabla">
                                        <thead >
                                            <tr>
                                                <th scope="col" colspan="7">Generador</th>
                                                
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <th scope="row">Declaracion</th>
                                                <th scope="row">Empresa</th>
                                                <th scope="row">Nombre</th>
                                                <th scope="row">Direccion</th>
                                                <th scope="row">Tipo</th>
                                                <th scope="row">Fecha</th>
                                                <th scope="row">Opciones</th>
                                                
                                                
                                            </tr>
                                            <tr>
                                                <td>Declaracion hecha </td>
                                                <td>Empresa registrada</td>
                                                <td>Nombre empresa</td>
                                                <td>Direccion empresa</td>
                                                <td>Tipo Residuo </td>
                                                <td>01/01/2020 </td>
                                                <td><button type="button" class="btn btn-success btn-xs ti-search"></button>
                                                    
                                                <button type="button" class="btn btn-success btn-xs ti-pencil"></button></td>
                                            </tr>
                                        </tbody>
                                    </table>
                                    </br>
                                    <table id="dTabla">
                                        <thead >
                                            <tr>
                                                <th scope="col" colspan="7">Transportador</th>
                                                
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <th scope="row">Declaracion</th>
                                                <th scope="row">Empresa</th>
                                                <th scope="row">Nombre</th>
                                                <th scope="row">Direccion</th>
                                                <th scope="row">Tipo</th>
                                                <th scope="row">Fecha</th>
                                                <th scope="row">Opciones</th>
                                                
                                                
                                            </tr>
                                            <tr>
                                                <td>Declaracion hecha </td>
                                                <td>Empresa registrada</td>
                                                <td>Nombre empresa</td>
                                                <td>Direccion empresa</td>
                                                <td>Tipo Residuo </td>
                                                <td>01/01/2020 </td>
                                                <td><button type="button" class="btn btn-success btn-xs ti-search"></button>
                                                    
                                                <button type="button" class="btn btn-success btn-xs ti-pencil"></button></td>
                                            </tr>
                                        </tbody>
                                    </table>
                                    </br>
                                    <table id="dTabla">
                                        <thead >
                                            <tr>
                                                <th scope="col" colspan="7">Gestor</th>
                                                
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <th scope="row">Declaracion</th>
                                                <th scope="row">Empresa</th>
                                                <th scope="row">Nombre</th>
                                                <th scope="row">Direccion</th>
                                                <th scope="row">Tipo</th>
                                                <th scope="row">Fecha</th>
                                                <th scope="row">Opciones</th>
                                                
                                                
                                            </tr>
                                            <tr>
                                                <td>Declaracion hecha </td>
                                                <td>Empresa registrada</td>
                                                <td>Nombre empresa</td>
                                                <td>Direccion empresa</td>
                                                <td>Tipo Residuo </td>
                                                <td>01/01/2020 </td>
                                                <td><button type="button" class="btn btn-success btn-xs ti-search"></button>
                                                    
                                                <button type="button" class="btn btn-success btn-xs ti-pencil"></button></td>
                                            </tr>
                                        </tbody>
                                    </table>
                                    </br>
                                    <button class="btn btn-success" type="submit"><a href="control?action=autoridad" class="text-white">Cancelar</a></button>
                              
                   

                    <!-- table success end -->
                <!-- main content area end -->
                <!-- footer area start-->
                <footer>
                    <div class="footer-area">
                        <p>Copyright ® 2020 <a href="http://www.ideam.gov.co/">IDEAM.</a>Todos los derechos reservados.</p>
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
            <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.min.js"></script>
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
