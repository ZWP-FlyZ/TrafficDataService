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
	private final BlockingQueue<Message> msgQueue  = 
								new LinkedBlockingQueue<Message>();
	private MyBus(){
		mThread = new Thread(mRunnable);
		mThread.start();
	}
	
	public static MyBus getMainBus(){
		if(bus==null) 
			bus = new MyBus();
		return bus;
	}
	
	public  <E> void regReceiver(MyReceiver<E> receiver,String name){
		@SuppressWarnings("rawtypes")
		List<MyReceiver> lr = mReceivers.get(name);
		if(lr==null)
			lr = new ArrayList<>();
		lr.add(receiver);
		mReceivers.put(name, lr);
	}
	
	
	
	
	public  <E> void regReceiver(MyReceiver<E> receiver,Class<E> msgType){
		regReceiver(receiver,msgType.getName());
	}
	
	
	public  void sendMessage(String name,Object message){	
		if(message==null) return ;
		msgQueue.offer(new Message(name,message));
	}

	public  void sendMessage(Object message){	
		if(message==null) return ;
		msgQueue.offer(new Message(message.getClass().getName(),message));
	}
	
	public  <E> void unregReceiver(MyReceiver<E> receiver,String name){
		@SuppressWarnings("rawtypes")
		List<MyReceiver> lr = mReceivers.get(name);
		if(lr!=null)
			lr.remove(receiver);
	}
	
	public  <E> void unregReceiver(MyReceiver<E> receiver,Class<E> msgType){
		unregReceiver(receiver,msgType.getName());
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
				Message msg = msgQueue.take();
				@SuppressWarnings("rawtypes")
				List<MyReceiver> lr = mReceivers.get(msg.name);
				if(lr==null)   continue;
				for(@SuppressWarnings("rawtypes") MyReceiver r:lr){
					r.onReceiver(msg.msg);
				}
				
			} catch (InterruptedException e) {

				e.printStackTrace();
			}	
				
				
			}// loop all
		}
		
	};
	
	class Message{
		String name;
		Object msg;
		public Message(String name,Object msg){
			this.name = name;
			this.msg = msg;
		}
	}

}

	