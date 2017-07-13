package app.main;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.cacheworker.CacheWorker;
import app.model.SerAskMessage;
import app.model.SerRspMessage;
import app.mybus.MyBus;

@Component
public class EncDecPcg implements InitializingBean {
	
	@Autowired
	private CacheWorker lanDataWorker;
	
	@Autowired
	private CacheWorker watDataWorker;
	
	public EncDecPcg(){
		MyBus.getMainBus().regReceiver(serAskReceiver, SerAskMessage.class);

	}
	
	
	private MyBus.MyReceiver<SerAskMessage> serAskReceiver = 
						new MyBus.MyReceiver<SerAskMessage>() {
		@Override
		protected void onReceiver(SerAskMessage message) {
			// TODO Auto-generated method stub
			
			/// do some logic
			
			MyBus.getMainBus().sendMessage(cheAskToRsp(message));
		}
	};
	
	
	private  SerRspMessage cheAskToRsp(SerAskMessage message){
		if(message==null) return null;
		SerRspMessage rsp = new SerRspMessage();
		rsp.setmId("F"+message.getmId());
		rsp.setFrom("Service");
		rsp.setTo(message.getFrom());
		rsp.setmType(message.getmType()+100);
		
		
		return rsp;
	}


	@Override
	public void afterPropertiesSet() throws Exception {
		lanDataWorker.setTypeMap(null);
		watDataWorker.setTypeMap(null);
	}
	
	
}
