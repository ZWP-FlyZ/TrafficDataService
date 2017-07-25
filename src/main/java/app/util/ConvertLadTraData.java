package app.util;

import org.springframework.beans.factory.annotation.Autowired;

import app.mapper.CityInfoMapper;
import app.model.CityInfoData;
import app.model.LadTraData;
import app.model.raw.LadTraRawData;

public class ConvertLadTraData {
	
	
	@Autowired
	private static  CityInfoMapper cityInfoMapper;

	public static void cvtCommonData(LadTraRawData rawData,LadTraData newData){
		newData.setTraType(rawData.getINDUSTRY());
		newData.setCompanyId(rawData.getCOMPANY_ID());
		
		newData.setTranDis(rawData.getRANGE_ABILITY());
		
		/*-------燃油转换-------*/
		String fuelType = rawData.getFUEL_TYPE();
		if(fuelType.equals("f10")||fuelType.equals("f11")||fuelType.equals("f12"))
			fuelType="f13";
		double fuelCsption = rawData.getTOTAL_FUEL();
		fuelCsption = ConvertFule.cvtFuleCspt(fuelType, fuelCsption);
		newData.setFuelType(fuelType);
		newData.setFuelCsption(fuelCsption);
		
		/*-------企业规模数字类型转换-------*/
		newData.setEntS((new Double(rawData.getVEHICLE_SUM())).intValue());
		
		/*-------地区代码转换-------*/
		String areaId = rawData.getAREA_NAME();
		CityInfoData cityInfoData = cityInfoMapper.getByCityId(areaId);
		newData.setPalce1(cityInfoData.getCity());
		newData.setPalce2(cityInfoData.getCounty());
	
		/*-------日期转换-------*/
		String REPORT_TIME = rawData.getREPORT_TIME();
		String inTime = REPORT_TIME.substring(0, 4)+"-"+REPORT_TIME.substring(4)+"-01";
		newData.setInTime(inTime);
	}
}
