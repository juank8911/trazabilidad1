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

import co.gov.ideam.sdstrp.model.Ciiu4;
import co.gov.ideam.sdstrp.model.Departamento;
import co.gov.ideam.sdstrp.model.Municipio;

public class ComboAdapter implements JsonSerializer<Boolean> {
	
    @Inject
    private Logger log;

	@Override
	public JsonElement serialize(Boolean aBoolean, Type type, JsonSerializationContext jsonSerializationContext) {
		// TODO Auto-generated method stub
        if(aBoolean == null) {  
            return new JsonPrimitive("");  
        }  
        return new JsonPrimitive(aBoolean.booleanValue() == true ? 1 : 0); 

	}
	
	
	public JsonArray formatDep(List<Departamento> depas)
	{
		JsonArray jsonDept = new JsonArray();
		for (Departamento depa : depas) {
			JsonObject jObDep = new JsonObject();
			jObDep.addProperty("id_dept", depa.getId_dept());
			jObDep.addProperty("dept_nombre", depa.getDept_nombre());
			jsonDept.add(jObDep);
		}
		return jsonDept;
	}
	
	public JsonArray formatCiiu(List<Ciiu4> ciiu)
	{
		JsonArray jsCiiu = new JsonArray();
		for (Ciiu4 cii : ciiu) {
			JsonObject jsCii = new JsonObject();
			jsCii.addProperty("cii_nombre", cii.getCii_nombre());
			jsCii.addProperty("cii_id", cii.getCii_id());
			jsCiiu.add(jsCii);
		}
		
		return jsCiiu;
	}


	public JsonArray formatMuni(List<Municipio> muni) {
		// TODO Auto-generated method stub
		JsonArray jsoArrMuni = new JsonArray();
		for (Municipio mu : muni) {
			JsonObject jsObMun = new JsonObject();
			jsObMun.addProperty("id_munic",mu.getId_munic());
			jsObMun.addProperty("id_dept",mu.getId_munic_dept());
			jsObMun.addProperty("munic_nombre",mu.getMunic_nombre());
			jsoArrMuni.add(jsObMun);
			
		}
		return jsoArrMuni;
	}

}
