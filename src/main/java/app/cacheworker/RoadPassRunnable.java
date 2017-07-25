package app.cacheworker;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import app.model.CityInfoData;
import app.model.LadTraData;
import app.model.RoadPassData;
import app.model.raw.RoadPassRawData;
import app.util.ConvertFule;
import app.util.ConvertLadTraData;
import app.mapper.CityInfoMapper;
import app.mapper.RawRoadPassMapper;
import app.mapper.RoadPassMapper;


public class RoadPassRunnable extends DataRunnable {

	private DataPackage dp;
	private DataService ds;
	
	@Autowired
	private RawRoadPassMapper rawRoadPassMapper;
	
	/*@Autowired 
	private CityInfoMapper cityInfoMapper;*/
	
	@Autowired
	private RoadPassMapper roadPassMapper;

	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		List<RoadPassRawData>  rprdList = (ArrayList<RoadPassRawData>)dp.getData();
		for(RoadPassRawData rprd:rprdList){
			
			rawRoadPassMapper.addRawRoadPass(rprd);
			RoadPassData ropaData = new RoadPassData();
		//	convertData(rprd, ropaData);
			ConvertLadTraData.cvtCommonData(rprd, ropaData);
			convertRoadPassData(rprd, ropaData);
		//	System.out.println(ropaData.getInTime());
			roadPassMapper.addRoadPass(ropaData);	
		}

	}

	@Override
	public void setDataPackage(DataPackage dp) {
		// TODO Auto-generated method stub
		this.dp = dp;
	}
	
	
	public void convertRoadPassData(RoadPassRawData rawData,RoadPassData newData){
		newData.setCarId(rawData.getCAR_ID());
	//	newData.setTraType(rawData.getINDUSTRY());
	//	newData.setCompanyId(rawData.getCOMPANY_ID());
		newData.setGoTurn(rawData.getPASSENGER_TURNOVER());  //
		newData.setCarType(rawData.getCAR_TYPE());         //
		newData.setSitCot(rawData.getPASSENGER_CAPACITY());    //
	//	newData.setTranDis(rawData.getRANGE_ABILITY());
		
		/*-------燃油转换-------
		String fuelType = rawData.getFUEL_TYPE();
		if(fuelType.equals("f10")||fuelType.equals("f11")||fuelType.equals("f12"))
			fuelType="f13";
		double fuelCsption = rawData.getTOTAL_FUEL();
		fuelCsption = ConvertFule.cvtFuleCspt(fuelType, fuelCsption);
		newData.setFuelType(fuelType);
		newData.setFuelCsption(fuelCsption);
		
		-------企业规模数字类型转换-------
		newData.setEntS((new Double(rawData.getVEHICLE_SUM())).intValue());
		
		-------地区代码转换-------
		String areaId = rawData.getAREA_NAME();
		CityInfoData cityInfoData = cityInfoMapper.getByCityId(areaId);
		newData.setPalce1(cityInfoData.getCity());
		newData.setPalce2(cityInfoData.getCounty());
	
		-------日期转换-------
		String REPORT_TIME = rawData.getREPORT_TIME();
		String inTime = REPORT_TIME.substring(0, 4)+"-"+REPORT_TIME.substring(4)+"-01";
		newData.setInTime(inTime);*/
		
		
		
		
		
	}

	@Override
	public void setDataService(DataService ds) {
		// TODO Auto-generated method stub
		this.ds = ds;
	}
	
	

}
