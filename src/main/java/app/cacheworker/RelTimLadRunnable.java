package app.cacheworker;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import app.model.CityInfoData;
import app.model.RelTimLadTraData;
import app.model.raw.RelTimLadTraRawData;
import app.util.GraftTraData;
import app.util.MyException;

public class RelTimLadRunnable extends DataRunnable {

	
	private final static String TN_RT_ROADGOODS_RAW = "rowroadgoodsrel";
	private final static String TN_RT_ROADPASS_RAW = "rowroadpassrel";
	private final static String TN_RT_BUSTRAN_RAW = "rowbustranrel";
	private final static String TN_RT_TAXI_RAW = "rowtaxitranrel";
	
	private final static String TN_RT_ROADGOODS = "roadgoodsrel";
	private final static String TN_RT_ROADPASS = "roadpassrel";
	private final static String TN_RT_BUSTRAN = "bustranrel";
	private final static String TN_RT_TAXI = "taxitranrel";
	
	
	private DataPackage dp;
	private DataService ds;
	
	private final static  Logger logger = LoggerFactory.getLogger("DataRunnable");
	
	
	
	@Override
	public void run() {
		
		@SuppressWarnings("unchecked")
		List<RelTimLadTraRawData> dls = (ArrayList<RelTimLadTraRawData>)dp.getData();
		
		boolean haveNull ;
		for(RelTimLadTraRawData rawData:dls){
			try {
				haveNull = false;
				//haveNull = JudgecanSave.JudgeNull(rawData.getClass(), rawData, false);
				String[] tns = getTName(rawData.getINDUSTRY());
				if (!haveNull) {
					RelTimLadTraData newData = new RelTimLadTraData();
					GraftTraData.cvtRelTimLadTraData(rawData, newData);
					this.cvtRelTimLadTraData(rawData, newData);
					ds.relTimLadMapper.add(tns[1],newData);
				}

				ds.rowRelTimLadMapper.add(tns[0],rawData);
			} catch (Exception e) {
				// TODO: handle exception
				String es = "exec data err: " +new Gson().toJson(rawData);
				logger.error(es,e);
			}	
		}
		
	}
	
	
	private  void cvtRelTimLadTraData(RelTimLadTraRawData rawData,RelTimLadTraData newData){

		newData.setLongitude(rawData.getLONGITUDE());
		newData.setLatitude(rawData.getLATITUDE());
		
		newData.setFuelCo(rawData.getTOTAL_FUEL_COAL());
		
		CityInfoData cityInfoData = ds.citiesMap.getCityInfo(rawData.getAREA_NAME());
		newData.setPlace1(cityInfoData.getCity());
		newData.setPlace2(cityInfoData.getCounty());
	}
	
	
	private String[] getTName(String tranType){
		if(tranType==null) throw new MyException("未知运输类型");
		String[] tmp = new String[2];
		
		switch (tranType) {
		case "t1":  
					tmp[0] = TN_RT_ROADGOODS_RAW;
					tmp[1] = TN_RT_ROADGOODS;
					break;
		case "t2": 					
					tmp[0] = TN_RT_ROADPASS_RAW;
					tmp[1] = TN_RT_ROADPASS;
					break;
		case "t3":					
					tmp[0] = TN_RT_BUSTRAN_RAW;
					tmp[1] = TN_RT_BUSTRAN;
					break;
		case "t4": 
					tmp[0] = TN_RT_TAXI_RAW;
					tmp[1] = TN_RT_TAXI;
					break;
		default:throw new MyException("未知运输类型");
		}
		return tmp;
	}
	
	@Override
	public void setDataPackage(DataPackage dp) {
		this.dp = dp;
	}

	@Override
	public void setDataService(DataService ds) {
		this.ds = ds;
	}

}
