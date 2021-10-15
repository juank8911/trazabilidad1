package co.gov.ideam.sdstrp.util;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import co.gov.ideam.sdstrp.model.TipoManejo;

public class TiManAdapter implements JsonSerializer<TipoManejo> {

	@Override
	public JsonElement serialize(TipoManejo src, Type typeOfSrc, JsonSerializationContext context) {
		// TODO Auto-generated method stub
		JsonObject jsOb = new JsonObject();
		jsOb.addProperty("tma_id", src.getTma_id());
		jsOb.addProperty("tma_nombre", src.getTma_nombre());
		return jsOb;
		
	}

}
