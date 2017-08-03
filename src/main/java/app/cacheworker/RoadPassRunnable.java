package app.cacheworker;



import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import app.model.CityInfoData;
import app.model.RoadPassData;
import app.model.raw.RoadPassRawData;
import app.util.GraftTraData;


public class RoadPassRunnable extends DataRunnable {

	private DataPackage dp;
	private DataService ds;
	

	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<RoadPassRawData>  rprdList = (ArrayList<RoadPassRawData>)dp.getData();
		boolean haveNull;
		for(RoadPassRawData rawData:rprdList){
			haveNull = false;
			try {
				for (Field field : rawData.getClass().getDeclaredFields()) {
					field.setAccessible(true);
					if (field.get(rawData)==null) {
						haveNull = true;
					}
				}	
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			if (!haveNull) {
				RoadPassData newData = new RoadPassData();
				GraftTraData.cvtLadTraData(rawData, newData);
				this.cvtRoadPassData(rawData, newData);
				ds.roadPassMapper.add(newData);
			}		
			ds.rawRoadPassMapper.add(rawData);			
		
		}
	}

	
	
	public void cvtRoadPassData(RoadPassRawData rawData,RoadPassData newData){

		newData.setGoTurn(rawData.getPASSENGER_TURNOVER());  //
		newData.setCarType(rawData.getCAR_TYPE());         //
		newData.setSitCot(rawData.getPASSENGER_CAPACITY());    //
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
