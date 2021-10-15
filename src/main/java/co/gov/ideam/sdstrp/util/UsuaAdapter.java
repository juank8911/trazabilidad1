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

import co.gov.ideam.sdstrp.model.Usuario;

public class UsuaAdapter implements com.google.gson.JsonSerializer<Boolean>
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
	
	public JsonArray formatUsu(List<Usuario> usua)
	{
		JsonArray jsusuArr = new JsonArray();
		for (Usuario usu : usua) {
			JsonObject jsUsu = new JsonObject();
			jsUsu.addProperty("id_usr", usu.getId_usr());
			jsUsu.addProperty("id_usr_aut", usu.getId_usr_aut());
			jsUsu.addProperty("usr_apellido", usu.getUsr_apellido());
			jsUsu.addProperty("usr_cargo", usu.getUsr_cargo());
			jsUsu.addProperty("usr_documento", usu.getUsr_documento());
			jsUsu.addProperty("usr_email", usu.getUsr_email());
			jsUsu.addProperty("usr_id_sede", usu.getUsr_id_sede());
			jsUsu.addProperty("usr_nom_persona", usu.getUsr_nom_persona());
			jsUsu.addProperty("usr_nombre", usu.getUsr_nombre());
			jsUsu.addProperty("usr_telefono", usu.getUsr_telefono());
			jsUsu.addProperty("usr_tip_ident", usu.getUsr_tip_ident());
			jsUsu.addProperty("clave", usu.getUsr_clave());
			jsUsu.addProperty("estado", usu.getUsr_estado());
			int es = usu.getUsr_estado();
			if(es==0)
			{
				jsUsu.addProperty("estadoA", "Incativo");
			}else
			{
				jsUsu.addProperty("estadoA", "Activo");
			}
			jsusuArr.add(jsUsu);
			
		}
		
		return jsusuArr;
	}

}
