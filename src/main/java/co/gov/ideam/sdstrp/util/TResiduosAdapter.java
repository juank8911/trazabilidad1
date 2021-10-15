package co.gov.ideam.sdstrp.util;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import co.gov.ideam.sdstrp.model.TipoResiduos;

public class TResiduosAdapter implements JsonSerializer<TipoResiduos> {

  
	@Override
	public JsonElement serialize(TipoResiduos tres, Type arg1, JsonSerializationContext arg2) {
		// TODO Auto-generated method stub
		JsonObject jsOb = new JsonObject();
		jsOb.addProperty("tre_id", tres.getTre_id());
		jsOb.addProperty("tre_nombre", tres.getTre_nombre());
		jsOb.addProperty("tre_padre", tres.getTre_padre());
		return jsOb;
	}


}