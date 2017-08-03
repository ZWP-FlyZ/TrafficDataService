package service.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import app.TrafficDataServiceApplication;
import app.main.EncDecPcg;
import app.util.CpTools;
import app.websocket.MyWebsocketClient;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=TrafficDataServiceApplication.class)
public class TrafficDataServiceApplicationTests {

	
	
	@Autowired
	MyWebsocketClient wc;
	
	@Autowired
	EncDecPcg encDecPcg;
	
	
	@Test
	public void testGetCityInfo(){
		
		String json =CpTools.readJsonFile("src/test/java/service/app/TestJson.json");
		
		wc.onMessage(json);
		
	}
	


}
