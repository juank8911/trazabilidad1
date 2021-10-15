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

import co.gov.ideam.sdstrp.model.Residuos;

public class ResiduosAdapter implements com.google.gson.JsonSerializer<Boolean>
{

	@Inject
	private Logger log ;

	@Override
	public JsonElement serialize(Boolean aBoolean, Type type, JsonSerializationContext jsonSerializationContext) {
		// TODO Auto-generated method stub
        if(aBoolean == null) {  
            return new JsonPrimitive("");  
        }  
        return new JsonPrimitive(aBoolean.booleanValue() == true ? 1 : 0); 

	} 
	
	public JsonArray adaResiduos(List<Residuos> residuos)
	{
		JsonArray jsResArr = new JsonArray();
		
		for (Residuos res : residuos) {
			log.info(res.toString());
			JsonObject jsOb = new JsonObject();
			jsOb.addProperty("res_id", res.getRes_id());
			jsOb.addProperty("res_nombre", res.getRes_nombre());
			jsOb.addProperty("res_tipo_residuo", res.getRes_tipo_residuo());
			jsOb.addProperty("res_estado_materia", res.getRes_estado_materia());
			jsOb.addProperty("res_gestion_ubica", res.getRes_gestion_ubica());
			jsOb.addProperty("res_tipo_empaque", res.getRes_tipo_empaque());
			jsOb.addProperty("res_tipo_embalaje", res.getRes_tipo_embalaje());
			jsOb.addProperty("res_tipo_gestion", res.getRes_tipo_gestion());
			jsOb.addProperty("res_peligrosidad", res.getRes_peligrosidad());
			jsOb.addProperty("res_sub_tip_gestion", res.getRes_sub_tip_gestion());
			jsOb.addProperty("res_transportador", res.getRes_transportador());
			jsOb.addProperty("res_sede_transporte", res.getRes_sede_transporte());
			jsOb.addProperty("res_gestor", res.getRes_gestor());
			jsOb.addProperty("res_sede_gestor", res.getRes_sede_gestor());
			jsOb.addProperty("res_sede_generador", res.getRes_sede_generador());
			jsOb.addProperty("tma_nombre", res.gettManejo().getTma_nombre());
			jsOb.addProperty("nombre_gestion_ubi", res.getgUbicacion().getNombre_gestion_ubi());
			jsOb.addProperty("nombre_gestion", res.gettGestion().getNombre_gestion());
			jsOb.addProperty("t_materia", res.getEstadoM().getEma_nombre());
			jsOb.addProperty("tre_nombre", res.gettResiduo().getTre_id()+" : " + res.gettResiduo().getTre_nombre());
			jsResArr.add(jsOb);
		}
		return jsResArr;
		
	}
	
}