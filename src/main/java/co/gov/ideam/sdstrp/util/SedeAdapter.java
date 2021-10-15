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

import co.gov.ideam.sdstrp.model.Departamento;
import co.gov.ideam.sdstrp.model.Empresa;
import co.gov.ideam.sdstrp.model.Municipio;
import co.gov.ideam.sdstrp.model.Sede;

public class SedeAdapter implements JsonSerializer<Boolean> {
	
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
	
	public JsonArray formatSedes(List<Sede> sedes)
	{
		JsonArray lJsonSede = new JsonArray();
		
		for (Sede sede : sedes) {
			JsonObject jsonSed = new JsonObject();
			jsonSed.addProperty("sed_id", sede.getSed_id());
			jsonSed.addProperty("sed_autoridad", sede.getSed_autoridad());
			jsonSed.addProperty("sed_departamento", sede.getSed_departamento());
			jsonSed.addProperty("sed_direccion", sede.getSed_direccion());
			jsonSed.addProperty("sed_empresa", sede.getSed_empresa());
			jsonSed.addProperty("sed_generador", sede.getSed_generador());
			jsonSed.addProperty("sed_gestor", sede.getSed_gestor());
			jsonSed.addProperty("sed_latitud", sede.getSed_latitud());
			jsonSed.addProperty("sed_longitud", sede.getSed_longitud());
			jsonSed.addProperty("sed_municipio", sede.getSed_municipio());
			jsonSed.addProperty("sed_nombre", sede.getSed_nombre());
			jsonSed.addProperty("sed_telefono_ext", sede.getSed_telefono_ext());
			jsonSed.addProperty("sed_telefono_n", sede.getSed_telefono_nro());
			jsonSed.addProperty("sed_transportador", sede.getSed_transportador());
			JsonObject jsEmp = new JsonObject();
			Empresa emp = sede.getEmpresaSed();
			jsEmp.addProperty("emp_id", emp.getEmp_id());
			jsEmp.addProperty("emp_nombre_comercial", emp.getEmp_nombre_comercial());
			jsonSed.add("empresa",jsEmp);
			Departamento dep = sede.getDepartamento();
			Municipio mun = sede.getSedMunic();
			JsonObject jSonDepart = new JsonObject();
			JsonObject jSonMun = new JsonObject();
			jSonDepart.addProperty("id_dept", dep.getId_dept());
			jSonDepart.addProperty("dept_nombre", dep.getDept_nombre());
			jsonSed.add("departamento", jSonDepart);
			jSonMun.addProperty("id_munic_dept", mun.getId_munic_dept());
			jSonMun.addProperty("id_munic", mun.getId_munic());
			jSonMun.addProperty("munic_nombre", mun.getMunic_nombre());
			jsonSed.add("municipio", jSonMun);
			lJsonSede.add(jsonSed);
		}
		
		return lJsonSede;
	}

}
