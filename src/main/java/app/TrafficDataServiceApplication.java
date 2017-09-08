package app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import app.websocket.MyWebsocketClient;



@SpringBootApplication
public class TrafficDataServiceApplication implements CommandLineRunner  {
	
	
	
	@Autowired
	MyWebsocketClient client;

	
	public static void main(String[] args) {
		SpringApplication.run(TrafficDataServiceApplication.class, args);
	}
	

	@Override
	public void run(String... arg0) throws Exception {
		// TODO Auto-generated method stub
		//test.addUser();
//		String json =CpTools.readJsonFile("src/test/java/service/app/TestJson.json");
//		client.onMessage(json);
		
	}
}
