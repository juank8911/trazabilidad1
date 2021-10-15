package co.gov.ideam.sdstrp.util;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.persistence.Tuple;



import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;


public class RepMesGenAdapter implements com.google.gson.JsonSerializer<Boolean>
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
	
	
	public JsonArray repoMesAdap(List<Tuple> tup)
	{
		JsonObject jsonOb = new JsonObject();
		JsonArray rep = new JsonArray();
		Long val = 0l;
		log.info(tup.size()+"");
		for (Tuple tuple : tup) {
			jsonOb = new JsonObject();
			jsonOb.addProperty("res_id", (int) tuple.get(0));
			jsonOb.addProperty("estado", (String) tuple.get(4));
			log.info(tuple.get(0).toString());
			int mes = Integer.parseInt(String.valueOf(tuple.get(3)));
			if(mes==1)
			{
				jsonOb.addProperty("mEnero", (Long) tuple.get(1));
			}
			else {jsonOb.addProperty("mEnero", (Long )val );}
			if(mes==2)
			{
				jsonOb.addProperty("mFebrero", (Long) tuple.get(1));
			}
			else {jsonOb.addProperty("mFebrero", (Long )val );}
			if(mes==3)
			{
				jsonOb.addProperty("mMarzo", (Long) tuple.get(1));
			}
			else {jsonOb.addProperty("mMarzo", (Long )val );}
			if(mes==4)
			{
				jsonOb.addProperty("mAbril", (Long) tuple.get(1));
			}
			else {jsonOb.addProperty("mAbril", (Long )val );}
			if(mes==5)
			{
				jsonOb.addProperty("mMayo", (Long) tuple.get(1));
			}
			else {jsonOb.addProperty("mMayo", (Long )val );}
			if(mes==6)
			{
				jsonOb.addProperty("mJunio", (Long) tuple.get(1));
			}
			else {jsonOb.addProperty("mJunio", (Long )val );}
			if(mes==7)
			{
				jsonOb.addProperty("mJulio", (Long) tuple.get(1));
			}
			else {jsonOb.addProperty("mJulio", (Long )val );}
			if(mes==8)
			{
				jsonOb.addProperty("mAgosto", (Long) tuple.get(1));
			}
			else {jsonOb.addProperty("mAgosto", (Long )val );}
			if(mes==9)
			{
				jsonOb.addProperty("mSeptiembre", (Long) tuple.get(1));
			}
			else {jsonOb.addProperty("mSeptiembre", (Long )val );}
			if(mes==10)
			{
				jsonOb.addProperty("mOctubre", (Long) tuple.get(1));
			}
			else {jsonOb.addProperty("mOctubre", (Long )val );}
			if(mes==11)
			{
				jsonOb.addProperty("mNoviembre", (Long) tuple.get(1));
			}
			else {jsonOb.addProperty("mNoviembre", (Long )val );}
			if(mes==12)
			{
				jsonOb.addProperty("mDiciembre", (Long) tuple.get(1));
			}
			else {jsonOb.addProperty("mDiciembre", (Long )val );}
			jsonOb.addProperty("tipo_res", (String) tuple.get(2));
			jsonOb.addProperty("mes", (int) tuple.get(3));
			rep.add(jsonOb);
			log.info(String.valueOf(jsonOb));
//			rep.add(jsonOb);
			log.info(String.valueOf(rep));

		}
		
		
		return rep;
		
	}
}
	