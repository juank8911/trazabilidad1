package co.gov.ideam.sdstrp.util;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import co.gov.ideam.sdstrp.model.EstadoMateria;
import co.gov.ideam.sdstrp.model.TipoResiduos;

public class EstMatAdapter implements JsonSerializer<EstadoMateria> {

	@Override
	public JsonElement serialize(EstadoMateria estd, Type typeOfSrc, JsonSerializationContext context) {
		// TODO Auto-generated method stub
		JsonObject jsOb = new JsonObject();
		jsOb.addProperty("ema_id", estd.getEma_id());
		jsOb.addProperty("Ema_nombre", estd.getEma_nombre());
		return jsOb;

	}

}
