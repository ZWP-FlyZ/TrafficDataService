/*package app.cacheworker;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import app.mapper.RawRoadPassMapper;
import app.model.CityInfoData;
import app.model.LadTraData;
import app.model.RoadPassData;
import app.model.raw.LadTraRawData;
import app.model.raw.RoadPassRawData;
import app.util.ConvertFule;
import app.util.ConvertLadTraData;


public class Test {
	

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<RoadPassRawData> list = new ArrayList<RoadPassRawData>();
		RoadPassRawData one = new RoadPassRawData();
		one.setCAR_ID("12345");
		one.setPLATE_NUMBER("浙KH2801");
		one.setREPORT_TIME("201704");
		one.setINDUSTRY("道路客运");
		one.setAREA_NAME("330102");
		one.setCOMPANY_ID("330000000001501882");
		one.setVEHICLE_SUM(400);
		one.setFUEL_TYPE("f2");
		one.setTOTAL_FUEL(1234.32);
		one.setPASSENGER_TURNOVER(438342);
		one.setCAR_TYPE("c1");
		one.setPASSENGER_CAPACITY(40);
		one.setRANGE_ABILITY(7250.25);
		list.add(one);
		DataPackage dp  = new DataPackage();
		dp.setDataType("道路客运");
		dp.setData(list);
	
		RoadPassData ropaData = new RoadPassData();
		ConvertLadTraData.cvtCommonData(one, ropaData);
		System.out.println(ropaData.getTraType()+" "+ropaData.getPalce1());
		
		
		RoadPassRunnable roPaRunnable=new RoadPassRunnable();
		roPaRunnable.setDataPackage(dp);
		Thread t1 = new Thread(roPaRunnable);
		t1.start();

	}
	
	

}
*/