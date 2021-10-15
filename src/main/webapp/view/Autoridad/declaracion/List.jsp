 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags"%>
<%@page import="java.util.HashMap"%>
<%@taglib prefix="demo" tagdir="/WEB-INF/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js" lang="en">

<!--     <head> -->
<!--         <meta charset="utf-8"> -->
<!--         <meta http-equiv="x-ua-compatible" content="ie=edge"> -->
<!--         <title>sdstrp - Panel de Control</title> -->
<!--         <meta name="viewport" content="width=device-width, initial-scale=1"> -->
<!--         <link rel="shortcut icon" type="image/png" href="assets/images/icon/favicon.ico"> -->
<!--         <link rel="stylesheet" href="assets/css/bootstrap.min.css"> -->
<!--         <link rel="stylesheet" href="assets/css/font-awesome.min.css"> -->
<!--         <link rel="stylesheet" href="assets/css/themify-icons.css"> -->
<!--         <link rel="stylesheet" href="assets/css/metisMenu.css"> -->
<!--         <link rel="stylesheet" href="assets/css/owl.carousel.min.css"> -->
<!--         <link rel="stylesheet" href="assets/css/slicknav.min.css"> -->
<!--         <link rel="stylesheet" href="assets/css/coloresInst.css"> -->
<!--         amchart css -->
<!--         <link rel="stylesheet" href="https://www.amcharts.com/lib/3/plugins/export/export.css" type="text/css" media="all" /> -->
<!--         others css -->
<!--         <link rel="stylesheet" href="assets/css/typography.css"> -->
<!--         <link rel="stylesheet" href="assets/css/default-css.css"> -->
<!--         <link rel="stylesheet" href="assets/css/styles.css"> -->
<!--         <link rel="stylesheet" href="assets/css/responsive.css"> -->
<!--         modernizr css -->
<!--         <script src="assets/js/vendor/modernizr-2.8.3.min.js"></script> -->
<!--     </head> -->

<demo:header></demo:header>

    <body>
        <!--[if lt IE 8]>
                <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
            <![endif]-->
        <!-- preloader area start -->

        <%@include file ="../sidebarNavbar.jsp" %>
            <!-- main content area start -->
            <div class="main-content">

                <!-- page title area start -->
                <div>
                    <div class="row align-items-center">
                        <div class="col-sm-6">
                            <div class="breadcrumbs-area">
                                <h4 class="page-title pull-left">Listado de declaraciones </h4>
                                <ul class="breadcrumbs pull-left">
                                    <li><span>de residuos </span></li>
                                </ul>
                            </div>
                        </div>
                        <!-- FIN DE MENU  -->
                    </div>
                </div>
                </br>
                <!-- main content area inicio -->
                
                <div class="text-uppercase bg-success mb-3">
                           <button type="button" class="fa fa-newspaper-o btn btn-success"> Columnas</button>
                           <button type="button" class="fa fa-floppy-o btn btn-success"> Exportar</button>
                           <div class="pull-right">
                                <form action="#">
                                <input type="text" name="search" placeholder="Buscar..." required="">
                                <i class="ti-search col-md-2"></i>
                            </form>
                           </div>
                           </div>
                <div class="single-table">
                                <div class="table-responsive">                                    
 <%
	HashMap<String, Object> data = new HashMap<String, Object>();
	data.put("dec_id", "#=dec_id#");
%>           

   <kendo:grid name="grid" pageable="true" height="550px" detailTemplate="template"
	dataBound="dataBound">
    <kendo:grid-columns>
        <kendo:grid-column title="Numero declaracion" field="dec_id" width="80px" />
        <kendo:grid-column title="Fecha Declaracion" field="dec_gen_fecha_gen" width="120px" format="{0:MM/dd/yyyy}" />
        <kendo:grid-column title="Generador" field="sedGen.sed_nombre"  width="120px" />
        <kendo:grid-column title="Transportador" field="sedTrn.sed_nombre"  width="120px" />
        <kendo:grid-column title="Gestor" field="sedGes.sed_nombre"  width="120px" />
        <kendo:grid-column title="Estado" field="estado"  width="120px" />
        
    </kendo:grid-columns>
    <kendo:dataSource pageSize="14" >
        <kendo:dataSource-schema data="data" total="total">
        <kendo:dataSource-schema-model id="dec_id">
                        <kendo:dataSource-schema-model-fields>
                            <kendo:dataSource-schema-model-field name="dec_id" type="number" />
                            <kendo:dataSource-schema-model-field name="dec_gen_fecha_gen" type="date" />

                        </kendo:dataSource-schema-model-fields>
                    </kendo:dataSource-schema-model>
        </kendo:dataSource-schema>
        <kendo:dataSource-transport>
            <kendo:dataSource-transport-read url="/trazabilidad/autoridadServlet?action=autHist" />
        </kendo:dataSource-transport>
    </kendo:dataSource>
</kendo:grid>

<kendo:grid-detailTemplate id="template">
    <kendo:tabStrip name="tabStrip_#=dec_id#">
    <kendo:tabStrip-animation>
    	<tabStrip-animation-open effects="fadeIn"/>
    </kendo:tabStrip-animation>
        <kendo:tabStrip-items>
            <kendo:tabStrip-item text="Estado De la Declaracion" selected="true">
                <kendo:tabStrip-item-content>
                    <kendo:grid name="grid_#=dec_id#" pageable="true" scrollable="false">
                        <kendo:grid-columns>
                            <kendo:grid-column title="Numero Declaracion" field="dec_id" width="100px" />
                            <kendo:grid-column title="Fecha Envio" field="dec_gen_fecha_trn" width="110px" />
                            <kendo:grid-column title="Fecha Aprobacion" field="dec_trn_fecha_trn" width="110px" />
                            <kendo:grid-column title="Fecha Finalizacion" field="dec_ges_fecha_ges" width="110px" />
                        </kendo:grid-columns>
                        <kendo:dataSource pageSize="15" >
                            <kendo:dataSource-schema data="data" total="total"></kendo:dataSource-schema>
                            <kendo:dataSource-transport>
                                <kendo:dataSource-transport-read url="/trazabilidad/autoridadServlet?action=autHistData" data="<%=data %>"  />
                            </kendo:dataSource-transport>
                        </kendo:dataSource>
                    </kendo:grid>
                </kendo:tabStrip-item-content>
            </kendo:tabStrip-item>
            <kendo:tabStrip-item text="Rechasada ">
                <kendo:tabStrip-item-content>
                    <div class='employee-details'>
                        <ul>
                            <li><label>Rechasada por:</label>#= rechasoP #</li>
                            <li><label>Fecha Rechaso:</label>#= fecha_rechasoP #</li>
                            <li><label>Observaciones:</label>#= dec_ges_observacion #</li>
                        </ul>
                    </div>
                </kendo:tabStrip-item-content>
            </kendo:tabStrip-item>
        </kendo:tabStrip-items>
    </kendo:tabStrip>
</kendo:grid-detailTemplate>
                                </div>
                                
                                <script>
        function dataBound() {
            this.expandRow(this.tbody.find("tr.k-master-row").first());
        }
</script>  
    
<style>
    .k-detail-cell .k-tabstrip .k-content {
        padding: 0.2em;
    }
    .employee-details ul
    {
        list-style:none;
        font-style:italic;
        margin: 15px;
        padding: 0;
    }
    .employee-details ul li
    {
        margin: 0;
        line-height: 1.7em;
    }

    .employee-details label
    {
        display:inline-block;
        width:90px;
        padding-right: 10px;
        text-align: right;
        font-style:normal;
        font-weight:bold;
    }
</style>
                            </div>
                    <!-- main content area end -->
                    <!-- footer area start-->
<!--                     <footer> -->
<!--                         <div class="footer-area"> -->
<!--                             <p>Copyright ® 2020 <a href="http://www.ideam.gov.co/">IDEAM.</a>Todos los derechos reservados.</p> -->
<!--                         </div> -->
<!--                     </footer> -->
<!--                     footer area end -->
<!--                 </div> -->
<!--                 page container area end -->

<!--                 jquery latest version -->
<!--                 <script src="assets/js/vendor/jquery-2.2.4.min.js"></script> -->
<!--                 bootstrap 4 js -->
<!--                 <script src="assets/js/popper.min.js"></script> -->
<!--                 <script src="assets/js/bootstrap.min.js"></script> -->
<!--                 <script src="assets/js/owl.carousel.min.js"></script> -->
<!--                 <script src="assets/js/metisMenu.min.js"></script> -->
<!--                 <script src="assets/js/jquery.slimscroll.min.js"></script> -->
<!--                 <script src="assets/js/jquery.slicknav.min.js"></script> -->

<!--                 start chart js -->
<!--                 <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.min.js"></script> -->
<!--                 start highcharts js -->
<!--                 <script src="https://code.highcharts.com/highcharts.js"></script> -->
<!--                 start zingchart js -->
<!--                 <script src="https://cdn.zingchart.com/zingchart.min.js"></script> -->
<!--                 <script> -->
<!-- //                     zingchart.MODULESDIR = "https://cdn.zingchart.com/modules/"; -->
<!-- //                     ZC.LICENSE = ["569d52cefae586f634c54f86dc99e6a9", "ee6b7db5b51705a13dc2339db3edaf6d"]; -->
<!--                 </script> -->
<!--                 all line chart activation -->
<!--                 <script src="assets/js/line-chart.js"></script> -->
<!--                 all pie chart -->
<!--                 <script src="assets/js/pie-chart.js"></script> -->
<!--                 others plugins -->
<!--                 <script src="assets/js/plugins.js"></script> -->
<!--                 <script src="assets/js/scripts.js"></script> -->
<demo:footer></demo:footer>

                </body>

                </html>
