package co.gov.ideam.sdstrp.util;

import java.lang.reflect.Type;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import co.gov.ideam.sdstrp.model.GestionUbicacion;

public class GesUbicaAdapter implements JsonSerializer<GestionUbicacion> {

	@Override
	public JsonElement serialize(GestionUbicacion src, Type typeOfSrc, JsonSerializationContext context) {
		// TODO Auto-generated method stub
		JsonObject jsOb = new JsonObject();
		jsOb.addProperty("id_gestion_ubi", src.getId_gestion_ubi());
		jsOb.addProperty("nombre_gestion_ubi", src.getNombre_gestion_ubi());
		return jsOb;
	}

}
