package app.websocket;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.channels.NotYetConnectedException;

import org.hamcrest.xml.HasXPath;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;

import app.mybus.MyBus;
import app.util.CpTools;
import app.websocket.yuntu.model.Certificate;
import app.websocket.yuntu.model.CpClient;
import app.websocket.yuntu.model.CpData;
import app.websocket.yuntu.model.CpDataInfo;
import app.websocket.yuntu.model.CpInfoDeserializer;
import app.websocket.yuntu.model.CpProtocalInfo;
import app.websocket.yuntu.model.CpWords;


@Component
public class MyWebsocketClient extends WebSocketClient implements InitializingBean{

	private final static String CP_IP = "121.40.30.88";
	private final static int CP_PORT =8080;
	
	private final static String MYBUS_SELF_NAME = "websocket";
	private final static String MYBUS_TANGER_NAME = "main";
	
	private final CpClient client = new CpClient();
	
	private final CpData regData = new CpData();
	private final CpData heartBeat = new CpData();
	
	 private final static Logger logger = LoggerFactory.getLogger(MyWebsocketClient.class); 
	 
	private static Gson gson ;
	
	public MyWebsocketClient() throws URISyntaxException{
		super(new URI("ws://"+CP_IP+":"+CP_PORT));
		MyBus.getMainBus().regReceiver(receiver, MYBUS_SELF_NAME);
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(CpData.class, new CpInfoDeserializer());
		gson = gsonBuilder.create();
	}
	
	@Override
	public void onOpen(ServerHandshake handshakedata) {
		// TODO Auto-generated method stub
		logger.error("onopen");
		regData.setCall_id(CpTools.getUUID());
		send(regData);
	}


	public void send(Object msg)  {
		// TODO Auto-generated method stub
		String json = gson.toJson(msg);
		logger.error("send json = "+json);
		super.send(json);
	}
	
	@Override
	public void onMessage(String message) {
		// TODO Auto-generated method stub
		CpData c = gson.fromJson(message, CpData.class);
		logger.error("send json = "+message);
		if(CpWords.TYPE_DATA_REQ.equals(c.getType()))
			MyBus.getMainBus().sendMessage(MYBUS_TANGER_NAME, c);
		
		
		else if(CpWords.TYPE_HEART_BEAT_RESP.equals(c.getType())){
			
			
			
		}
		
		else if(CpWords.TYPE_REGISTER_RESP.equals(c.getType())){
			CpProtocalInfo info = (CpProtocalInfo) c.getInfo();
			if(info.getResult() == 0x0){
				//do some after reg
			}else{
				logger.error("reg err = "+ Integer.toHexString(info.getResult()));
			}
		}
		
		
	}

	@Override
	public void onClose(int code, String reason, boolean remote) {
		// TODO Auto-generated method stub
		System.err.println("onclose");
		
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
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		String cerJson = 
				CpTools.readJsonFile("src/main/java/app/websocket/yuntu/model/certificate.json");
		
		client.setCertificate(gson.fromJson(cerJson, Certificate.class));
		client.setStatus(CpClient.CLIENT_STATUS_CLOSED);
		
		regData.setMethod(CpWords.METHOD_REGISTER);
		regData.setType(CpWords.TYPE_REGISTER_REQ);
		CpProtocalInfo info = new CpProtocalInfo();
		info.setCertificate(client.getCertificate());
		regData.setInfo(info);
		
		
		heartBeat.setMethod(CpWords.METHOD_INFO);
		heartBeat.setType(CpWords.TYPE_HEART_BEAT_REQ);
		
		this.connect();
		
	}




}
