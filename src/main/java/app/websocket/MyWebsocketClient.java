package app.websocket;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import app.mybus.MyBus;
import app.util.CpTools;
import app.util.FileStorageUtil;
import app.websocket.yuntu.model.Certificate;
import app.websocket.yuntu.model.CpClient;
import app.websocket.yuntu.model.CpData;
import app.websocket.yuntu.model.CpDataInfo;
import app.websocket.yuntu.model.CpInfoDeserializer;
import app.websocket.yuntu.model.CpProtocalInfo;
import app.websocket.yuntu.model.CpResultData;
import app.websocket.yuntu.model.CpWords;


@Component
public class MyWebsocketClient extends WebSocketClient implements InitializingBean{

	private final static String CP_IP = "121.40.30.88";
	private final static int CP_PORT =8080;
	private final static int HEART_BEAT_DELAY = 60;
	private final static int CLIENT_MISSED_COT = 2;
	
	
	private final static String MYBUS_SELF_NAME = "websocket";
	private final static String MYBUS_TANGER_NAME = "main";
	
	@Autowired
	FileStorageUtil fs;
	
	
	private final CpClient client = new CpClient();
	private final CpData regData = new CpData();
	private final CpData heartBeat = new CpData();
	
	private final RunData rd = new RunData();
	ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(1);
	
	private final static Logger logger = LoggerFactory.getLogger(MyWebsocketClient.class);
	 
	 
	private static Gson gson ;
	
	public MyWebsocketClient() throws URISyntaxException{
		super(new URI("ws://"+CP_IP+":"+CP_PORT));

	}
	
	@Override
	public void onOpen(ServerHandshake handshakedata) {
		// TODO Auto-generated method stub
		logger.info("websocket open");
		regData.setCall_id(CpTools.getUUID());
		send(regData);
	}


	public void send(Object msg)  {
		// TODO Auto-generated method stub
		String json = gson.toJson(msg);
		if(!json.contains(CpWords.TYPE_HEART_BEAT_REQ))
			logger.info("send json = "+json);
		//System.err.println(json);
		super.send(json);
	}
	
	@Override
	public void onMessage(String message) {
		// TODO Auto-generated method stub
		CpData c =null;
		if(!message.contains(CpWords.TYPE_HEART_BEAT_REQ))
			logger.info("get json = "+message);
		try {
			 c = gson.fromJson(message, CpData.class);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("json decode ERR! jsonS = "+ message ,e);
			c = new Gson().fromJson(message, CpData.class);
			String tmp = c.getFrom();
			c.setFrom(c.getTo());
			c.setTo(tmp);
			c.setType(CpWords.TYPE_DATA_RESP);
			CpDataInfo di = new CpDataInfo() ;
			CpResultData rd = new CpResultData();
			rd.setCode(CpWords.RESP_ECODE_WRONG_FORMAL);
			rd.setMessage(CpWords.RESP_MSG_WRONG_FORMAL);
			di.setResult(rd);
			di.setData(null);
			c.setInfo(di);
			send(c);
			return ;
		}
		
		
		
		if(CpWords.TYPE_DATA_REQ.equals(c.getType()))
			MyBus.getMainBus().sendMessage(MYBUS_TANGER_NAME, c);
		
		
		else if(CpWords.TYPE_HEART_BEAT_RESP.equals(c.getType())){
			//System.err.println("get heart beat");
			CpProtocalInfo info = (CpProtocalInfo) c.getInfo();
			if(info.getResult()==0x0){
				rd.isLive = true;
				rd.cot = 0;
			}else
				logger.warn("useless heart beat");

		}
		
		else if(CpWords.TYPE_REGISTER_RESP.equals(c.getType())){
			CpProtocalInfo info = (CpProtocalInfo) c.getInfo();
			if(info.getResult() == 0x0){
				logger.info("session_id = "+info.getSession_id());
				client.setSession_id(info.getSession_id());
				client.setStatus(CpClient.CLIENT_STATUS_REGED);
				scheduledThreadPool.scheduleAtFixedRate(runable, 10, 
							HEART_BEAT_DELAY,TimeUnit.SECONDS);
				fs.setMessageTo("sid", info.getSession_id());
				
			}else{
				logger.error("reg err = "+ Integer.toHexString(info.getResult()));
				
			}
		}
		
		else if(CpWords.TYPE_RESUME_RESP.equals(c.getType())){
			CpProtocalInfo info = (CpProtocalInfo) c.getInfo();
			if(info.getResult() == 0x0){
				logger.info("session_id = "+client.getSession_id());
				client.setStatus(CpClient.CLIENT_STATUS_REGED);
				scheduledThreadPool.scheduleAtFixedRate(runable, 10, 
							HEART_BEAT_DELAY,TimeUnit.SECONDS);
			}else{
				logger.error("reg resume err = 0x"+ Integer.toHexString(info.getResult()));
			}
		}
		
		
	}

	@Override
	public void onClose(int code, String reason, boolean remote) {
		// TODO Auto-generated method stub
		logger.info("websocket onclose");
		
	}

	@Override
	public void onError(Exception ex) {
		// TODO Auto-generated method stub
		logger.info("websocket onError :" +ex.toString());
	}
	
	
	MyBus.MyReceiver<CpData> receiver = new MyBus.MyReceiver<CpData>() {
		@Override
		protected void onReceiver(CpData message) {
			// TODO Auto-generated method stub
			send(message);
		}};
	
	
	class RunData{
		boolean isLive = true;
		int cot = 0;
	}
		
		
	Runnable runable = new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			if(CpClient.CLIENT_STATUS_REGED.equals(client.getStatus())){
				heartBeat.setFrom(client.getSession_id());
				heartBeat.setCall_id(CpTools.getUUID());
				send(heartBeat);
				
			}
			
			if(!rd.isLive){
				if(rd.cot>=CLIENT_MISSED_COT){
					client.setStatus(CpClient.CLIENT_STATUS_MISSED);
				}else
					rd.cot += 1;
				logger.error("client missing ..."+rd.cot);
			}
			
			if(CpClient.CLIENT_STATUS_MISSED.equals(client.getStatus())){
				logger.error("client miss");
				scheduledThreadPool.shutdown();
			}
			
			rd.isLive= false;

		}
	};
	
	

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		
		MyBus.getMainBus().regReceiver(receiver, MYBUS_SELF_NAME);
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(CpData.class, new CpInfoDeserializer());
		gson = gsonBuilder.create();
		
		String cerJson = 
				CpTools.readJsonFile("/app/websocket/yuntu/model/certificate.json");
		
		client.setCertificate(gson.fromJson(cerJson, Certificate.class));
		client.setStatus(CpClient.CLIENT_STATUS_CLOSED);
		CpProtocalInfo info = new CpProtocalInfo();
		info.setCertificate(client.getCertificate());
		regData.setMethod(CpWords.METHOD_REGISTER);
		
		String sid = fs.getMessageFrom("sid", null);
		logger.info("get sid " + sid);
		if(sid!=null){
			client.setSession_id(sid);
			regData.setType(CpWords.TYPE_RESUME_REQ);
			info.setSession_id(sid);
		}else
			regData.setType(CpWords.TYPE_REGISTER_REQ);
		regData.setInfo(info);
		heartBeat.setMethod(CpWords.METHOD_INFO);
		heartBeat.setType(CpWords.TYPE_HEART_BEAT_REQ);
		
		this.connect();
		
	}




}
