<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html class="no-js" lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>sdstrp - Panel de Control</title>
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
        
        <%@include file ="sidebarNavbar.jsp" %>
   
            <div class="main-content">

                <!-- page title area start -->
                <div>
                    <div class="row align-items-center">
                        <div class="col-sm-8">
                            <div class="breadcrumbs-area">
                                <div class="col-sm-8">
                                <h4 class="page-title pull-left">Panel de Control</h4>
                                <ul class="breadcrumbs pull-left">
                                    
                                    <li><span>Autoridad</span></li>
                                    
                                </ul>
                                </div>
                            </div>
                        </div>
                        <!-- FIN DE MENU  -->
                    </div>
                </div>
                <!-- page title area end -->
                <div class="main-content-inner">
                    <div class="row">
                        <!-- seo fact area start -->
                        <div class="col-lg-12">
                            <div class="row">
                                <div class="col-3 mt-4 mb-3">
                                    <div class="card">
                                        <div class="seo-fact sbg1">
                                            <div class="p-4 d-flex justify-content-between align-items-center">
                                                <div class="seofct-icon"><i class="ti-archive"></i> Generadores</div>
                                                <h2>${gendhSedesAut}</h2>
                                            </div>
                                            
                                        </div>
                                    </div>
                                </div>
                                <div class="col-3 mt-4 mb-3">
                                    <div class="card">
                                        <div class="seo-fact sbg2">
                                            <div class="p-4 d-flex justify-content-between align-items-center">
                                                <div class="seofct-icon"><i class="ti-truck"></i> Transportadores</div>
                                                <h2>${trndhSedesAut}</h2>
                                            </div>
                                            
                                        </div>
                                    </div>
                                </div>
                                <div class="col-3 mt-4 mb-4 mb-lg-4">
                                    <div class="card">
                                        <div class="seo-fact sbg4">
                                            <div class="p-3 d-flex justify-content-between align-items-center">
                                                <div class="seofct-icon"><i class="fa fa-industry"></i>_ Gestores</div>
                                                <h2>${gestordhSedesAut}</h2>
                                            </div>
                                            
                                        </div>
                                    </div>
                                </div>
                                <div class=" col-3 mt-4">
                                    <div class="card">
                                        <div class="seo-fact sbg3">
                                            <div class="p-3 d-flex justify-content-between align-items-center">
                                                <div class="seofct-icon"><i class="fa fa-building-o"></i> Establecimientos</div>
                                                <h2>${SedesAut}</h2>
                                            </div>
                                            
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- seo fact area end -->


                    </div>
                    <!-- page title area end -->
                    
                    <div class="row align-items-start">
                   <div class=" col-9">
              
                        <canvas id="grafico1" width="80" height="60"></canvas>
     
                   </div>
                    
                    
                        <div class="col-3">

                           
                            <div class="card mt-1">
                                <div class="seo-fact sbg1">
                                    <div class="p-3 d-flex justify-content-between align-items-center">
                                        <div class="seofct-icon"><i class="ti-archive"></i>No Enviadas</div>
                                        <h2>${noEnviadasAut}</h2>
                                    </div>
                                </div>
                            </div>
                            
                                    <div class="card mt-1">
                                        <div class="seo-fact sbg1">
                                            <div class="p-3 d-flex justify-content-between align-items-center">
                                                <div class="seofct-icon"><i class="ti-layout-media-right"></i> Enviadas</div>
                                                <h2>${enviadasAut}</h2>
                                            </div>
                                            
                                        </div>
                                    </div>
                                
                                    <div class="card mt-1">
                                        <div class="seo-fact sbg2">
                                            <div class="p-3 d-flex justify-content-between align-items-center">
                                                <div class="seofct-icon"><i class="ti-truck"></i> Aprobadas</div>
                                                <h2>${aprovadasAut}</h2>
                                            </div>
                                            
                                        </div>
                                    </div>
                                
                                    <div class="card mt-1">
                                        <div class="seo-fact sbg3">
                                            <div class="p-3 d-flex justify-content-between align-items-center">
                                                <div class="seofct-icon"><i class="ti-truck"></i>Rechazadas Transportador</div>
                                                <h2>${rechasadasTAut}</h2>
                                            </div>
                                            
                                        </div>
                                    </div>
                                            <div class="card mt-1">
                                        <div class="seo-fact sbg2">
                                            <div class="p-3 d-flex justify-content-between align-items-center">
                                                <div class="seofct-icon"><i class="fa fa-industry"></i>Finalizadas</div>
                                                <h2>${finalizadasAut}</h2>
                                            </div>
                                            
                                        </div>
                                    </div>

                                    <div class="card mt-1">
                                        <div class="seo-fact sbg3">
                                            <div class="p-3 d-flex justify-content-between align-items-center">
                                                <div class="seofct-icon"><i class="fa fa-industry"></i> Rechazadas Gestor</div>
                                                <h2>${rechasadasGAut}</h2>
                                            </div>
                                            
                                        </div>
                                    </div>

                        </div>
 
                   </div>
                    <div class="row">
                        
                <h:panelGroup styleClass="p-grid" layout="block">
			<h:panelGroup styleClass="col-md-6 col-sm-6 col-xs-12" layout="block">
				<p:radarChart id="radarChart" " style="width:100%" />
			</h:panelGroup>
			<h:panelGroup styleClass="p-col-12 p-sm-6 p-md-3" layout="block">
				<h:panelGroup styleClass="info-box" layout="block">
					<h:panelGroup styleClass="info-box-icon bg-green">
						<h:outputText value="" escape="false" />
					</h:panelGroup>	
					<h:panelGroup styleClass="info-box-content" layout="block">
						<h:outputText value="" styleClass="info-box-text" />	
						<h:outputText value="" styleClass="info-box-number" />
					</h:panelGroup>
				</h:panelGroup>	
			</h:panelGroup>
			</h:panelGroup>
  
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
