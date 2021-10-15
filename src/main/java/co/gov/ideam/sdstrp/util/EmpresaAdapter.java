package co.gov.ideam.sdstrp.util;

import java.lang.reflect.Type;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import co.gov.ideam.sdstrp.model.Empresa;
import co.gov.ideam.sdstrp.model.Sede;

public class EmpresaAdapter implements JsonSerializer<Boolean> {

    @Inject
    private Logger log;
    
    @Inject
    private SedeAdapter sedAd;

	@Override
	public JsonElement serialize(Boolean aBoolean, Type type, JsonSerializationContext jsonSerializationContext) {
		// TODO Auto-generated method stub
        if(aBoolean == null) {  
            return new JsonPrimitive("");  
        }  
        return new JsonPrimitive(aBoolean.booleanValue() == true ? 1 : 0); 

	}
	
	public JsonArray formatEmpres(List<Empresa> empresas)
	{
		int gestor=0;
		int generador =0;
		int Transporta=0;
		JsonArray lJsonEmp = new JsonArray();
		for (Empresa emp : empresas) {
			JsonObject jsonEmp = new JsonObject();
			jsonEmp.addProperty("emp_id", emp.getEmp_id());
			jsonEmp.addProperty("emp_nombre_comercial", emp.getEmp_nombre_comercial());
			jsonEmp.addProperty("emp_razon_social", emp.getEmp_razon_social());
			jsonEmp.addProperty("emp_rep_email", emp.getEmp_rep_email());
			jsonEmp.addProperty("emp_rep_nombre", emp.getEmp_rep_nombre());
			jsonEmp.addProperty("emp_cc_represen", emp.getEmp_cc_represen());
//			jsonEmp.addProperty("emp_direccion", emp.getEmp_);
			jsonEmp.addProperty("emp_ext", emp.getEmp_ext());
			jsonEmp.addProperty("ciiu4", emp.getEmp_ciiu4());
			jsonEmp.addProperty("emp_id_aut", emp.getEmp_id_aut());
			jsonEmp.addProperty("emp_id_ubica", emp.getEmp_id_ubica());
			jsonEmp.addProperty("emp_numero_documento", emp.getEmp_numero_documento());
			jsonEmp.addProperty("emp_telefono", emp.getEmp_telefono());
			jsonEmp.addProperty("emp_tip_docu", emp.getEmp_tip_docu());
			JsonObject depa = new JsonObject();
				depa.addProperty("id_dept", emp.getEmpDept().getId_dept());
				depa.addProperty("dept_nombre", emp.getEmpDept().getDept_nombre());
			jsonEmp.add("departamento", depa);
			JsonObject muni = new JsonObject();
			muni.addProperty("munic_nombre", emp.getEmp_mun().getMunic_nombre());
			muni.addProperty("id_munic", emp.getEmp_mun().getId_munic());
			muni.addProperty("id_dept", emp.getEmp_mun().getId_munic_dept());
			jsonEmp.add("municipio", muni);
			JsonObject cii = new JsonObject();
			cii.addProperty("ciipr_id", emp.getEmpCii().getCii_id());
			cii.addProperty("ciipr_nombre", emp.getEmpCii().getCii_nombre());
			jsonEmp.add("ciiupr", cii);
			List<Sede> sed =  emp.getEmpresaSed();
			JsonArray jsSed = sedAd.formatSedes(sed);
			jsonEmp.add("sedes", jsSed);
			jsonEmp.addProperty("conSed", sed.size());
			gestor=0;
			generador =0;
			Transporta=0;
			for (Sede se : sed) {
				if(se.getSed_generador().equalsIgnoreCase("S"))
				{
					generador++;
				}
				if(se.getSed_gestor().equalsIgnoreCase("S"))
				{
					gestor++;
				}
				if(se.getSed_transportador().equalsIgnoreCase("S"))
				{
					Transporta++;
				}
			}
			jsonEmp.addProperty("conGes", gestor);
			jsonEmp.addProperty("conGen", generador);
			jsonEmp.addProperty("conTrn", Transporta);
			lJsonEmp.add(jsonEmp);
			
		}
		return lJsonEmp;
	}

}
