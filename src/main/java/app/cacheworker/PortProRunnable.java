package app.cacheworker;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import app.model.CityInfoData;
import app.model.PortProData;
import app.model.raw.PortProRawData;
import app.util.ConvertTool;
import app.util.JudgecanSave;

public class PortProRunnable extends DataRunnable {

	private DataPackage dp;
	private DataService ds;
	
	private final static  Logger logger = LoggerFactory.getLogger("DataRunnable");
	@Override
	public void run() {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<PortProRawData> pprdList = (ArrayList<PortProRawData>)dp.getData();
		boolean haveNull;
		for (PortProRawData rawData : pprdList) {
			
			try {
				haveNull = false;
				String compType = rawData.getTransType();
				if (compType.equals("t8")) {
					haveNull = JudgecanSave.JudgePortProNull(rawData.getClass(), rawData, haveNull);
					if (!haveNull) {
						PortProData newData = new PortProData();
						this.cvtPortProData(rawData, newData);
						ds.portProMapper.add(newData);
					}
					ds.rawPortProMapper.add(rawData);
				}else {
					ds.rawShipComMapper.add(rawData);
				}
			} catch (Exception e) {
				// TODO: handle exception
				String es = "exec data err: " +new Gson().toJson(rawData);
				logger.error(es,e);
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
		newData.setEntS(0);

		String inTime = ConvertTool.decodeDate(rawData.getCountDate());
		newData.setInTime(inTime);
		
		String traType = ConvertTool.decodeTraType(rawData.getTransType());
		newData.setTraType(traType);
		
		CityInfoData cityInfoData = ds.citiesMap.getCityInfo(rawData.getAreaId());
		if(cityInfoData==null) throw new NullPointerException("cityInfoData==null");
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
