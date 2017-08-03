package app.cacheworker;

import java.util.ArrayList;
import java.util.List;


import app.model.CityInfoData;
import app.model.RiverTranData;
import app.model.raw.RiverTranRawData;
import app.util.GraftTraData;
import app.util.JudgecanSave;

public class RiverTranRunnable extends DataRunnable {

	private DataPackage dp;
	private DataService ds;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<RiverTranRawData> rtrdList = (ArrayList<RiverTranRawData>)dp.getData();
		boolean haveNull;
		for (RiverTranRawData rawData : rtrdList) {
			haveNull = false;
			haveNull = JudgecanSave.JudgeNull(rawData.getClass(), rawData, haveNull);
			if(!haveNull){
				RiverTranData newData = new RiverTranData();
				GraftTraData.cvtWatTraData(rawData, newData);
				this.cvtRiverTraData(rawData, newData);
				ds.riverTranMapper.add(newData);
			}
			ds.rawRiverTranMapper.add(rawData);
			
			
		}

	}
	
	public void cvtRiverTraData(RiverTranRawData rawData,RiverTranData newData){
		newData.setGoTurn(rawData.getCargoTurn());
		newData.setTonnage(rawData.getTonnage());
		newData.setShipType(rawData.getShipType());
		
		CityInfoData cityInfoData = ds.cityInfoMapper.getByCityId(rawData.getAreaId());
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
