package app.cacheworker;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import app.model.CityInfoData;
import app.model.OceanGoodsData;
import app.model.raw.OceanGoodsRawData;
import app.util.GraftTraData;
import app.util.JudgecanSave;

public class OceanGoodsRunnable extends DataRunnable{

	private DataPackage dp;
	private DataService ds;
	private final static  Logger logger = LoggerFactory.getLogger("DataRunnable");
	@Override
	public void run() {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<OceanGoodsRawData> ogrdList = (ArrayList<OceanGoodsRawData>)dp.getData();
		boolean haveNull;
		for (OceanGoodsRawData rawData : ogrdList) {
			
			
			try {
				haveNull = false;
				haveNull = JudgecanSave.JudgeNull(rawData.getClass(), rawData, haveNull);
				if(!haveNull){
					OceanGoodsData newData = new OceanGoodsData();
					GraftTraData.cvtWatTraData(rawData, newData);
					this.cvtOceanGoodsData(rawData, newData);
					ds.oceanGoodsMapper.add(newData);
				}
				ds.rawOceanGoodsMapper.add(rawData);
			} catch (Exception e) {
				// TODO: handle exception
				String es = "exec data err: " +new Gson().toJson(rawData);
				logger.error(es,e);
			}
		}
		
	}
	
	public void cvtOceanGoodsData(OceanGoodsRawData rawData,OceanGoodsData newData){
		newData.setGoTurn(rawData.getCargoTurn());
		newData.setTonnage(rawData.getTonnage());
		newData.setShipType(rawData.getShipType());
		
		CityInfoData cityInfoData = ds.citiesMap.getCityInfo(rawData.getAreaId());
		
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
