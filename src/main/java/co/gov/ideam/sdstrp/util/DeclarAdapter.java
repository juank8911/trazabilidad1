package co.gov.ideam.sdstrp.util;

import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import co.gov.ideam.sdstrp.model.Declaracion;
import co.gov.ideam.sdstrp.model.DeclaracionResiduo;
import co.gov.ideam.sdstrp.model.Sede;

public class DeclarAdapter implements JsonSerializer<Boolean>
{
	@Inject
	private Logger log ;
	
	@Inject
	private DeclaResiAdapter deReAd;
	
	@Override
	public JsonElement serialize(Boolean aBoolean, Type type, JsonSerializationContext jsonSerializationContext) {
		// TODO Auto-generated method stub
        if(aBoolean == null) {  
            return new JsonPrimitive("");  
        }  
        return new JsonPrimitive(aBoolean.booleanValue() == true ? 1 : 0); 

	} 
	
	
	public JsonElement crear(List<Declaracion> dec)
	{	
		String estado = null;
		JsonArray declas = new JsonArray();
		StringBuilder tipRes = new StringBuilder();
		
		try {
		for (Declaracion src : dec ) {
			JsonObject jsonDec = new JsonObject();
	

		List<DeclaracionResiduo> dl = src.getDeclaracion_res();
		if(src.getDec_gen_aprobada().equalsIgnoreCase("N"))
		{
			estado = "No Enviada";
		}
		else if(src.getDec_gen_aprobada().equalsIgnoreCase("A") && src.getDec_trn_aprobada().equalsIgnoreCase("N"))
		{
			estado = "Enviada";
		}
		else if(src.getDec_gen_aprobada().equalsIgnoreCase("A") && src.getDec_trn_aprobada().equalsIgnoreCase("A") && src.getDec_ges_aprobada().equalsIgnoreCase("N"))
		{
			estado = "Aprobada";
		}
		else if(src.getDec_trn_aprobada().equalsIgnoreCase("R") || src.getDec_ges_aprobada().equalsIgnoreCase("R"))
		{
			estado = "Rechasada";
		}
		else if(src.getDec_trn_aprobada().equalsIgnoreCase("A") && src.getDec_ges_aprobada().equalsIgnoreCase("A"))
		{
			estado = "Finalizada";
		}
		
		jsonDec.addProperty("dec_id", src.getDec_id());
		log.info(src.getDec_id()+"");
		jsonDec.addProperty("estado", estado);
		jsonDec.addProperty("dec_generador", src.getDec_generador());
		jsonDec.addProperty("dec_gen_fecha_trn",String.valueOf(src.getDec_gen_fecha_trn()));
		jsonDec.addProperty("dec_gen_fecha_gen",String.valueOf(src.getDec_gen_fecha_gen()));
		jsonDec.addProperty("gen_aprobada", src.getDec_gen_aprobada());
		jsonDec.addProperty("dec_transportador", src.getDec_transportador());
		jsonDec.addProperty("dec_trn_vehiculo", src.getDec_trn_vehiculo());
		jsonDec.addProperty("dec_trn_fecha_trn", String.valueOf(src.getDec_trn_fecha_trn()));
		jsonDec.addProperty("dec_trn_fecha_ent", String.valueOf(src.getDec_trn_fecha_ent()));
		jsonDec.addProperty("dec_trn_aprobada", src.getDec_trn_aprobada());
		jsonDec.addProperty("dec_gestor", src.getDec_gestor());
		jsonDec.addProperty("dec_ges_fecha_ent", String.valueOf(src.getDec_ges_fecha_ent()));
		jsonDec.addProperty("dec_ges_fecha_ges", String.valueOf(src.getDec_ges_fecha_ges()));
		jsonDec.addProperty("dec_ges_aprobada", src.getDec_ges_aprobada());
		log.info(String.valueOf(src.getDec_ges_observacion()));
		jsonDec.addProperty("dec_ges_observacion", String.valueOf(src.getDec_ges_observacion())!="null"?src.getDec_ges_observacion():"SIN OBSERACIONES");
		jsonDec.addProperty("dec_prog_id", src.getDec_prog_id());
		if(src.getDec_trn_aprobada().equalsIgnoreCase("R"))
		{
			jsonDec.addProperty("rechasoP", "Transportador");
			jsonDec.addProperty("fecha_rechasoP", String.valueOf(src.getDec_trn_fecha_trn())!="null"?String.valueOf(src.getDec_trn_fecha_trn()):"--");
			
		}
		else if(src.getDec_ges_aprobada().equalsIgnoreCase("R"))
		{
			jsonDec.addProperty("rechasoP", "Gestor");
			jsonDec.addProperty("fecha_rechasoP", String.valueOf(src.getDec_ges_fecha_ges())!="null"?String.valueOf(src.getDec_ges_fecha_ges()):"--");
		}
		else
		{
			jsonDec.addProperty("rechasoP", "--");
			jsonDec.addProperty("fecha_rechasoP", "--");
		}
		
		JsonObject jsonSedGes = new JsonObject();
		Sede sedGes = src.getDecSedGes();
		jsonSedGes.addProperty("sed_id", sedGes.getSed_id());
		jsonSedGes.addProperty("sed_municipio", sedGes.getSed_municipio());
		jsonSedGes.addProperty("sed_latitud", sedGes.getSed_latitud());
		jsonSedGes.addProperty("sed_longitud", sedGes.getSed_longitud());
		jsonSedGes.addProperty("sed_nombre", sedGes.getSed_nombre());
		jsonSedGes.addProperty("sed_direccion", sedGes.getSed_direccion());
		jsonSedGes.addProperty("sed_departamento", sedGes.getSed_departamento());
		jsonSedGes.addProperty("sed__telefono_nro", sedGes.getSed_telefono_nro());
		jsonSedGes.addProperty("sed_telefono_ext", sedGes.getSed_telefono_ext());
		jsonSedGes.addProperty("sed_autoridad", sedGes.getSed_autoridad());
		jsonSedGes.addProperty("sed_generador", sedGes.getSed_generador());
		jsonSedGes.addProperty("sed_transportador", sedGes.getSed_transportador());
		jsonSedGes.addProperty("sed_gestor", sedGes.getSed_gestor());
		jsonSedGes.addProperty("departamento", sedGes.getDepartamento().getDept_nombre());
		jsonSedGes.addProperty("empresa_sed", sedGes.getEmpresaSed().getEmp_nombre_comercial());
		jsonSedGes.addProperty("sedMunic", sedGes.getSedMunic().getMunic_nombre());
		jsonDec.add("sedGes", jsonSedGes);
		
		JsonObject jsonSedGen = new JsonObject();
		Sede sedGen = src.getDecSedGen();
		jsonSedGen.addProperty("sed_id", sedGen.getSed_id());
		jsonSedGen.addProperty("sed_municipio", sedGen.getSed_municipio());
		jsonSedGen.addProperty("sed_latitud", sedGen.getSed_latitud());
		jsonSedGen.addProperty("sed_longitud", sedGen.getSed_longitud());
		jsonSedGen.addProperty("sed_nombre", sedGen.getSed_nombre());
		jsonSedGen.addProperty("sed_direccion", sedGen.getSed_direccion());
		jsonSedGen.addProperty("sed_departamento", sedGen.getSed_departamento());
		jsonSedGen.addProperty("sed__telefono_nro", sedGen.getSed_telefono_nro());
		jsonSedGen.addProperty("sed_telefono_ext", sedGen.getSed_telefono_ext());
		jsonSedGen.addProperty("sed_autoridad", sedGen.getSed_autoridad());
		jsonSedGen.addProperty("sed_generador", sedGen.getSed_generador());
		jsonSedGen.addProperty("sed_transportador", sedGen.getSed_transportador());
		jsonSedGen.addProperty("sed_gestor", sedGen.getSed_gestor());
		jsonSedGen.addProperty("departamento", sedGen.getDepartamento().getDept_nombre());
		jsonSedGen.addProperty("empresa_sed", sedGen.getEmpresaSed().getEmp_nombre_comercial());
		jsonSedGen.addProperty("sedMunic", sedGen.getSedMunic().getMunic_nombre());
		jsonDec.add("sedGen", jsonSedGen);
		JsonObject jsonSedTrn = new JsonObject();
		Sede sedTrn = src.getDecSedTran();
	    jsonSedTrn.addProperty("sed_id",           sedTrn.getSed_id());
	    jsonSedTrn.addProperty("sed_municipio",    sedTrn.getSed_municipio());
	    jsonSedTrn.addProperty("sed_latitud",      sedTrn.getSed_latitud());
	    jsonSedTrn.addProperty("sed_longitud",     sedTrn.getSed_longitud());
	    jsonSedTrn.addProperty("sed_nombre",       sedTrn.getSed_nombre());
	    jsonSedTrn.addProperty("sed_direccion",    sedTrn.getSed_direccion());
		jsonSedTrn.addProperty("sed_departamento", sedTrn.getSed_departamento());
		jsonSedTrn.addProperty("sed__telefono_nro",sedTrn.getSed_telefono_nro());
		jsonSedTrn.addProperty("sed_telefono_ext", sedTrn.getSed_telefono_ext());
		jsonSedTrn.addProperty("sed_autoridad",    sedTrn.getSed_autoridad());
		jsonSedTrn.addProperty("sed_generador",    sedTrn.getSed_generador());
		jsonSedTrn.addProperty("sed_transportador",sedTrn.getSed_transportador());
		jsonSedTrn.addProperty("sed_gestor",       sedTrn.getSed_gestor());
		jsonSedTrn.addProperty("departamento",     sedTrn.getDepartamento().getDept_nombre());
		jsonSedTrn.addProperty("empresa_sed",      sedTrn.getEmpresaSed().getEmp_nombre_comercial());
		jsonSedTrn.addProperty("sedMunic",         sedTrn.getSedMunic().getMunic_nombre());
		jsonDec.add("sedTrn", jsonSedTrn);	
		
		/*
		 * AGREGANDO SEDES AL JSON
		 */
		
		
		
		log.info(src.getDeclaracion_res().toString());
		List<DeclaracionResiduo> declaracionRes = src.getDeclaracion_res();
		JsonElement pr = deReAd.formato(declaracionRes);
		StringBuilder tiRes = new StringBuilder();
		int pesoGen = 0;
		int pesoTrn = 0;
		int pesoGes = 0;
		int i = 0;
		for (Iterator iterator = declaracionRes.iterator(); iterator.hasNext();) {
			DeclaracionResiduo declaRes = (DeclaracionResiduo) iterator.next();
			i++;
			if(declaracionRes.size()==i)
			{
				tiRes.append(declaRes.getDer_gen_tipo_residuo());
			}
			else
			{
				tiRes.append(declaRes.getDer_gen_tipo_residuo() +", ");
			}
			
			
				log.info(declaRes.getDer_gen_peso_residuo()+"");
				pesoGen += declaRes.getDer_gen_peso_residuo();
				pesoTrn += declaRes.getDer_trn_peso_residuo2();
				pesoGes += declaRes.getDer_ges_peso_residuo2();
				log.info(pesoGen+"");
			
		}
		log.info(tipRes.toString());
		jsonDec.addProperty("tRes", tiRes.toString());
		jsonDec.addProperty("pesotrn", pesoTrn);
		jsonDec.addProperty("pesoGen", pesoGen);
		jsonDec.addProperty("pesoGes", pesoGes);
		log.info(pr.toString());
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.registerTypeAdapter(DeclaracionResiduo.class, new DeclaResiAdapter()).create();
		jsonDec.add("declaResiduo", pr);
		log.info(jsonDec.toString());
		declas.add(jsonDec);
		log.info(declas.toString());
	}
		} catch (Exception e) {
			// TODO: handle exception
			log.info("Fallo lista historico de declaraciones: " + e.getMessage());
	        e.fillInStackTrace();
		}
		
		return declas ;
		
		
	}


	public JsonElement crearId(List<Declaracion> decId) {
		// TODO Auto-generated method stub
		JsonArray declas = new JsonArray();
		try {
			for (Declaracion src : decId) {
				JsonObject jsonDec = new JsonObject();
				jsonDec.addProperty("dec_id", src.getDec_id());
				jsonDec.addProperty("dec_generador", src.getDec_generador());
				jsonDec.addProperty("dec_gen_fecha_trn",String.valueOf(src.getDec_gen_fecha_trn())!="null"?String.valueOf(src.getDec_gen_fecha_trn()):"--");
				jsonDec.addProperty("dec_gen_fecha_gen",String.valueOf(src.getDec_gen_fecha_gen())!="null"?String.valueOf(src.getDec_gen_fecha_gen()):"--");
				jsonDec.addProperty("gen_aprobada", src.getDec_gen_aprobada());
				jsonDec.addProperty("dec_transportador", src.getDec_transportador());
				jsonDec.addProperty("dec_trn_vehiculo", src.getDec_trn_vehiculo());
				jsonDec.addProperty("dec_trn_fecha_trn", String.valueOf(src.getDec_trn_fecha_trn())!="null"?String.valueOf(src.getDec_trn_fecha_trn()):"--");
				jsonDec.addProperty("dec_trn_fecha_ent", String.valueOf(src.getDec_trn_fecha_ent())!="null"?String.valueOf(src.getDec_trn_fecha_ent()):"--");
				jsonDec.addProperty("dec_trn_aprobada", src.getDec_trn_aprobada());
				jsonDec.addProperty("dec_gestor", src.getDec_gestor());
				log.info(String.valueOf(src.getDec_ges_fecha_ent()));
				jsonDec.addProperty("dec_ges_fecha_ent", String.valueOf(src.getDec_ges_fecha_ent())!="null"?String.valueOf(src.getDec_ges_fecha_ent()):"--");
				jsonDec.addProperty("dec_ges_fecha_ges", String.valueOf(src.getDec_ges_fecha_ges())!="null"?String.valueOf(src.getDec_ges_fecha_ges()):"--");
				jsonDec.addProperty("dec_ges_aprobada", src.getDec_ges_aprobada());
				log.info(src.getDec_ges_observacion()+"");
				jsonDec.addProperty("dec_ges_observacion", String.valueOf(src.getDec_ges_observacion())!="null"?String.valueOf(src.getDec_ges_observacion()):"Sin Observaciones");
				jsonDec.addProperty("dec_prog_id", src.getDec_prog_id());
				
				if(src.getDec_trn_aprobada().equalsIgnoreCase("R"))
				{
					jsonDec.addProperty("rechasoP", "Transportador");
					jsonDec.addProperty("fecha_rechasoP", String.valueOf(src.getDec_trn_fecha_trn())!="null"?String.valueOf(src.getDec_trn_fecha_trn()):"--");
					
				}
				else if(src.getDec_ges_aprobada().equalsIgnoreCase("R"))
				{
					jsonDec.addProperty("rechasoP", "Gestor");
					jsonDec.addProperty("fecha_rechasoP", String.valueOf(src.getDec_ges_fecha_ges())!="null"?String.valueOf(src.getDec_ges_fecha_ges()):"--");
				}
				else
				{
					jsonDec.addProperty("rechasoP", "--");
					jsonDec.addProperty("fecha_rechasoP", "--");
				}
				declas.add(jsonDec);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}

		return declas;
	}

}
