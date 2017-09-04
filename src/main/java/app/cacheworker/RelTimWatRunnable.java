package app.cacheworker;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import app.model.CityInfoData;
import app.model.RelTimLadTraData;
import app.model.RelTimWatTraData;
import app.model.raw.RelTimLadTraRawData;
import app.model.raw.RelTimWatTraRawData;
import app.util.GraftTraData;
import app.util.JudgecanSave;

public class RelTimWatRunnable extends DataRunnable {

	private DataPackage dp;
	private DataService ds;
	
	
	private final static String TN_RT_RIVER_RAW = "rowrivertranrel";
	private final static String TN_RT_RIVER = "rivertranrel";
	
	
	private final static  Logger logger = LoggerFactory.getLogger("DataRunnable");
	
	
	@Override
	public void run() {
		
		@SuppressWarnings("unchecked")
		List<RelTimWatTraRawData> dls = (ArrayList<RelTimWatTraRawData>)dp.getData();
		
		boolean haveNull ;
		for(RelTimWatTraRawData rawData:dls){
			try {
				haveNull = false;
				haveNull = JudgecanSave.JudgeNull(rawData.getClass(), rawData, haveNull);
				String[] tns = getTName(rawData.getTransType());
				if (!haveNull) {
					RelTimWatTraData newData = new RelTimWatTraData();
					GraftTraData.cvtRelTimWatTraData(rawData, newData);
					this.cvtRelTimLadTraData(rawData, newData);
					ds.relTimWatMapper.add(tns[1],newData);
				}			
				ds.rowRelTimWatMapper.add(tns[0],rawData);
			} catch (Exception e) {
				// TODO: handle exception
				String es = "exec data err: " +new Gson().toJson(rawData);
				logger.error(es,e);
			}	
		}

	}

	private  void cvtRelTimLadTraData(RelTimWatTraRawData rawData,RelTimWatTraData newData){

		newData.setLongitude(rawData.getLongitude());
		newData.setLatitude(rawData.getLatitude());
		
		newData.setFuelCo(rawData.getFuelCsptionCoal());
		
		CityInfoData cityInfoData = ds.citiesMap.getCityInfo(rawData.getAreaId());
		newData.setPlace1(cityInfoData.getCity());
		newData.setPlace2(cityInfoData.getCounty());
	}
	
	
	private String[] getTName(String tranType){
		if(tranType==null) return null;
		String[] tmp = new String[2];
		
		switch (tranType) {
		case "t5":  
					tmp[0] = TN_RT_RIVER_RAW;
					tmp[1] = TN_RT_RIVER;
					break;
		default:break;
		}
		return tmp;
	}
	
	
	
	
	@Override
	public void setDataPackage(DataPackage dp) {
		this.dp= dp;
	}

	@Override
	public void setDataService(DataService ds) {
		this.ds = ds;
	}

}
