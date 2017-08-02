package service.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import app.TrafficDataServiceApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=TrafficDataServiceApplication.class)
public class TrafficDataServiceApplicationTests {
	
	/*@Autowired
	private RawRoadPassMapper rawRoadPassMapper;*/
	
	/*@Autowired
	DataService dataService;*/
	
	/*
	@Test
	public void testInsert() throws Exception{
		List<RoadPassRawData> list= new ArrayList<RoadPassRawData>();
		RoadPassRawData one = new RoadPassRawData();
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
		//dataService.rawRoadPassMapper.addRawRoadPass(one);
		list.add(one);
		DataPackage dp  = new DataPackage();
		dp.setDataType("t1");
		dp.setData(list);
		
		RoadPassRunnable roPaRunnable=new RoadPassRunnable();
		roPaRunnable.setDataPackage(dp);
		roPaRunnable.setDataService(dataService);
		Thread t1 = new Thread(roPaRunnable);
		t1.start();
		
	}*/
	
	@Test
	public void testGetCityInfo(){
	}
	
	/*@Test
	public void contextLoads() {
	}*/

}
