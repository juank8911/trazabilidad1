function tResEditor(container, options)
         {
     		$('<input data-text-field="tre_nombre" data-value-field="tre_id" data-bind="value:' + options.field + '"/>')
             .appendTo(container)
             .kendoDropDownList({
                 autoBind: false,
                 dataSource: {                
                     transport: {
                         read: "/trazabilidad/combServlet?action=combTRes"
                     }
                 }
             });

     	}

         function tmaneEditor(container, options)
         {
     		$('<input data-text-field="tma_nombre" data-value-field="tma_id" data-bind="value:' + options.field + '"/>')
             .appendTo(container)
             .kendoDropDownList({
                 autoBind: false,
                 cascadeFrom: "nombre_gestion",
                 dataSource: {                
                     transport: {
                         read: "/trazabilidad/combServlet?action=combTMa"
                     }
                 }
             });

         function gestionEditor(container, options)
         {
     		$('<input data-text-field="nombre_gestion" data-value-field="id_tip_gestion" data-bind="value:' + options.field + '"/>')
             .appendTo(container)
             .kendoDropDownList({
                 autoBind: false,
                 dataSource: {                
                     transport: {
                         read: "/trazabilidad/combServlet?action=combTGestion"
                     }
                 }
             });
     	}

        

         function tMatEditor(container, options)
         {
     		$('<input data-text-field="ema_nombre" data-value-field="ema_id" data-bind="value:' + options.field + '"/>')
             .appendTo(container)
             .kendoDropDownList({
                 autoBind: false,
                 dataSource: {                
                     transport: {
                         read: "/trazabilidad/combServlet?action=combTMat"
                     }
                 }
             });
     	}
         function  gUbicaEditor(container, options)
         {
     		$('<input data-text-field="nombre_gestion_ubi" data-value-field="id_gestion_ubi" data-bind="value:' + options.field + '"/>')
             .appendTo(container)
             .kendoDropDownList({
                 autoBind: false,
                 dataSource: {                
                     transport: {
                         read: "/trazabilidad/combServlet?action=combGUbica"
                     }
                 }
             });
     	}
}  