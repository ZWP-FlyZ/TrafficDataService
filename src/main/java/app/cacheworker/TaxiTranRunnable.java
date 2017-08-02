package app.cacheworker;

import java.util.ArrayList;

import java.util.List;

import app.model.CityInfoData;
import app.model.TaxiTranData;
import app.model.raw.TaxiTranRawData;
import app.util.GraftTraData;

public class TaxiTranRunnable extends DataRunnable {

	private DataPackage dp;
	private DataService ds;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		List<TaxiTranRawData> ttrdList = (ArrayList<TaxiTranRawData>)dp.getData();
		for(TaxiTranRawData rawData:ttrdList){
			TaxiTranData newData = new TaxiTranData();
			GraftTraData.cvtLadTraData(rawData, newData);
			this.cvtTaxiTranData(rawData, newData);
			ds.rawTaxiTranMapper.add(rawData);
			ds.taxiTranMapper.add(newData);
		}
		

	}
	
	
	public void cvtTaxiTranData(TaxiTranRawData rawData,TaxiTranData newData){
		newData.setGoTurn(rawData.getPASSENGER_TURNOVER());
		newData.setDpCot(rawData.getENGINE_DISPLACEMENT());
		
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