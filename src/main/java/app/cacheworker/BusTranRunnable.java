package app.cacheworker;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import app.model.BusTranData;
import app.model.CityInfoData;
import app.model.raw.BusTranRawData;
import app.util.GraftTraData;

public class BusTranRunnable extends DataRunnable {

	private DataPackage dp;
	private DataService ds;
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<BusTranRawData> btrdList = (ArrayList<BusTranRawData>)dp.getData();
		boolean haveNull;
		for (BusTranRawData rawData : btrdList) {
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
				BusTranData newData = new BusTranData();
				GraftTraData.cvtLadTraData(rawData, newData);
				this.cvtBusTranData(rawData, newData);
				ds.busTranMapper.add(newData);
			}
			ds.rawBusTranMapper.add(rawData);
		}

	}

	public void cvtBusTranData(BusTranRawData rawData,BusTranData newData){
		newData.setGoTurn(rawData.getPASSENGER_TURNOVER());
		newData.setCarLength(rawData.getBUS_LENGTH());
		
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
		this.ds =ds;
	}

}
