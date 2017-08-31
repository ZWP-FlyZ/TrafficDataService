package app.cacheworker;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import app.model.CityInfoData;
import app.model.RoadGoodsData;
import app.model.raw.RoadGoodsRawData;
import app.util.GraftTraData;
import app.util.JudgecanSave;

public class RoadGoodsRunnable extends DataRunnable {

	private DataPackage dp;
	private DataService ds;
	
	private final static  Logger logger = LoggerFactory.getLogger("DataRunnable");
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<RoadGoodsRawData> rgrdList = (ArrayList<RoadGoodsRawData>)dp.getData();
		boolean haveNull;
		for(RoadGoodsRawData rawData:rgrdList){
			
			try {
				haveNull = false;
				haveNull = JudgecanSave.JudgeNull(rawData.getClass(), rawData, haveNull);
				if (!haveNull) {
					RoadGoodsData newData = new RoadGoodsData();
					GraftTraData.cvtLadTraData(rawData, newData);
					this.cvtRoadGoodsData(rawData, newData);
					ds.roadGoodsMapper.add(newData);
				}			
				ds.rawRoadGoodsMapper.add(rawData);
			} catch (Exception e) {
				// TODO: handle exception
				String es = "exec data err: " +new Gson().toJson(rawData);
				logger.error(es,e);
			}
						
		}

	}
	
	public void cvtRoadGoodsData(RoadGoodsRawData rawData,RoadGoodsData newData){
		newData.setGoTurn(rawData.getTRANSPORT_TURNOVER());
		newData.setCarType(rawData.getCAR_TYPE());
		newData.setTonnage(rawData.getAPPROVED_CARGO());
		
		CityInfoData cityInfoData = ds.cityInfoMapper.getByCityId(rawData.getAREA_NAME());
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
