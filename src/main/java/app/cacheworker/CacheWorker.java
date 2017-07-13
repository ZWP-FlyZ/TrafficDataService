package app.cacheworker;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class CacheWorker implements InitializingBean{
	
	private final static int DATA_QUEUE_SIZE = 21;
	private final static int THREAD_SIZE = 10;
	private final  BlockingQueue<DataPackage> dataQueue = 
							new LinkedBlockingQueue<DataPackage>(DATA_QUEUE_SIZE+ 10);
	private final  Map<String,Class<? extends DataRunnable>> taskTypes = new HashMap<>();
	private final ExecutorService  threadPool = Executors.newFixedThreadPool(THREAD_SIZE);
	private  Thread mThread;
	
	
	public CacheWorker(){
		//start();
	}	
	private void start(){
		if(mThread==null){
			this.mThread = new Thread(mRunnable);
			this.mThread.start();
		}
	}
	
	public int putData(DataPackage dp) throws InterruptedException{
		dataQueue.put(dp);
		int size = dataQueue.size();
		if(size<DATA_QUEUE_SIZE-1)// next ok
			return 0;
		else if(size==DATA_QUEUE_SIZE-1)//wait_next
			return 1;
		else
			return -1;	
	}
	public void setTypeMap(Map<String,Class<? extends DataRunnable>> taskTypes){
		if(taskTypes==null) return ;
		this.taskTypes.clear();
		this.taskTypes.putAll(taskTypes);
	}
	
	Runnable mRunnable = new Runnable(){
		@Override
		public void run() {
			while(true){
				try {
					DataPackage dp = dataQueue.take();
					DataRunnable dr = taskTypes.get(dp.getDataType()).newInstance();
					dr.setDataPackage(dp);
					threadPool.execute(dr);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}// while = true endless loop
		}
		
	};


	@Override
	public void afterPropertiesSet() throws Exception {

		start();
	}
	
	
	
}
