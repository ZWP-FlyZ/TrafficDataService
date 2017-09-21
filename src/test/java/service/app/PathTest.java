package service.app;

import org.junit.Test;

import app.util.CpTools;
import app.websocket.MyWebsocketClient;

public class PathTest {

	@Test
	public void test() {
		System.out.println(MyWebsocketClient.class.getResource("/certificate.json"));
		String cerJson = 
				CpTools.readJsonFile("/certificate.json");
		System.err.println(cerJson);
		
	}

}
