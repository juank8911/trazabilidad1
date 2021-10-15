<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js" lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>sdstrp - Ver Perfil</title>
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
                            <h4 class="page-title pull-left">Ver</h4>
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
                <div class="row form-control">
                    <div class="form-group row">
                    <div class="col-md-2">
                        <label for="validationCustom01"><strong>Razon Social:</strong></label>
                    </div>
                    <div class="col-md-10">
                     <label for="validationCustom01"> ${nomPersona}</label>
                    </div>
                    </div>
                    <div class="form-group row"> 

                        <div class="col-md-2">
                            <label for="validationCustom01"><strong>Nombre Comercial:</strong></label>
                        </div>
                        <div class="col-md-10">
                            <label for="validationCustom01">${nom_comerc}</label>
                        </div>
                    </div>
                    <div class="form-group row">
                    <div class="col-md-2">
                     <label for="validationCustom01"><strong>Nit</strong></label>
                    </div>
                    <div class="col-md-2">
                     <label for="validationCustom01">999.999.999-1</label>
                    </div>
                                
                    <div class="col-md-2">
                     <label for="validationCustom01"><strong>CIUU</strong></label>
                    </div>
                    <div class="col-md-2">
                     <label for="validationCustom01">Recolector residuos</label>
                    </div>
                    </div>
                    <div class="form-group row">
                    <div class="form-group col-md-2">
                     <label for="validationCustom01"><strong>Direccion:</strong></label>
                    </div>
                        <div class="col-md-3">
                     <label for="validationCustom01">${direccion} , ${departamento} </label>
                    </div>
                        <div class="form-group col-md-2">
                     <label for="validationCustom01"><strong>Telefono:</strong></label>
                    </div>
                        <div class="col-md-2">
                     <label for="validationCustom01">${telefono}</label>
                    </div>
                        <div class="form-group col-md-2">
                     <label for="validationCustom01"><strong>Ext:</strong></label>
                    </div>
                        <div class="col-md-1">
                     <label for="validationCustom01">1023</label>
                    </div>
                        
                    </div>
                    <div class="form-group row">
                        <div class="form-group col-md-2">
                            <label for="validationCustom01"><strong>CC Representante</strong></label>
                        </div>
                        <div class="col-md-3">
                            <label for="validationCustom01">${doc}</label>
                        </div>
                        <div class="form-group col-md-2">
                            <label for="validationCustom01"><strong>Mail Representante</strong></label>
                        </div>
                        <div class="col-md-2">
                            <label for="validationCustom01">${correo}</label> 
                        </div>
                    </div>
                    
                    
                    
    
                </div>
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
