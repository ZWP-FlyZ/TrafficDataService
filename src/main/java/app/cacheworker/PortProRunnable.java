package app.cacheworker;

import java.util.ArrayList;
import java.util.List;

import app.model.CityInfoData;
import app.model.PortProData;
import app.model.raw.PortProRawData;
import app.util.ConvertTool;

public class PortProRunnable extends DataRunnable {

	private DataPackage dp;
	private DataService ds;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		List<PortProRawData> pprdList = (ArrayList<PortProRawData>)dp.getData();
		for (PortProRawData rawData : pprdList) {
			String compType = rawData.getTransType();
			if (compType.equals("t8")) {
				PortProData newData = new PortProData();
				this.cvtPortProData(rawData, newData);
				ds.rawPortProMapper.add(rawData);
				ds.portProMapper.add(newData);
			}else {
				ds.rawShipComMapper.add(rawData);
			}
			
			
		}

	}
	
	public void cvtPortProData(PortProRawData rawData,PortProData newData){
		newData.setCompanyId(rawData.getCompanyId());
		newData.setDiesel(rawData.getDiesel());
		newData.setGasoline(rawData.getGasoline());
		newData.setCoal(rawData.getCoal());
		newData.setPower(rawData.getPower());
		newData.setOther(rawData.getOther());
		newData.setEntS(rawData.getThroughput());
		newData.setProTask(rawData.getProTask());

		String inTime = ConvertTool.decodeDate(rawData.getCountDate());
		newData.setInTime(inTime);
		
		String traType = ConvertTool.decodeTraType(rawData.getTransType());
		newData.setTraType(traType);
		
		CityInfoData cityInfoData = ds.cityInfoMapper.getByCityId(rawData.getAreaId());
		newData.setPlace1(cityInfoData.getCity());
		newData.setPlace2(cityInfoData.getCounty());
		
		
	}

	@Override
	public void setDataPackage(DataPackage dp) {
		// TODO Auto-generated method stub
		this.dp =dp;
	}

	@Override
	public void setDataService(DataService ds) {
		// TODO Auto-generated method stub
		this.ds= ds;
	}

}
