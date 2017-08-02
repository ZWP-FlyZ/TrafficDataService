package app.cacheworker;

import app.model.CityInfoData;
import app.model.OceanPassData;
import app.model.raw.OceanPassRawData;

public class OceanPassRunnable extends DataRunnable {

	private DataPackage dp;
	private DataService ds;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub

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
