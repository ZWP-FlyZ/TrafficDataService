package service.app;

import static org.junit.Assert.*;

import org.junit.Test;

import app.mybus.MyBus;
import app.websocket.yuntu.model.CpData;

public class MyBusTest {

	@Test
	public void test() {
		MyBus.getMainBus().regReceiver(testReciver, "hello");
		MyBus.getMainBus().regReceiver(testReciver2, "hello2");
		MyBus.getMainBus().sendMessage("hello", "hello");
		MyBus.getMainBus().sendMessage("hello2", "hello1");
		MyBus.getMainBus().sendMessage("hello", "hello2");
		MyBus.getMainBus().sendMessage("hello2", "hello3");
	}
	
	MyBus.MyReceiver<String> testReciver = new  MyBus.MyReceiver<String>(){

		@Override
		protected void onReceiver(String message) {
			// TODO Auto-generated method stub
			System.err.println(" test|"+message);
		}
		
	};
	
	MyBus.MyReceiver<String> testReciver2 = new  MyBus.MyReceiver<String>(){

		@Override
		protected void onReceiver(String message) {
			// TODO Auto-generated method stub
			System.err.println("test2|"+message);
		}
		
	};

}
