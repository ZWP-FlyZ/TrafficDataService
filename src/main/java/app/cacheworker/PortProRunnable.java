package app.cacheworker;

import java.lang.reflect.Field;
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
		@SuppressWarnings("unchecked")
		List<PortProRawData> pprdList = (ArrayList<PortProRawData>)dp.getData();
		boolean haveNull;
		for (PortProRawData rawData : pprdList) {
			haveNull = false;
			String compType = rawData.getTransType();
			if (compType.equals("t8")) {
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
					PortProData newData = new PortProData();
					this.cvtPortProData(rawData, newData);
					ds.portProMapper.add(newData);
				}
				ds.rawPortProMapper.add(rawData);
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
		newData.setThroughput(rawData.getThroughput());
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
