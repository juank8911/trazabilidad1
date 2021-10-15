<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags"%>
<%@taglib prefix="demo" tagdir="/WEB-INF/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<demo:header/>

    <body>
       <%@include file ="../sidebarNavbar.jsp" %>
       
            <!-- sidebar menu area end -->
            <!-- main content area start -->
            <div class="main-content">

                <!-- page title area start -->
                <div>
                    <div class="row align-items-center">
                        <div class="col-sm-6">
                            <div class="breadcrumbs-area">
                                <h4 class="page-title pull-left">Listado de Usuarios</h4>
                                <ul class="breadcrumbs pull-left">
                                    <li><span>Registrados en el sistema de informacion</span></li>
                                </ul>
                            </div>
                        </div>
                        <!-- FIN DE MENU  -->
                    </div>
                </div>
                <!-- page title area end -->
                <div class="main-content-inner">
                    <!-- table success start -->

                    <div class="card">
                        <div class="card-body">
                            <div class="text-uppercase bg-success mb-3">
                           <button type="button" class="fa fa-newspaper-o btn btn-success"> Columnas</button>
                           <button type="button" class="fa fa-floppy-o btn btn-success">Exportar</button>
                           <div class="pull-right">
                                <form action="#">
                                <input type="text" name="search" placeholder="Buscar..." required="">
                                <i class="ti-search col-md-1"></i>
                            </form>
                           </div>
                           </div>
                           
                           			<kendo:grid name="usuario"  pageable="true" groupable="true" columnMenu="true" width="99%">
                           				<kendo:grid-toolbar>
                           					<kendo:grid-toolbarItem name="excel"></kendo:grid-toolbarItem>
                           				</kendo:grid-toolbar>
                           				<kendo:grid-excel fileName="Usuarios_autoridad.xlsx" filterable="true" proxyURL="/trazabilidad/autoridadServlet?action=outExcel" />
                           					<kendo:dataSource pageSize="5">
                           							<kendo:dataSource-transport>
                           									<kendo:dataSource-transport-read url="/trazabilidad/autoridadServlet?action=autUsu"/>
                           							</kendo:dataSource-transport>
                           							<kendo:grid-columns>
                           									<kendo:grid-column title="Identificacion" field="usr_documento"/>
                           									<kendo:grid-column title="Usuario" field="usr_nombre"/>
                           									<kendo:grid-column title="Estado" field="estadoA"/>                           									
                           									<kendo:grid-column>
                           										<kendo:grid-column-command>
                           														<kendo:grid-column-commandItem click="newContra" name="Recuperar Contrseña" text="Recuperar Contraseña" >
                           															<kendo:grid-column-commandItem-click>
                           															<script type="text/javascript">
                           															function newContra(e)
                           															{
                           																var detailsTemplate = kendo.template($("#template").html());
                           																e.preventDefault();
                           																var dataItem = this.dataItem($(e.currentTarget).closest("tr"));
                           																var wnd = $("#contra").data("kendoWindow");              
                           																var ncontra = Math.random().toString(36).slice(-8);
                           																dataItem.clave = ncontra;
                           																
                           															    $.ajax({
                           															        url: "/trazabilidad/autoridadServlet?action=autUsuUpC",
                           															        //send current record ID to the server
                           															        data: { id_usr: dataItem.id_usr, contra: ncontra },
                           															        success: function (data) {
                           															            //update the current dataItem with the received data from the server
                           															            //example data: {"OrderID":4,"OrderDate":"\/Date(1343941200000)\/","OrderDescription":"NewDescription","EmployeeId":4}
                           															           if(data.success)
                               															        {
                                   																wnd.content(detailsTemplate(dataItem));
                                   								                                wnd.center().open();
                               															        }
                           															        }
                           															    })
                           																
                               														}
                           															</script>
                           															</kendo:grid-column-commandItem-click>
                           															</kendo:grid-column-commandItem>
                           														<kendo:grid-column-commandItem click="cEstado" name="Cambiar Estado" >
                           														<kendo:grid-column-commandItem-click>
                           														<script type="text/javascript">
//                            												
                           															function cEstado(e)
																								{
																					var detailsTemplate = kendo.template($("#desactiva").html());
																					   e.preventDefault();
																					   var grid = $("#grid").data("kendoGrid");
																					   var dataItem = this.dataItem($(e.currentTarget).closest("tr"));
																					   var wnd = $("#estado").data("kendoWindow");
																					 $.ajax({ 
																					 url: "/trazabilidad/autoridadServlet?action=autUsDes",
																					    //send current record ID to the server
																					    data: { id_usr: dataItem.id_usr, est: dataItem.estado},
																					success: function (data) {
																							if(data.success)
																							{
																								if(data.estado==0){
																					               dataItem.estadoA = 'Inactivo';}
																								else{
																					               dataItem.estadoA = 'Activo';
																					               }
																					            for (var property in data) {
																					                dataItem.set(property, data[property]);
																					            }
																					            
																								wnd.content(detailsTemplate(dataItem));
																								wnd.center().open();   
																								grid.refresh();                
																							}		
																					}	
		
																								})
																								}	
                           														</script>
                           														</kendo:grid-column-commandItem-click>
                           														</kendo:grid-column-commandItem>
                           										</kendo:grid-column-command>
                           									</kendo:grid-column>
                           							</kendo:grid-columns>
                           					</kendo:dataSource>
                           			</kendo:grid>
                           			
  <script type="text/x-kendo-template" id="template">
    <div id="details-container">
        <h2>#= usr_nombre # <span> Su nueva clave de acceso es:</span></h2>
        <em>#= clave #</em>
        <dl>

        </dl>
    </div>
    </script>

    <kendo:window name="contra" modal="true" title="Nueva clave del usuario" draggable="true" visible="false" />
<style type="text/css">
     #details-container
     {
         padding: 10px;
     }

     #details-container h2
     {
         margin: 0;
     }

     #details-container em
     {
         color: #8c8c8c;
     }

     #details-container dt
     {
         margin:0;
         display: inline;
     }
 </style>
 
 <script type="text/x-kendo-template" id="desactiva">
    <div id="details-container">
		<h1> Cambio Estado</h1>
        <h2><span>El nuevo estado del Usuario </span> #= usr_nombre # <span> es:</span> </h2>

        <em></em>

        <dl>
			#= estadoA #
        </dl>
    </div>
    </script>

    <kendo:window name="estado" modal="true" title="Estado del Usuario Actualizado" draggable="true" visible="false" />
<style type="text/css">
     #details-container
     {
         padding: 10px;
     }

     #details-container h2
     {
         margin: 0;
     }

     #details-container em
     {
         color: #8c8c8c;
     }

     #details-container dt
     {
         margin:0;
         display: inline;
     }
 </style>

                        </div>
                    </div>

                    <!-- table success end -->
                </div>
                <!-- main content area end -->
                <!-- footer area start-->
                
               <demo:footer/>

    </body>

</html>
