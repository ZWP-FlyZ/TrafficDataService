package app.cacheworker;



import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import app.model.CityInfoData;
import app.model.RoadPassData;
import app.model.raw.RoadPassRawData;
import app.util.GraftTraData;
import app.util.JudgecanSave;


public class RoadPassRunnable extends DataRunnable {

	private DataPackage dp;
	private DataService ds;
	

	private final static  Logger logger = LoggerFactory.getLogger("DataRunnable");
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<RoadPassRawData>  rprdList = (ArrayList<RoadPassRawData>)dp.getData();
		boolean haveNull;
		for(RoadPassRawData rawData:rprdList){
			
			try {
				haveNull = false;
				haveNull = JudgecanSave.JudgeNull(rawData.getClass(), rawData, haveNull);
				
				if (!haveNull) {
					RoadPassData newData = new RoadPassData();
					GraftTraData.cvtLadTraData(rawData, newData);
					this.cvtRoadPassData(rawData, newData);
					ds.roadPassMapper.add(newData);
				}		
				ds.rawRoadPassMapper.add(rawData);
			} catch (Exception e) {
				// TODO: handle exception
				String es = "exec data err: " +new Gson().toJson(rawData);
				logger.error(es,e);
			}
			
		}
	}

	
	
	public void cvtRoadPassData(RoadPassRawData rawData,RoadPassData newData){

		newData.setGoTurn(rawData.getPASSENGER_TURNOVER());  //
		newData.setCarType(rawData.getCAR_TYPE());         //
		newData.setSitCot(rawData.getPASSENGER_CAPACITY());    //
		CityInfoData cityInfoData = ds.citiesMap.getCityInfo(rawData.getAREA_NAME());
		newData.setPlace1(cityInfoData.getCity());
		newData.setPlace2(cityInfoData.getCounty());
		
	}

	
	@Override
	public void setDataPackage(DataPackage dp) {
		// TODO Auto-generated method stub
		this.dp = dp;
	}
	
	@Override
	public void setDataService(DataService ds) {
		// TODO Auto-generated method stub
		this.ds = ds;
	}
	

}
