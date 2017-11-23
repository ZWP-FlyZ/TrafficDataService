package service.app;

import org.junit.Before;
import org.junit.Test;

import app.model.raw.RoadGoodsRawData;
import app.model.raw.RoadPassRawData;
import app.model.raw.TaxiTranRawData;
import app.util.RangeChecker;

public class RangeCheckerTest {

	
	RoadGoodsRawData rd1 = new RoadGoodsRawData();
	RoadPassRawData rd2 = new RoadPassRawData();
	TaxiTranRawData rd3 = new TaxiTranRawData();
	
	@Before
	public void init(){
		rd1.setAPPROVED_CARGO(19.0);
		rd1.setTOTAL_FUEL(1000.0);
		rd1.setTRANSPORT_TURNOVER(10000.0);
		
		rd2.setPASSENGER_CAPACITY(35);
		rd2.setTOTAL_FUEL(367.0);
		rd2.setPASSENGER_TURNOVER(10000.0);
		
		rd3.setTOTAL_FUEL(1555.0);
		rd3.setPASSENGER_TURNOVER(10000.0);		
		
	}
	
	@Test
	public void test() {
		
		boolean tg = true;
		
		tg &= RangeChecker.checkRoadGoods(rd1);
		System.err.println(RangeChecker.checkRoadGoods(rd1));
		System.err.println(RangeChecker.checkRoadPass(rd2));
		System.err.println(RangeChecker.checkTaxiTran(rd3));
		
		System.err.println(tg);
	}

}
