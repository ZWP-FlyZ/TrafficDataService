package app;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import app.cacheworker.BusTranRunnable;
import app.cacheworker.DataPackage;
import app.cacheworker.DataService;
import app.cacheworker.OceanGoodsRunnable;
import app.cacheworker.OceanPassRunnable;
import app.cacheworker.PortProRunnable;
import app.cacheworker.RiverTranRunnable;
import app.cacheworker.RoadGoodsRunnable;
import app.cacheworker.RoadPassRunnable;
import app.cacheworker.TaxiTranRunnable;
import app.model.raw.BusTranRawData;
import app.model.raw.OceanGoodsRawData;
import app.model.raw.OceanPassRawData;
import app.model.raw.PortProRawData;
import app.model.raw.RiverTranRawData;
import app.model.raw.RoadGoodsRawData;
import app.model.raw.RoadPassRawData;
import app.model.raw.TaxiTranRawData;


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
		
		List<PortProRawData> list = new ArrayList<PortProRawData>();
		PortProRawData one = new PortProRawData();
		one.setCompanyId("33090312345");
		one.setCountDate("201707");
		one.setAreaId("330903");
		one.setTransType("t8");
		one.setGasoline(423.2);
		one.setThroughput(123.2);
		one.setProTask(321.2);
		
		list.add(one);
		DataPackage dp  = new DataPackage();
		dp.setDataType("t7");
		dp.setData(list);
		
		PortProRunnable riverTranRunnable = new PortProRunnable();
		riverTranRunnable.setDataPackage(dp);
		riverTranRunnable.setDataService(ds);
		Thread t1 = new Thread(riverTranRunnable);
		t1.start();
		
		/*List<OceanPassRawData> list = new ArrayList<OceanPassRawData>();
		OceanPassRawData one = new OceanPassRawData();
		one.setShipId("s13055807");
		one.setCountDate("201707");
		//one.setAreaId("330903");
		one.setTransType("t7");
		one.setCompanyId("33090312345");
		one.setShipNumber(20);
		one.setFuelType("f3");
		one.setFuelCsption(342.3);
		one.setPsgergoTurn(123.2);
		one.setCountPsger(30);
		one.setSail(400.6);
		list.add(one);
		DataPackage dp  = new DataPackage();
		dp.setDataType("t7");
		dp.setData(list);
		
		OceanPassRunnable riverTranRunnable = new OceanPassRunnable();
		riverTranRunnable.setDataPackage(dp);
		riverTranRunnable.setDataService(ds);
		Thread t1 = new Thread(riverTranRunnable);
		t1.start();*/
		
		
		/*List<OceanGoodsRawData> list = new ArrayList<OceanGoodsRawData>();
		OceanGoodsRawData one = new OceanGoodsRawData();
		one.setShipId("s13055807");
		one.setCountDate("201707");
		one.setAreaId("330903");
		one.setTransType("t6");
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
		dp.setDataType("t6");
		dp.setData(list);
		
		OceanGoodsRunnable riverTranRunnable = new OceanGoodsRunnable();
		riverTranRunnable.setDataPackage(dp);
		riverTranRunnable.setDataService(ds);
		Thread t1 = new Thread(riverTranRunnable);
		t1.start();*/
		
	/*	List<RiverTranRawData> list = new ArrayList<RiverTranRawData>();
		RiverTranRawData one = new RiverTranRawData();
		one.setShipId("s13055807");
		one.setCountDate("201707");
		one.setAreaId("330903");
		one.setTransType("t5");
		//one.setCompanyId("33090312345");
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
		t1.start();*/
	}
}
