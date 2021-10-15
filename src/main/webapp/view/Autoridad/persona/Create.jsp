<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js" lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>sdstrp - Crea Perfil</title>
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
            <!-- page title area start -->
            <div>
                <div class="row align-items-center">
                    <div class="col-sm-6">
                        <div class="breadcrumbs-area">
                            <h4 class="page-title pull-left">Ingresar</h4>
                            <ul class="breadcrumbs pull-left">
                                <li><span>Perfil</span></li>
                            </ul>
                        </div>
                    </div>
                    <!-- FIN DE MENU  -->
                </div>
            </div>
            <!-- page title area end -->
            <!-- main content area inicio -->
            <div class="main-content-inner">
                <br>
                <div class="row">
                            <!-- Server side start -->
                                <div class="card mt-4">
                                    
                                        <form method="post" action="control?action=perfilLee" class="needs-validation form-control" novalidate="">

                                            <div class="form-row">
                                                <div class="form-group col-md-2">
                                                 <label for="validationCustom01">Nombre:</label>
                                                </div>
                                                <div class="form-group col-md-4">
                                                 <input type="text" name="nomUsu" class="form-control" id="validationCustom02"  value="" required=""> 
                                                </div>
                                                <div class="form-group col-md-2">
                                                 <label for="example-text-input" class="col-form-label">Apellido:</label>
                                                </div>
                                                <div class="form-group col-md-4">
                                                <input type="text" name="apeUsu" class="form-control" id="validationCustom02"  value="" required="">
                                                </div>
                                                <div class="form-group col-md-2">
                                                     <label for="example-text-input" class="col-form-label">Tipo de Documento:</label>
                                                </div>
                                                <div class="form-group col-md-4">
                                                     <select class="form-control">
                                                            <option>Select</option>
                                                            <option>Large select</option>
                                                            <option>Small select</option>
                                                        </select>
                                                </div>
                                                <div class="form-group col-md-2">
                                                     <label for="example-text-input" class="col-form-label">Nro de Documento:</label>
                                                </div>
                                                <div class="form-group col-md-4">
                                                    <input type="number" name="telUsu" class="form-control" id="validationCustom02"  value="" required="">
                                                </div>
                                                <div class="form-group col-md-2">
                                                    <label for="validationCustom01">Cargo </label>
                                                </div>
                                                <div class="form-group col-md-4">
                                                    <input type="text" name="nroDocu" class="form-control" id="validationCustom02"  value="" required="">
                                                </div>
                                                <div class="form-group col-md-2">
                                                     <label for="example-text-input" class="col-form-label">Telefono </label>
                                                </div>
                                                <div class="form-group col-md-4">
                                                    <input type="email" name="nroDocu" class="form-control" id="validationCustom02"  value="" required="">
                                                </div>
                                                
                                               <div class="form-group col-md-2">
                                                    <label for="validationCustom01">Correo Electronico</label>
                                               </div>
                                                <div class="form-group col-md-10">
                                                    <input type="text" name="nroDocu" class="form-control" id="validationCustom02"  value="" required="">
                                                </div>
                                            </div>
                                            <div class="form-group form-control">

                                                    <td> <button class="btn btn-success" type="submit">Crear </button>
                                                    <td> <button class="btn btn-success" type="submit"><a href="control?action=perfilLee" class="text-white">Cancelar</a></button>
                                            </div>
                                        </form>         
                                </div>
                            
                            <!-- Server side end -->
    
                </div>
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
