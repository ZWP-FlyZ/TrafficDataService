package app.cacheworker;

import java.util.ArrayList;
import java.util.List;

import app.model.CityInfoData;
import app.model.OceanGoodsData;
import app.model.raw.OceanGoodsRawData;
import app.util.GraftTraData;

public class OceanGoodsRunnable extends DataRunnable{

	private DataPackage dp;
	private DataService ds;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		List<OceanGoodsRawData> ogrdList = (ArrayList<OceanGoodsRawData>)dp.getData();
		for (OceanGoodsRawData rawData : ogrdList) {
			
			OceanGoodsData newData = new OceanGoodsData();
			GraftTraData.cvtWatTraData(rawData, newData);
			this.cvtOceanGoodsData(rawData, newData);
			ds.rawOceanGoodsMapper.add(rawData);
			ds.oceanGoodsMapper.add(newData);
		}
		
	}
	
	public void cvtOceanGoodsData(OceanGoodsRawData rawData,OceanGoodsData newData){
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
