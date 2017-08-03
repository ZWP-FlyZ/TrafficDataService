package app.websocket;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.channels.NotYetConnectedException;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import app.mybus.MyBus;
import app.websocket.yuntu.model.CpData;

@Component
public class MyWebsocketClient extends WebSocketClient{

	private final static String CP_IP = "121.40.30.88";
	private final static int CP_PORT =8080;
	private final static Gson gson = new Gson();
	private final static String MYBUS_SELF_NAME = "websocket";
	private final static String MYBUS_TANGER_NAME = "main";
	public MyWebsocketClient() throws URISyntaxException{
		super(new URI("ws://"+CP_IP+":"+CP_PORT));
		MyBus.getMainBus().regReceiver(receiver, MYBUS_SELF_NAME);
	}
	
	public MyWebsocketClient(URI serverUri) {
		super(serverUri);
	}

	@Override
	public void onOpen(ServerHandshake handshakedata) {
		// TODO Auto-generated method stub
		System.err.println("onopen");
	}

	@Override
	public void onMessage(String message) {
		// TODO Auto-generated method stub
		System.err.println("get json = "+message);
		CpData c = gson.fromJson(message, CpData.class);
		c = decodeCpData(c);
		MyBus.getMainBus().sendMessage(MYBUS_TANGER_NAME, c);
	}

	@Override
	public void onClose(int code, String reason, boolean remote) {
		// TODO Auto-generated method stub
		System.err.println("onopen");
		
	}

	@Override
	public void onError(Exception ex) {
		// TODO Auto-generated method stub
		System.err.println("onError");
	}
	
	MyBus.MyReceiver<CpData> receiver = new MyBus.MyReceiver<CpData>() {
		@Override
		protected void onReceiver(CpData message) {
			// TODO Auto-generated method stub
			send(gson.toJson(message));
		}};
		
	
		
		
	@Override
	public void send(String text) throws NotYetConnectedException {
		// TODO Auto-generated method stub
		System.err.println("send json = "+text);
		super.send(text);
	}

	private CpData decodeCpData(CpData cpDataOri){
		
		
		
		return cpDataOri;
	}

}
