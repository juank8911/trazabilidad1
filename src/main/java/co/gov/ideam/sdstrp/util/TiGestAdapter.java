package co.gov.ideam.sdstrp.util;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import co.gov.ideam.sdstrp.model.TipoGestion;

public class TiGestAdapter implements JsonSerializer<TipoGestion> {

	@Override
	public JsonElement serialize(TipoGestion src, Type typeOfSrc, JsonSerializationContext context) {
		// TODO Auto-generated method stub
		JsonObject jsOb = new JsonObject();
		jsOb.addProperty("id_tip_gestion", src.getId_tip_gestion());
		jsOb.addProperty("nombre_gestion", src.getNombre_gestion());
		return jsOb;
	}

}
