package service.app;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.fasterxml.jackson.databind.Module.SetupContext;


import app.TrafficDataServiceApplication;
import app.cacheworker.DataPackage;
import app.cacheworker.RoadPassRunnable;
import app.mapper.CityInfoMapper;
import app.mapper.RawRoadPassMapper;
import app.model.CityInfoData;
import app.model.raw.RoadPassRawData;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=TrafficDataServiceApplication.class)


public class TrafficDataServiceApplicationTests {
	
/*	@Autowired
	private RawRoadPassMapper rawRoadPassMapper;*/
	
	
	
	
	@Test
	public void testInsert() throws Exception{
		List<RoadPassRawData> list= new ArrayList<RoadPassRawData>();
		RoadPassRawData one = new RoadPassRawData();
		one.setCAR_ID("12345");
		one.setPLATE_NUMBER("浙KH2801");
		one.setREPORT_TIME("201704");
		one.setINDUSTRY("t1");
		one.setAREA_NAME("330102");
		one.setCOMPANY_ID("330000000001501882");
		one.setVEHICLE_SUM(400.0);
		one.setFUEL_TYPE("f2");
		one.setTOTAL_FUEL(1234.32);
		one.setPASSENGER_TURNOVER(438342);
		one.setCAR_TYPE("c1");
		one.setPASSENGER_CAPACITY(40);
		one.setRANGE_ABILITY(7250.25);
		//rawRoadPassMapper.addRawRoadPass(one);
		list.add(one);
		DataPackage dp  = new DataPackage();
		dp.setDataType("t1");
		dp.setData(list);
		
		RoadPassRunnable roPaRunnable=new RoadPassRunnable();
		roPaRunnable.setDataPackage(dp);
		Thread t1 = new Thread(roPaRunnable);
		t1.start();
		
	}
	
	/*@Test
	public void testGetCityInfo(){
		CityInfoData cityInfoData = cityInfoMapper.getByCityId("330102");
		System.out.println(cityInfoData.getCity()+" "+cityInfoData.getCounty());
	}*/
	
	/*@Test
	public void contextLoads() {
	}*/

}
