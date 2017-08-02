package app;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import app.cacheworker.DataPackage;
import app.cacheworker.DataService;
import app.cacheworker.RiverTranRunnable;
import app.cacheworker.RoadPassRunnable;
import app.model.raw.RiverTranRawData;
import app.model.raw.RoadPassRawData;


@SpringBootApplication
public class TrafficDataServiceApplication implements CommandLineRunner  {
	
	private final DataService ds;
	
	public TrafficDataServiceApplication(DataService ds){
		this.ds=ds;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(TrafficDataServiceApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		// TODO Auto-generated method stub
		//test.addUser();
		
		
		/*List<RoadPassRawData> list= new ArrayList<RoadPassRawData>();
		RoadPassRawData one = new RoadPassRawData();
		one.setPLATE_NUMBER("浙KH2801");
		one.setREPORT_TIME("201704");
		one.setINDUSTRY("t1");
		one.setAREA_NAME("330102");
		one.setCOMPANY_ID("330000000001501882");
		one.setVEHICLE_SUM(400.0);
		one.setFUEL_TYPE("f2");
		one.setTOTAL_FUEL(1234.32);
		one.setPASSENGER_TURNOVER(342.2);
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
		roPaRunnable.setDataService(ds);
		Thread t1 = new Thread(roPaRunnable);
		t1.start();*/
		
		List<RiverTranRawData> list = new ArrayList<RiverTranRawData>();
		RiverTranRawData one = new RiverTranRawData();
		one.setShipId("s13055807");
		one.setCountDate("201707");
		one.setAreaId("330903");
		one.setTransType("t5");
		one.setCompanyId("33090312345");
		one.setShipNumber(20);
		one.setFuelType("f3");
		one.setFuelCsption(342.3);
		one.setCargoTurn(3422.3);
		one.setTonnage(50.0);
		one.setShipType("s1");
		one.setSail(400.6);
		list.add(one);
		DataPackage dp  = new DataPackage();
		dp.setDataType("t5");
		dp.setData(list);
		
		RiverTranRunnable riverTranRunnable = new RiverTranRunnable();
		riverTranRunnable.setDataPackage(dp);
		riverTranRunnable.setDataService(ds);
		Thread t1 = new Thread(riverTranRunnable);
		t1.start();
	}
}
