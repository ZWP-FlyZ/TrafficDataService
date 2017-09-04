package app.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.mapper.CityInfoMapper;
import app.model.CityInfoData;

@Component
public class CitiesMap {

	@Autowired
	private  CityInfoMapper cityInfoMapper;
	
	private final  Map<String,CityInfoData> mMap = new HashMap<>();
	
	public CityInfoData getCityInfo(String city){
		if(city==null) return null;
		CityInfoData t = mMap.get(city);
		if(t!=null) return t;
		else
			t = getCityInfoFromDB(city);
		return t;
	}
	
	private CityInfoData getCityInfoFromDB(String city){
		if(city==null) return null;
		CityInfoData t = cityInfoMapper.getByCityId(city);
		if(t!=null) mMap.put(city, t);
		return t;
	}
	
	
	
	
	
}
