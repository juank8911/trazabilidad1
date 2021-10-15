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

import co.gov.ideam.sdstrp.model.DeclaracionResiduo;

public class DeclaResiAdapter implements JsonSerializer<Boolean>
{
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

	
	
	public JsonElement formato(List<DeclaracionResiduo> lDeclas)
	{
		JsonArray lisJsontDec = new JsonArray();
		JsonObject jsonDeRe = new JsonObject();
		for (DeclaracionResiduo src : lDeclas) {
			jsonDeRe = new JsonObject();
			log.info(src.toString());
			jsonDeRe.addProperty("der_id", src.getDer_id());
			log.info(src.getDer_id()+"");
			jsonDeRe.addProperty("der_declaracion", src.getDer_declaracion());
			jsonDeRe.addProperty("der_res_id", src.getDer_res_id());
			jsonDeRe.addProperty("der_gen_nombre", src.getDer_gen_nombre());
			jsonDeRe.addProperty("der_gen_tipo_residuo", src.getDer_gen_tipo_residuo());
			jsonDeRe.addProperty("der_gen_estado_materia", src.getDer_gen_estado_materia());
			jsonDeRe.addProperty("der_gen_tipo_empaque", src.getDer_gen_tipo_empaque());
			jsonDeRe.addProperty("der_gen_numero_empaques", src.getDer_gen_numero_empaques());
			jsonDeRe.addProperty("der_gen_tipo_embalaje", src.getDer_gen_tipo_embalaje());
			jsonDeRe.addProperty("der_gen_numero_embalajes", src.getDer_gen_numero_embalajes());
			jsonDeRe.addProperty("der_gen_tipo_peligrosidad", src.getDer_gen_tipo_peligrosidad());
			jsonDeRe.addProperty("der_gen_tipo_manejo", src.getDer_gen_tipo_manejo());
			jsonDeRe.addProperty("der_gen_peso_residuo", src.getDer_gen_peso_residuo());
			jsonDeRe.addProperty("der_trn_tipo_empaque", src.getDer_trn_tipo_empaque());
			jsonDeRe.addProperty("der_trn_numero_empaques", src.getDer_trn_numero_empaques());
			jsonDeRe.addProperty("der_trn_tipo_embalaje", src.getDer_trn_tipo_embalaje());
			jsonDeRe.addProperty("der_trn_numero_embalajes", src.getDer_trn_numero_embalajes());
			jsonDeRe.addProperty("der_trn_tipo_embalaje2", src.getDer_trn_tipo_embalaje2());
			jsonDeRe.addProperty("der_trn_tipo_peligrosidad2", src.getDer_trn_tipo_peligrosidad2());
			jsonDeRe.addProperty("der_trn_peso_residuo2", src.getDer_trn_peso_residuo2());
			jsonDeRe.addProperty("der_ges_tipo_empaque2", src.getDer_ges_tipo_empaque2());
			jsonDeRe.addProperty("der_ges_numero_empaques2", src.getDer_ges_numero_empaques2());
			jsonDeRe.addProperty("der_ges_tipo_embalaje2", src.getDer_ges_tipo_embalaje2());
			jsonDeRe.addProperty("der_ges_numero_embalajes2", src.getDer_ges_numero_embalajes2());
			jsonDeRe.addProperty("der_ges_tipo_peligrosidad3", src.getDer_ges_tipo_peligrosidad3());
			jsonDeRe.addProperty("der_ges_tipo_manejo2", src.getDer_ges_tipo_manejo2());
			jsonDeRe.addProperty("der_ges_ti_gestion", src.getDec_ges_ti_gestion());
			jsonDeRe.addProperty("der__ges_subti_gestion", src.getDec_ges_subti_gestion());
			jsonDeRe.addProperty("der_ges_peso_residuo2", src.getDer_ges_peso_residuo2());
			log.info(String.valueOf(jsonDeRe));
			lisJsontDec.add(jsonDeRe);
			log.info(lisJsontDec.toString());
			
		}
		
		JsonElement sen = lisJsontDec;
		log.info(sen.toString());
		return sen;
	}
	
	










}
