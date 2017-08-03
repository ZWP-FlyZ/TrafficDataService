package app.cacheworker;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import app.model.CityInfoData;
import app.model.OceanPassData;
import app.model.raw.OceanPassRawData;
import app.util.GraftTraData;

public class OceanPassRunnable extends DataRunnable {

	private DataPackage dp;
	private DataService ds;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<OceanPassRawData> oprdList = (ArrayList<OceanPassRawData>)dp.getData();
		boolean haveNull;
		for (OceanPassRawData rawData : oprdList) {
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
			if(!haveNull){
				OceanPassData newData = new OceanPassData();
				GraftTraData.cvtWatTraData(rawData, newData);
				this.cvtOceanPassData(rawData, newData);
				ds.oceanPassMapper.add(newData);
			}
			ds.rawOceanPassMapper.add(rawData);
		}

	}
	
	public void cvtOceanPassData(OceanPassRawData rawData,OceanPassData newData){
		newData.setGoTurn(rawData.getPsgergoTurn());
		newData.setSitCot(rawData.getCountPsger());
		newData.setShipType("-");
		
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
