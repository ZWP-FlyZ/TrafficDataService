package app.cacheworker;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class CacheWorker implements InitializingBean{
	
	private final static int DATA_QUEUE_SIZE = 31;
	private final static int THREAD_SIZE = 10;
	private final  BlockingQueue<DataPackage> dataQueue = 
							new LinkedBlockingQueue<DataPackage>(DATA_QUEUE_SIZE+ 20);
	private final  Map<String,Class<? extends DataRunnable>> taskTypes = new HashMap<>();
	private final ExecutorService  threadPool = Executors.newFixedThreadPool(THREAD_SIZE);
	private  Thread mThread;
	
	
	
	private static Logger logger = LoggerFactory.getLogger(CacheWorker.class);
	@Autowired
	DataService dataService;
	
	
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
		
		int size = dataQueue.size();
		if(size<DATA_QUEUE_SIZE-1){
			dataQueue.put(dp);
			return 0;
		}// next ok
		else if(size==DATA_QUEUE_SIZE-1){
			dataQueue.put(dp);
			return 1;
		}//wait_next
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
					logger.debug(dp.getDataType());
					DataRunnable dr = taskTypes.get(dp.getDataType()).newInstance();
					dr.setDataService(dataService);
					dr.setDataPackage(dp);
					threadPool.execute(dr);
				} catch (Exception e) {
					logger.error("循环添加任务出错",e);
				} 
			}// while = true endless loop
		}
		
	};


	@Override
	public void afterPropertiesSet() throws Exception {
		start();
	}
	
	
	
}
