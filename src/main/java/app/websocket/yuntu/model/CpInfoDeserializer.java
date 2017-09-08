package app.websocket.yuntu.model;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import app.model.raw.BusTranRawData;
import app.model.raw.OceanGoodsRawData;
import app.model.raw.OceanPassRawData;
import app.model.raw.PortProRawData;
import app.model.raw.RelTimLadTraRawData;
import app.model.raw.RelTimWatTraRawData;
import app.model.raw.RiverTranRawData;
import app.model.raw.RoadGoodsRawData;
import app.model.raw.RoadPassRawData;
import app.model.raw.TaxiTranRawData;



public class CpInfoDeserializer implements JsonDeserializer<CpData>  {

	@Override
	public CpData deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		// TODO Auto-generated method stub
		JsonElement je = null;
		CpData cpData = new CpData();
		JsonObject jo = json.getAsJsonObject();
		
		if((je=jo.get("method"))!=null)
				cpData.setMethod(je.getAsString());
		if((je=jo.get("type"))!=null)
			cpData.setType(jo.get("type").getAsString());
		if((je=jo.get("from"))!=null)
			cpData.setFrom(jo.get("from").getAsString());
		if((je=jo.get("to"))!=null)
			cpData.setTo(jo.get("to").getAsString());
		if((je=jo.get("call_id"))!=null)
			cpData.setCall_id(jo.get("call_id").getAsString());
		if((je=jo.get("info"))==null) return cpData;
		
		
		JsonObject infoJ = je.getAsJsonObject();		
		if(CpWords.TYPE_DATA_REQ.equals(cpData.getType())){
			CpDataInfo di = new CpDataInfo();
			if((je=infoJ.get("doc"))!=null)
				di.setDoc(je.getAsString());
			if((je=infoJ.get("ver"))!=null)
				di.setVer(je.getAsString());
			if((je=infoJ.get("biz"))!=null)
				di.setBiz(je.getAsString());
			
			if((je=infoJ.get("mutil"))!=null)
				di.setMutil(context.deserialize(je, CpMutilData.class));
			
			if((je=infoJ.get("data"))!=null){
				
				if(CpWords.BIZ_RT_LAND.equals(di.getBiz()))
					di.setData(context.deserialize(je, RelTimLadTraRawData[].class));
				if(CpWords.BIZ_RT_WATER.equals(di.getBiz()))
					di.setData(context.deserialize(je, RelTimWatTraRawData[].class));
				
				
				
				if(CpWords.BIZ_ROAD_GOODS.equals(di.getBiz()))
					di.setData(context.deserialize(je, RoadGoodsRawData[].class));
				if(CpWords.BIZ_ROAD_PASS.equals(di.getBiz()))
					di.setData(context.deserialize(je, RoadPassRawData[].class));
				if(CpWords.BIZ_BUS.equals(di.getBiz()))
					di.setData(context.deserialize(je, BusTranRawData[].class));
				if(CpWords.BIZ_TAXI.equals(di.getBiz()))
					di.setData(context.deserialize(je, TaxiTranRawData[].class));
				
				if(CpWords.BIZ_RIVER.equals(di.getBiz()))
					di.setData(context.deserialize(je, RiverTranRawData[].class));
				if(CpWords.BIZ_OCEAN_GOODS.equals(di.getBiz()))
					di.setData(context.deserialize(je, OceanGoodsRawData[].class));
				if(CpWords.BIZ_OCEAN_PASS.equals(di.getBiz()))
					di.setData(context.deserialize(je, OceanPassRawData[].class));
				if(CpWords.BIZ_PORT_PRODUCE.equals(di.getBiz()))
					di.setData(context.deserialize(je, PortProRawData[].class));
				
			}
			
			cpData.setInfo(di);
		}//end  data
		else {
			CpProtocalInfo ci = new CpProtocalInfo();
			if((je=infoJ.get("result"))!=null)
				ci.setResult( je.getAsInt());
			if((je=infoJ.get("session_id"))!=null)
				ci.setSession_id( je.getAsString());
			cpData.setInfo(ci);
		}
				
//		if(CpWords.METHOD_REGISTER.equals(cpData.getMethod())){
//			CpProtocalInfo ci = new CpProtocalInfo();
//			if((je=infoJ.get("result"))!=null)
//				ci.setResult( je.getAsInt());
//			if((je=infoJ.get("session_id"))!=null)
//				ci.setSession_id( je.getAsString());
//			cpData.setInfo(ci);
//		}//end reg resp
		return cpData;
	}

}
