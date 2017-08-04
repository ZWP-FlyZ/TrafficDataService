package app.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.cacheworker.BusTranRunnable;
import app.cacheworker.CacheWorker;
import app.cacheworker.DataPackage;
import app.cacheworker.DataRunnable;
import app.cacheworker.OceanGoodsRunnable;
import app.cacheworker.OceanPassRunnable;
import app.cacheworker.PortProRunnable;
import app.cacheworker.RoadGoodsRunnable;
import app.cacheworker.RoadPassRunnable;
import app.cacheworker.TaxiTranRunnable;
import app.model.raw.BusTranRawData;
import app.model.raw.OceanGoodsRawData;
import app.model.raw.OceanPassRawData;
import app.model.raw.PortProRawData;
import app.model.raw.RiverTranRawData;
import app.model.raw.RoadGoodsRawData;
import app.model.raw.RoadPassRawData;
import app.model.raw.TaxiTranRawData;
import app.mybus.MyBus;
import app.websocket.yuntu.model.CpData;
import app.websocket.yuntu.model.CpDataInfo;
import app.websocket.yuntu.model.CpMutilData;
import app.websocket.yuntu.model.CpResultData;
import app.websocket.yuntu.model.CpWords;

@Component
public class EncDecPcg implements InitializingBean {
	
	
	private final static String MYBUS_SELF_NAME = "main";
	private final static String MYBUS_TANGER_NAME = "websocket";
	
	private Map<String,CpMutilData> taskMap = new HashMap<>();
	
	@Autowired
	private CacheWorker lanDataWorker;
	
	@Autowired
	private CacheWorker watDataWorker;
	
	
	@Autowired
	private CacheWorker lanDataWorkerRel;
	
	@Autowired
	private CacheWorker watDataWorkerRel;
	
	
	public EncDecPcg(){
		MyBus.getMainBus().regReceiver(receiver, MYBUS_SELF_NAME);

	}
	
	
	private MyBus.MyReceiver<CpData> receiver = 
						new MyBus.MyReceiver<CpData>() {
		@Override
		protected void onReceiver(CpData message) {
			// TODO Auto-generated method stub
			
			/// do some logic
			
			////////////////
			String tmp = message.getFrom();
			message.setFrom(message.getTo());
			message.setTo(tmp);
			message.setType(CpWords.TYPE_DATA_RESP);
			//////////////////
			

			CpDataInfo di = null ;
			CpResultData rd = new CpResultData();
			try {
				
				di = (CpDataInfo)message.getInfo();
				CpMutilData mutil = di.getMutil();
				Object data = di.getData();
				CpMutilData mutil_before = taskMap.get(mutil.getTaskId());
				if(mutil_before==null&&mutil.getPackageIndex()!=1){
					rd.setCode(CpWords.RESP_ECODE_WRONG_INDEX);
					rd.setMessage(CpWords.RESP_MSG_WRONG_INDEX);
				}
				if(mutil_before!=null &&mutil.getPackageIndex() - mutil_before.getPackageIndex() != 1){
					rd.setCode(CpWords.RESP_ECODE_WRONG_INDEX);
					rd.setMessage(CpWords.RESP_MSG_WRONG_INDEX);
				}else if(mutil.getPackageIndex()<1||
							mutil.getPackageIndex()>mutil.getTotalPackage()){
					rd.setCode(CpWords.RESP_ECODE_WRONG_INDEX);
					rd.setMessage(CpWords.RESP_MSG_WRONG_INDEX);
				}else{
					DataPackage dp = new DataPackage();
					int flag = 0;
					if(CpWords.BIZ_ROAD_GOODS.equals(di.getBiz())){
						dp.setDataType(CpWords.BIZ_ROAD_GOODS);
						dp.setData(arr2list((RoadGoodsRawData[]) data));
					}

					if(CpWords.BIZ_ROAD_PASS.equals(di.getBiz())){
						dp.setDataType(CpWords.BIZ_ROAD_PASS);
						dp.setData(arr2list((RoadPassRawData[])data));
					}
					if(CpWords.BIZ_BUS.equals(di.getBiz())){
						dp.setDataType(CpWords.BIZ_BUS);
						dp.setData(arr2list((BusTranRawData[])data));
					}
					if(CpWords.BIZ_TAXI.equals(di.getBiz())){
						dp.setDataType(CpWords.BIZ_TAXI);
						dp.setData(arr2list((TaxiTranRawData[])data));
					}
					
					if(CpWords.BIZ_RIVER.equals(di.getBiz())){
						dp.setDataType(CpWords.BIZ_RIVER);
						dp.setData(arr2list((RiverTranRawData[])data));
						flag = 1;
					}
					if(CpWords.BIZ_OCEAN_GOODS.equals(di.getBiz())){
						dp.setDataType(CpWords.BIZ_OCEAN_GOODS);
						dp.setData(arr2list((OceanGoodsRawData[])data));
						flag =1;
					}
					if(CpWords.BIZ_OCEAN_PASS.equals(di.getBiz())){
						dp.setDataType(CpWords.BIZ_OCEAN_PASS);
						dp.setData(arr2list((OceanPassRawData[])data));
						flag =1;
					}
					if(CpWords.BIZ_PORT_PRODUCE.equals(di.getBiz())){
						dp.setDataType(CpWords.BIZ_PORT_PRODUCE);
						dp.setData(arr2list((PortProRawData[])data));
						flag =1;
					}
					
					if(mutil.getPackageIndex()==mutil.getTotalPackage())
						taskMap.remove(mutil.getTaskId());
					else{
						taskMap.put(mutil.getTaskId(), mutil);
					} 
					
					if(flag == 0){
						flag = lanDataWorker.putData(dp);
					}else if(flag ==1){
						flag = watDataWorker.putData(dp);
					}
						
					if(flag==0){
						rd.setCode(CpWords.RESP_ECODE_OK);
						rd.setMessage(CpWords.RESP_MSG_OK);
					}else if(flag ==1){
						rd.setCode(CpWords.RESP_ECODE_WAIT_NEXT);
						rd.setMessage(CpWords.RESP_MSG_WAIT_NEXT);
					}else if(flag == -1){
						rd.setCode(CpWords.RESP_ECODE_WAIT_REPEAT);
						rd.setMessage(CpWords.RESP_MSG_WAIT_REPEAT);
					}
						
				}
				
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				rd.setCode(CpWords.RESP_ECODE_WAIT_REPEAT);
				rd.setMessage(CpWords.RESP_MSG_WAIT_REPEAT);
			}
			
			di.setResult(rd);
			di.setData(null);
			
			message.setInfo(di);
			
			MyBus.getMainBus().sendMessage(MYBUS_TANGER_NAME,message);
		}
	};
	
	private <T> List<T> arr2list(T[] array){
		if(array==null) 
				throw new NullPointerException();
		List<T> list = new ArrayList<>();
		for(T t :array)
			list.add(t);
		return list;
	}
	
	
	
	

	@Override
	public void afterPropertiesSet() throws Exception {
		Map<String, Class<? extends DataRunnable>> landMap = new HashMap<>();
		Map<String, Class<? extends DataRunnable>> watMap = new HashMap<>();
		
		landMap.put(CpWords.BIZ_ROAD_GOODS, RoadGoodsRunnable.class);
		landMap.put(CpWords.BIZ_ROAD_PASS, RoadPassRunnable.class);
		landMap.put(CpWords.BIZ_BUS, BusTranRunnable.class);
		landMap.put(CpWords.BIZ_TAXI, TaxiTranRunnable.class);
		
		watMap.put(CpWords.BIZ_RIVER, TaxiTranRunnable.class);
		watMap.put(CpWords.BIZ_OCEAN_GOODS, OceanGoodsRunnable.class);
		watMap.put(CpWords.BIZ_OCEAN_PASS, OceanPassRunnable.class);
		watMap.put(CpWords.BIZ_PORT_PRODUCE, PortProRunnable.class);

		lanDataWorker.setTypeMap(landMap);
		watDataWorker.setTypeMap(watMap);
		
		lanDataWorkerRel.setTypeMap(null);
		watDataWorkerRel.setTypeMap(null);
	}
	
	
}
