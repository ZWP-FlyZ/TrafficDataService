package app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.mapper.EvelMapper;
import app.model.EvelData;
import app.model.RequestData;

@Service
public class EvelService {

	@Autowired
	EvelMapper em;
		

	public List<List<EvelData>> getEvelDataPar(RequestData data){
		List<List<EvelData>> ds = new ArrayList<List<EvelData>>();
		List<String> times = getTimes(data.getTimeRange());
		String[] tNames = getTNameByTranType(data.getTranType());
		String cId = checkComIdNull(data.getCompanyId());
		
		if(tNames[2]==null)
			ds.add(em.getEvelFromRawWat(tNames[0], times.get(0), times.get(1),cId));
		else
			ds.add(em.getEvelFromRawLad(tNames[0], times.get(0), times.get(1),cId));
		ds.add(part(em.getEvelFromAll(tNames[1], times.get(2), times.get(3), cId)));
		
		return ds;
	}
	
	
	private  final static List<String> getTimes(String timeRange){
		if(timeRange==null) throw new NullPointerException("timeRange is null");
		List<String> ls = new ArrayList<String>();
		String[] ts = timeRange.split(":");
//		ts[0] = ts[0].substring(0, 7);
//		ts[1] = ts[1].substring(0, 7);
		ls.add(ts[0].replace("-", ""));
		ls.add(ts[1].replace("-", ""));
		ls.add(ts[0]+"-01");
		ls.add(ts[1]+"-01");
		return ls;
	}
	
	
	public final static String TT_LAND_PASS = "道路客运";
	public final static String TT_LAND_GOODS = "道路货运";
	public final static String TT_LAND_BUS = "公交客运";
	public final static String TT_LAND_TAXI = "出租客运";
	public final static String TT_WATER_RIVER = "内河运输";
	public final static String TT_WATER_PASS = "海洋客运";
	public final static String TT_WATER_GOODS = "海洋货运";
	public final static String TT_WATER_PORT = "港口生产";
	

	private  final static String[] getTNameByTranType(String tranType){
		
		String[] names = new String[3];
		if(TT_LAND_PASS.equals(tranType)){
			names[0] = "rawroadpass"; 
			names[1] = "roadpass";
			names[2] = "r";
		}
			
		else if(TT_LAND_GOODS.equals(tranType)){
			names[0] = "rawroadgoods"; 
			names[1] = "roadgoods";
			names[2] = "r";
		}
		else if(TT_LAND_BUS.equals(tranType)){
			names[0] = "rawbustran"; 
			names[1] = "bustran";
			names[2] = "r";
		}
		else if(TT_LAND_TAXI.equals(tranType)){
			names[0] = "rawtaxitran"; 
			names[1] = "taxitran";
			names[2] = "r";
		}
		
		else if(TT_WATER_RIVER.equals(tranType)){
			names[0] = "rawrivertran"; 
			names[1] = "rivertran";		
		}
		else if(TT_WATER_PASS.equals(tranType)){
			names[0] = "rawoceanpass"; 
			names[1] = "oceanpass";		
		}
		else if(TT_WATER_GOODS.equals(tranType)){
			names[0] = "rawoceangoods"; 
			names[1] = "oceangoods";		
		}
		else if(TT_WATER_PORT.equals(tranType)){
			names[0] = "rawportproduct"; 
			names[1] = "portproduct";		
		}
		
		return names;
	}
	
	
	private final static String checkComIdNull(String cId){
		return cId==null||cId.equals("")?"%":cId;
	}
	
	public final static List<EvelData> part(List<EvelData> ori){
		if(ori==null) return null;
		String tmp;
		for(EvelData d:ori){
			tmp = d.getYm();
			tmp = tmp.substring(0, 7);
			d.setYm(tmp.replace("-", ""));
		}
		
		return ori;
	}
	
	
	
}
