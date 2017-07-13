package app.mybus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MyBus {
	
	
	private  static MyBus bus = null;
	@SuppressWarnings("rawtypes")
	private final  Map<String,List<MyReceiver>> mReceivers = new HashMap<>();
	private Thread mThread = null;
	private final BlockingQueue<Object> msgQueue  = 
								new LinkedBlockingQueue<Object>();
	private MyBus(){
		mThread = new Thread(mRunnable);
		mThread.start();
	}
	
	public static MyBus getMainBus(){
		if(bus==null) 
			bus = new MyBus();
		return bus;
	}
	
	public  <E> void regReceiver(MyReceiver<E> receiver,Class<E> msgType){
		@SuppressWarnings("rawtypes")
		List<MyReceiver> lr = mReceivers.get(msgType.getName());
		if(lr==null)
			lr = new ArrayList<>();
		lr.add(receiver);
		mReceivers.put(msgType.getName(), lr);
	}
	
	

	public  void sendMessage(Object message){	
		msgQueue.offer(message);
	}
	
	public  <E> void unregReceiver(MyReceiver<E> receiver,Class<E> msgType){
		@SuppressWarnings("rawtypes")
		List<MyReceiver> lr = mReceivers.get(msgType.getName());
		if(lr!=null)
			lr.remove(receiver);
	}
	public static abstract class MyReceiver<E>{
		protected abstract void onReceiver(E message);
	}
	
	Runnable mRunnable = new Runnable(){

		@SuppressWarnings("unchecked")
		@Override
		public void run() {
			while(true){
			try {
				Object msg = msgQueue.take();
				@SuppressWarnings("rawtypes")
				List<MyReceiver> lr = mReceivers.get(msg.getClass().getName());
				if(lr==null)   continue;
				for(@SuppressWarnings("rawtypes") MyReceiver r:lr){
					r.onReceiver(msg);
				}
				
			} catch (InterruptedException e) {

				e.printStackTrace();
			}	
				
				
			}// loop all
		}
		
	};
	
	

}
