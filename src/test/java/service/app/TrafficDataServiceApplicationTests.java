package service.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import app.TrafficDataServiceApplication;
import app.service.RangeService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=TrafficDataServiceApplication.class)
public class TrafficDataServiceApplicationTests {

	
	
//	@Autowired
//	MyWebsocketClient wc;
//	
//	@Autowired
//	EncDecPcg encDecPcg;
	
	
//	@Autowired
	RangeService rs;
	
	
	@Test
	public void testGetCityInfo(){
		
//		rs.preLoad();
//		
//		System.err.println(rs.getRanges("道路客运"));
//		
//		RoadGoodsRawData d = new RoadGoodsRawData();
//		d.setFUEL_TYPE("f1");
//		d.setTRANSPORT_TURNOVER(21.101);
//		d.setRANGE_ABILITY(1.101);
//		d.setTOTAL_FUEL(31.101);
//		
//		System.err.println(rs.checkRoadGoods(d));
	}
	


}
