package service.app;

import static org.junit.Assert.*;

import org.junit.Test;

import app.util.CpTools;
import app.websocket.MyWebsocketClient;

public class PathTest {

	@Test
	public void test() {
		System.out.println(MyWebsocketClient.class.getResource("/app/websocket/yuntu/model/certificate.json"));
		String cerJson = 
				CpTools.readJsonFile("/app/websocket/yuntu/model/certificate.json");
		System.err.println(cerJson);
		
	}

}
