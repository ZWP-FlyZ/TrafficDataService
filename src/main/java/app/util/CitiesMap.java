package app.util;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.mapper.CityInfoMapper;
import app.model.CityInfoData;

@Component
public class CitiesMap {

	@Autowired
	private  CityInfoMapper cityInfoMapper;
	
	private ReadWriteLock lock = new ReentrantReadWriteLock();
	
	private final  Map<String,CityInfoData> mMap = new HashMap<>();
	
	public CityInfoData getCityInfo(String city){
		if(city==null)  throw new MyException("解析地区码为空！");
		lock.readLock().lock();
		CityInfoData t = mMap.get(city);
		lock.readLock().unlock();
		if(t!=null) return t;
		else
			t = getCityInfoFromDB(city);
		if(t==null) throw new MyException("未获取地区码["+city+"]信息！");
		return t;
	}
	
	private CityInfoData getCityInfoFromDB(String city){
		if(city==null) return null;
		CityInfoData t = cityInfoMapper.getByCityId(city);
		if(t!=null){
			lock.writeLock().lock();
			mMap.put(city, t);
			lock.writeLock().unlock();
		} 	
		return t;
	}
	
	
	
	
	
}
