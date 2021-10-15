 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="kendo" uri="http://www.kendoui.com/jsp/tags"%>
<%@taglib prefix="demo" tagdir="/WEB-INF/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<demo:header/>

<body>

	<%@include file="../sidebarNavbar.jsp"%>
	<!-- main content area start -->
	<div class="main-content">

		<!-- page title area start -->
		<div>
			<div class="row align-items-center">
				<div class="col-sm-6">
					<div class="breadcrumbs-area">
						<h4 class="page-title pull-left">Listado de Empresas</h4>
						<ul class="breadcrumbs pull-left">
							<li><span>Registrados en el sistema de informacion</span></li>
						</ul>
					</div>
				</div>
				<!-- FIN DE MENU  -->
			</div>
			<button type="button" class="btn btn-success mb-3 ti-plus pull-right">
				<a href="control?action=empresaCrear" class="text-white">
					Crear Empresa </a>
			</button>
		</div>
		<!-- page title area end -->
		<!--div class="main-content-inner"-->
		<!-- table success start -->

		<!--div class="card"-->

		<!--div class="card-body"-->

		<br /> <br />

		<div class="single-table">

				<div class="table-responsive">
					<kendo:grid  name="Empresa" pageable="true" sortable="true" editable="true" navigatable="true" groupable="true" columnMenu="true" width="95%">
    		<kendo:grid-editable mode="popup"  >
    			<kendo:grid-editable-template>
					<script>
            			function() {

       					return $("#popupTemplate").html();
       					
                		// Code to handle the template event.
            			}

        </script>
    			</kendo:grid-editable-template>
    			<kendo:grid-edit>
    			<script >
    			function(e){
    				console.log(e);
    				console.log(e.model);
    				var model = e.model;
    				console.log(model.departamento);
    				var depa = model.departamento.dept_nombre;
    				var muni = model.municipio.munic_nombre;
    				var ciiu = model.ciiupr.ciipr_nombre
    				console.log(ciiu);
    				var departamento = $("#departamento").kendoDropDownList({
    					optionLabel: depa,
		                dataTextField: "dept_nombre",
		                dataValueField: "id_dept",
		                dataSource: {
		                    type: "json",
		                    serverFiltering: true,
		                    transport: {
		                        read: "/trazabilidad/combServlet?action=combDepa"
		                    }
		                }
		            }).data("kendoDropDownList");

		            var ciiupr = $("#ciiupr").kendoDropDownList({
		            	optionLabel: ciiu,
		                autoBind: true,
		                dataTextField: "cii_nombre",
		                dataValueField: "cii_id",
		                dataSource: {
		                    serverFiltering: true,
		                    transport: {
		                        read: "/trazabilidad/combServlet?action=combCiiu"
		                    }
		                }
		            }).data("kendoDropDownList");

		           

		            var municipio = $("#municipio").kendoDropDownList({
		            	optionLabel: muni,
		                autoBind: false,
		                cascadeFrom: "departamento",
		                dataTextField: "munic_nombre",
		                dataValueField: "id_munic",
		                dataSource: {
		                    serverFiltering: true,
		                    transport: {
		                        read: "/trazabilidad/combServlet?action=combMuni"
		                    }
		                }
		            }).data("kendoDropDownList");
        			}
    			</script>
    			</kendo:grid-edit>
    		</kendo:grid-editable>
    		        <kendo:grid-toolbar>
            			<kendo:grid-toolbarItem name="create"/>
        		</kendo:grid-toolbar>
    		<kendo:dataSource pageSize="5"  >
    		<kendo:dataSource-transport>
    		<kendo:dataSource-transport-read url="/trazabilidad/autoridadServlet?action=autEmpresa"/>
    		<kendo:dataSource-transport-update url="/trazabilidad/autoridadServlet?action=autEmpresaUpt" />
    		</kendo:dataSource-transport>
    		<kendo:dataSource-schema>
    			<kendo:dataSource-schema-model id="emp_id">
    			<kendo:dataSource-schema-model-fields >
    					<kendo:dataSource-schema-model-field name="emp_razon_social" type="string">
                        	<kendo:dataSource-schema-model-field-validation required="true" />
                        </kendo:dataSource-schema-model-field>
                        <kendo:dataSource-schema-model-field name="emp_nombre_comercial" type="string">
                        	<kendo:dataSource-schema-model-field-validation required="true" />
                        </kendo:dataSource-schema-model-field>                         
                        <kendo:dataSource-schema-model-field name="emp_numero_documento" type="number" >
                        </kendo:dataSource-schema-model-field>
                        <kendo:dataSource-schema-model-field name="departamento">
                        </kendo:dataSource-schema-model-field>
                         <kendo:dataSource-schema-model-field name="municipio">
                        </kendo:dataSource-schema-model-field>
                        <kendo:dataSource-schema-model-field name="ciiupr" >
                        	<kendo:dataSource-schema-model-field-validation required="true" />
                        </kendo:dataSource-schema-model-field>                        
                        <kendo:dataSource-schema-model-field name="emp_direccion" type="string">
                        	<kendo:dataSource-schema-model-field-validation required="true" />
                        </kendo:dataSource-schema-model-field>
                        <kendo:dataSource-schema-model-field name="emp_telefono" type="number">
                        	<kendo:dataSource-schema-model-field-validation required="true" />
                        </kendo:dataSource-schema-model-field>
                        <kendo:dataSource-schema-model-field name="emp_ext" type="number">
                        	<kendo:dataSource-schema-model-field-validation required="true" />
                        </kendo:dataSource-schema-model-field>
                        <kendo:dataSource-schema-model-field name="emp_cc_represen" type="number">
                        	<kendo:dataSource-schema-model-field-validation required="true" />
                        </kendo:dataSource-schema-model-field>
                        <kendo:dataSource-schema-model-field name="emp_rep_email" type="string">
                        	<kendo:dataSource-schema-model-field-validation required="true" />
                        </kendo:dataSource-schema-model-field>
                        <kendo:dataSource-schema-model-field name="emp_rep_nombre" type="string">
                        	<kendo:dataSource-schema-model-field-validation required="true" />
                        </kendo:dataSource-schema-model-field>
                        <kendo:dataSource-schema-model-field name="conGen" type="number" editable="false">
                        	<kendo:dataSource-schema-model-field-validation required="true" />
                        </kendo:dataSource-schema-model-field>
                        <kendo:dataSource-schema-model-field name="conTrn" type="number" editable="false">
                        	<kendo:dataSource-schema-model-field-validation required="true" />
                        </kendo:dataSource-schema-model-field>
                        <kendo:dataSource-schema-model-field name="conGes" type="number" editable="false">
                        	<kendo:dataSource-schema-model-field-validation required="true" />
                        </kendo:dataSource-schema-model-field>
                        <kendo:dataSource-schema-model-field name="conSed" type="number" editable="false">
                        	<kendo:dataSource-schema-model-field-validation required="true" />
                        </kendo:dataSource-schema-model-field>
    			</kendo:dataSource-schema-model-fields>
    			</kendo:dataSource-schema-model>
    		</kendo:dataSource-schema>
    		</kendo:dataSource>
    		<kendo:grid-columns>
    				<kendo:grid-column title="Razon Social" field="emp_razon_social" width="150px"></kendo:grid-column>
    				<kendo:grid-column title="Nombre Comercial" field="emp_nombre_comercial" hidden="true"></kendo:grid-column>
    				<kendo:grid-column title="Nit" field="emp_numero_documento" width="180px" format="{0:n0}"></kendo:grid-column>
    				<kendo:grid-column title="Direccion" field="emp_direccion" hidden="true"></kendo:grid-column>
    				<kendo:grid-column title="Telefono" field="emp_telefono" hidden="true"></kendo:grid-column>
    				<kendo:grid-column title="Ext" field="emp_ext" hidden="true"></kendo:grid-column>
    				<kendo:grid-column title="CC representante" field="emp_cc_represen" hidden="true"></kendo:grid-column>
    				<kendo:grid-column title="Email Representante" field="emp_rep_email" hidden="true"></kendo:grid-column>
    				<kendo:grid-column title="Nombre Representante" field="emp_rep_nombre" hidden="true"></kendo:grid-column>
    				<kendo:grid-column title="ciuu4"  field="ciiupr" template="#: ciiupr.ciipr_nombre #" hidden="true" ></kendo:grid-column>    				
    				<kendo:grid-column title="Departamento" field="departamento" template="#: departamento.dept_nombre #" hidden="true"/>
    				<kendo:grid-column title="Municipio" field="municipio" template="#: municipio.munic_nombre #" hidden="true" />    				
    				<kendo:grid-column title="Generadores" field="conGen" ></kendo:grid-column>
    				<kendo:grid-column title="Transportadores" field="conTrn" ></kendo:grid-column>
    				<kendo:grid-column title="Gestors" field="conGes" ></kendo:grid-column>
    				<kendo:grid-column title="Establecimientos" field="conSed" editable="false"></kendo:grid-column>	
						<kendo:grid-column title="&nbsp;" width="200px" >
            				<kendo:grid-column-command>            				
            					<kendo:grid-column-commandItem name="edit" />
            				</kendo:grid-column-command>
            </kendo:grid-column>
    		</kendo:grid-columns>
    		</kendo:grid>
        			
 <script type="text/x-kendo-template" id="popupTemplate">
  <div class="k-edit-label" > 
        <label for="razonSocial">Razon Social:</label>
    </div>
    <div class="k-edit-field">
        <input id="razonSocial" name="RazonSocial" data-bind="value:emp_razon_social" required />
        <span class="k-invalid-msg" data-for="#: emp_razon_social #" ></span>
    </div>

  <div class="k-edit-label" > 
        <label for="emp_nombre_comercial">Nombre Comercial:</label>
    </div>
    <div class="k-edit-field">
        <input id="emp_nombre_comercial" name="emp_nombre_comercial" data-bind="value:emp_nombre_comercial" required />
        <span class="k-invalid-msg" data-for="#: emp_nombre_comercial #" ></span>
    </div>

  <div class="k-edit-label" > 
        <label for="emp_numero_documento">NIT:</label>
    </div>
    <div class="k-edit-field">
        <input id="emp_numero_documento" name="emp_numero_documento" data-bind="value:emp_numero_documento" required />
        <span class="k-invalid-msg" data-for="#: emp_numero_documento #" ></span>
    </div>

    <div class="k-edit-label">
        <label for="emp_direccion">Direccion:</label>
    </div>
   <div class="k-edit-field">
        <input id="emp_direccion" name="emp_direccion" data-bind=" value:emp_direccion " required />
        <span class="k-invalid-msg" data-for="emp_direccion"></span>
    </div>

  <div class="k-edit-label" > 
        <label for="emp_telefono">Telefono:</label>
    </div>
    <div class="k-edit-field">
        <input id="emp_telefono" name="emp_telefono" data-bind="value: emp_telefono" required />
        <span class="k-invalid-msg" data-for="#: emp_telefono #" ></span>
    </div>

  <div class="k-edit-label" > 
        <label for="emp_ext">Ext:</label>
    </div>
    <div class="k-edit-field">
        <input id="emp_ext" name="emp_ext" data-bind="value: emp_ext" required />
        <span class="k-invalid-msg" data-for="#: emp_ext #" ></span>
    </div>

    <div class="k-edit-label">
        <label for="emp_cc_represen">CC Representante:</label>
    </div>
   <div class="k-edit-field">
        <input id="emp_cc_represen" name="emp_cc_represen" data-bind=" value: emp_cc_represen " required />
        <span class="k-invalid-msg" data-for="emp_cc_represen"></span>
    </div>

    <div class="k-edit-label">
        <label for="emp_rep_email">Email Representante:</label>
    </div>
   <div class="k-edit-field">
        <input id="emp_rep_email" name="emp_rep_email" data-bind=" value: emp_rep_email " required />
        <span class="k-invalid-msg" data-for="emp_rep_email"></span>
    </div>

    <div class="k-edit-label">
        <label for="emp_rep_nombre">Nombre Representante:</label>
    </div>
   <div class="k-edit-field">
        <input id="emp_rep_nombre" name="emp_rep_nombre" data-bind=" value: emp_rep_nombre " required />
        <span class="k-invalid-msg" data-for="emp_rep_nombre"></span>
    </div>


    <div class="k-edit-label">
        <label for="ciiupr">CIIU4:</label>
    </div>
   <div class="k-edit-field">
        <input id="ciiupr" name="ciiupr" data-bind=" value: ciiupr.ciipr_nombre " required />
        <span class="k-invalid-msg" data-for="ciiupr"></span>
    </div>

    <div class="k-edit-label">
        <label for="departamento">Departamento:</label>
    </div>
   <div class="k-edit-field">
        <input id="departamento" name="departamento" data-bind=" value: departamento.dept_nombre " required />
        <span class="k-invalid-msg" data-for="departamento"></span>
    </div>

    <div class="k-edit-label">
        <label for="municipio">Municipio:</label>
    </div>
    <div class="k-edit-field">
        <input id="municipio" name="municipio" data-bind=" value: municipio.munic_nombre " required />
       <span class="k-invalid-msg" data-for="municipio"></span> </div>
 </script>
		
		</div>
			
		</div>
		<!--/div-->
		<!--/div-->

		<!-- table success end -->
		<!--/div-->
		<!-- main content area end -->
		<!-- footer area start-->
		<demo:footer/>

</body>

</html>
