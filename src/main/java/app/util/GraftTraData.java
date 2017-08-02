package app.util;

import java.text.DecimalFormat;

import app.model.CityInfoData;
import app.model.LadTraData;
import app.model.WatTraData;
import app.model.raw.LadTraRawData;
import app.model.raw.WatTraRawData;

public class GraftTraData {
	
	public static void cvtLadTraData(LadTraRawData rawData , LadTraData newData) {
		newData.setCarId(rawData.getPLATE_NUMBER());
		newData.setCompanyId(rawData.getCOMPANY_ID());
		newData.setTranDis(rawData.getRANGE_ABILITY());
		newData.setFuelCsption(rawData.getTOTAL_FUEL());
		/*-------企业规模数字类型转换-------*/
		Integer entS = Integer.valueOf(new DecimalFormat("0").format(rawData.getVEHICLE_SUM()));
		newData.setEntS(entS);
		
		/*-------燃油类型解码-------*/
		String fuelType = ConvertTool.decodeFuleType(rawData.getFUEL_TYPE());
		newData.setFuelType(fuelType);
		
		/*-------运输类型解码----------*/
		String traType = ConvertTool.decodeTraType(rawData.getINDUSTRY());
		newData.setTraType(traType);
		
		/*-------统计期解码----------*/
		String inTime = ConvertTool.decodeDate(rawData.getREPORT_TIME());
		newData.setInTime(inTime);
		
		/*-------地区解码----------*/
		/*String areaId = rawData.getAREA_NAME();
		CityInfoData cityInfoData = ConvertTool.decodeAreaId(areaId);
		newData.setPlace1(cityInfoData.getCity());
		newData.setPlace2(cityInfoData.getCounty());*/
	}
	
	public static void cvtWatTraData(WatTraRawData rawData,WatTraData newData){
		newData.setShipId(rawData.getShipId());
		newData.setCompanyId(rawData.getCompanyId());
		newData.setFuelCsption(rawData.getFuelCsption());
		newData.setTranDis(rawData.getSail());
		newData.setEntS(rawData.getShipNumber());
		
		/*-------统计期解码----------*/
		String inTime = ConvertTool.decodeDate(rawData.getCountDate());
		newData.setInTime(inTime);
		
		/*-------运输类型解码----------*/
		String traType = ConvertTool.decodeTraType(rawData.getTransType());
		newData.setTraType(traType);
		
		/*-------燃油类型解码-------*/
		String fuelType = ConvertTool.decodeFuleType(rawData.getFuelType());
		newData.setFuelType(fuelType);
	}
	

}
