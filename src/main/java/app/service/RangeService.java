package app.service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import app.mapper.RangeMapper;
import app.model.raw.BusTranRawData;
import app.model.raw.OceanGoodsRawData;
import app.model.raw.OceanPassRawData;
import app.model.raw.PortProRawData;
import app.model.raw.RiverTranRawData;
import app.model.raw.RoadGoodsRawData;
import app.model.raw.RoadPassRawData;
import app.model.raw.TaxiTranRawData;

@Service
public class RangeService implements InitializingBean{

	
	EdgeData data = new EdgeData();
	
	private ReadWriteLock rwl = new ReentrantReadWriteLock();
	private final static Logger logger = LoggerFactory.getLogger(RangeService.class);
	
	
	
	@Autowired
	RangeMapper rm;
	
	public void test(){
		System.err.println(rm.getRangesByType("道路货运"));
	}
	
	public void testin(){
		rm.insertRangeByType("t", ""+System.currentTimeMillis());
	}
	
	
	
	
	
	class EdgeData{
		/*
		 * 1.运距
		 * 2.周转量
		 * 3.油耗
		 * 4.气耗
		 * 5。电耗
		 * 6.其他能耗
		 * 
		 * -1。道路客运
		 * -2.道路货运
		 * -3.公交客运
		 * -4.出租客运
		 * -5.内河运输
		 * -6.海洋客运
		 * -7.海洋货运
		 * -8.港口生产
		 * 
		 */
		boolean[][] flags = new boolean[6][8];
		double[][][] ranges = new double[6][8][2];
	}
	
	
	
	
	public  boolean checkRoadGoods(RoadGoodsRawData rd){
		int tIndex = 1;//运输类型
		double[] rec = new double[3];
		int cIndex = 2;
		boolean temp;
		
		rec[0] = rd.getRANGE_ABILITY();
		rec[1] = rd.getTRANSPORT_TURNOVER();
		rec[2] = rd.getTOTAL_FUEL();

		cIndex = getIndexByFu(rd.getFUEL_TYPE());
		
		for(int i=0;i<2;i++){
			rwl.readLock().lock();
			temp = data.flags[i][tIndex];
			rwl.readLock().unlock();
			if(temp&&!inRange(data.ranges[i][tIndex],rec[i]))
				 return false;
		}
		rwl.readLock().lock();
		temp = data.flags[cIndex][tIndex];
		rwl.readLock().unlock();
		if(temp&&!inRange(data.ranges[cIndex][tIndex],rec[2]))
			 return false;
		return true;	

	}
	
	public boolean checkRoadPass(RoadPassRawData rd){
		int tIndex = 0;//运输类型
		double[] rec = new double[3];
		int cIndex = 2;
		boolean temp;
		
		rec[0] = rd.getRANGE_ABILITY();
		rec[1] = rd.getPASSENGER_TURNOVER();
		rec[2] = rd.getTOTAL_FUEL();

		cIndex = getIndexByFu(rd.getFUEL_TYPE());
		
		for(int i=0;i<2;i++){
			rwl.readLock().lock();
			temp = data.flags[i][tIndex];
			rwl.readLock().unlock();
			if(temp&&!inRange(data.ranges[i][tIndex],rec[i]))
				 return false;
		}
		rwl.readLock().lock();
		temp = data.flags[cIndex][tIndex];
		rwl.readLock().unlock();
		if(temp&&!inRange(data.ranges[cIndex][tIndex],rec[2]))
			 return false;
		return true;

	}
	
	public  boolean checkBusTran(BusTranRawData rd){
		int tIndex = 2;//运输类型
		double[] rec = new double[3];
		int cIndex = 2;
		boolean temp;
		
		rec[0] = rd.getRANGE_ABILITY();
		rec[1] = rd.getPASSENGER_TURNOVER();
		rec[2] = rd.getTOTAL_FUEL();

		cIndex = getIndexByFu(rd.getFUEL_TYPE());
		
		for(int i=0;i<2;i++){
			rwl.readLock().lock();
			temp = data.flags[i][tIndex];
			rwl.readLock().unlock();
			if(temp&&!inRange(data.ranges[i][tIndex],rec[i]))
				 return false;
		}
		rwl.readLock().lock();
		temp = data.flags[cIndex][tIndex];
		rwl.readLock().unlock();
		if(temp&&!inRange(data.ranges[cIndex][tIndex],rec[2]))
			 return false;
		return true;

	}
	
	public boolean checkTaxiTran(TaxiTranRawData rd){
		int tIndex = 3;//运输类型
		double[] rec = new double[3];
		int cIndex = 2;
		boolean temp;
		
		rec[0] = rd.getRANGE_ABILITY();
		rec[1] = rd.getPASSENGER_TURNOVER();
		rec[2] = rd.getTOTAL_FUEL();

		cIndex = getIndexByFu(rd.getFUEL_TYPE());
		
		for(int i=0;i<2;i++){
			rwl.readLock().lock();
			temp = data.flags[i][tIndex];
			rwl.readLock().unlock();
			if(temp&&!inRange(data.ranges[i][tIndex],rec[i]))
				 return false;
		}
		rwl.readLock().lock();
		temp = data.flags[cIndex][tIndex];
		rwl.readLock().unlock();
		if(temp&&!inRange(data.ranges[cIndex][tIndex],rec[2]))
			 return false;
		return true;
	}
	
	public boolean checkRiverTran(RiverTranRawData rd){
		int tIndex = 4;//运输类型
		double[] rec = new double[3];
		int cIndex = 2;
		boolean temp;
		
		rec[0] = rd.getSail();
		rec[1] = rd.getCargoTurn();
		rec[2] = rd.getFuelCsption();

		cIndex = getIndexByFu(rd.getFuelType());
		
		for(int i=0;i<2;i++){
			rwl.readLock().lock();
			temp = data.flags[i][tIndex];
			rwl.readLock().unlock();
			if(temp&&!inRange(data.ranges[i][tIndex],rec[i]))
				 return false;
		}
		rwl.readLock().lock();
		temp = data.flags[cIndex][tIndex];
		rwl.readLock().unlock();
		if(temp&&!inRange(data.ranges[cIndex][tIndex],rec[2]))
			 return false;
		return true;
	}
	
	public boolean checkOceanGoods(OceanGoodsRawData rd){
		int tIndex = 6;//运输类型
		double[] rec = new double[3];
		int cIndex = 2;
		boolean temp;
		
		rec[0] = rd.getSail();
		rec[1] = rd.getCargoTurn();
		rec[2] = rd.getFuelCsption();

		cIndex = getIndexByFu(rd.getFuelType());
		
		for(int i=0;i<2;i++){
			rwl.readLock().lock();
			temp = data.flags[i][tIndex];
			rwl.readLock().unlock();
			if(temp&&!inRange(data.ranges[i][tIndex],rec[i]))
				 return false;
		}
		rwl.readLock().lock();
		temp = data.flags[cIndex][tIndex];
		rwl.readLock().unlock();
		if(temp&&!inRange(data.ranges[cIndex][tIndex],rec[2]))
			 return false;
		return true;
	}
	
	
	public boolean checkOceanPass(OceanPassRawData rd){
		int tIndex = 5;//运输类型
		double[] rec = new double[3];
		int cIndex = 2;
		boolean temp;
		
		rec[0] = rd.getSail();
		rec[1] = rd.getPsgergoTurn();
		rec[2] = rd.getFuelCsption();

		cIndex = getIndexByFu(rd.getFuelType());
		
		for(int i=0;i<2;i++){
			rwl.readLock().lock();
			temp = data.flags[i][tIndex];
			rwl.readLock().unlock();
			if(temp&&!inRange(data.ranges[i][tIndex],rec[i]))
				 return false;
		}
		rwl.readLock().lock();
		temp = data.flags[cIndex][tIndex];
		rwl.readLock().unlock();
		if(temp&&!inRange(data.ranges[cIndex][tIndex],rec[2]))
			 return false;
		return true;
	}
	
	public boolean checkPortProduct(PortProRawData rd){
		int tIndex = 7;//运输类型

		boolean temp;
		
		rwl.readLock().lock();
		temp = data.flags[0][tIndex];
		rwl.readLock().unlock();
		if(temp&&!inRange(data.ranges[0][tIndex],rd.getThroughput()))
			 return false;
		
		
		rwl.readLock().lock();
		temp = data.flags[1][tIndex];
		rwl.readLock().unlock();
		if(temp&&!inRange(data.ranges[1][tIndex],rd.getProTask()))
			 return false;
		
		//柴油
		rwl.readLock().lock();
		temp = data.flags[2][tIndex];
		rwl.readLock().unlock();
		if(temp&&!inRange(data.ranges[2][tIndex],rd.getDiesel()))
			 return false;
		//汽油
		rwl.readLock().lock();
		temp = data.flags[2][tIndex];
		rwl.readLock().unlock();
		if(temp&&!inRange(data.ranges[2][tIndex],rd.getGasoline()))
			 return false;
		
		//煤油
		rwl.readLock().lock();
		temp = data.flags[2][tIndex];
		rwl.readLock().unlock();
		if(temp&&!inRange(data.ranges[2][tIndex],rd.getCoal()))
			 return false;
		
		//其他油
		rwl.readLock().lock();
		temp = data.flags[2][tIndex];
		rwl.readLock().unlock();
		if(temp&&!inRange(data.ranges[2][tIndex],rd.getOtherCoal()))
			 return false;
		
		//电能
		rwl.readLock().lock();
		temp = data.flags[4][tIndex];
		rwl.readLock().unlock();
		if(temp&&!inRange(data.ranges[4][tIndex],rd.getPower()))
			 return false;
		
		//其他
		rwl.readLock().lock();
		temp = data.flags[5][tIndex];
		rwl.readLock().unlock();
		if(temp&&!inRange(data.ranges[5][tIndex],rd.getOther()))
			 return false;	
		
		return true;
	}
	
	
	private String NAN = "N";
	public void setRange(String tranType,List<String> ranges){
		int index = getIndexByTranType(tranType);
		String[] t;
		if(index<0) throw new NullPointerException("未知运输类型"+tranType) ;
		if(ranges==null||ranges.size()<0) throw new NullPointerException("范围数据错误"+ranges) ;
		rwl.writeLock().lock();
		try {
			for(int i=0;i<6;i++)
				if(NAN.equals(ranges.get(i)))
					data.flags[i][index] = false;
				else
				{
					t = ranges.get(i).split("_");
					data.ranges[i][index][0] = Double.parseDouble(t[1]);
					data.ranges[i][index][1] = Double.parseDouble(t[2]);
					data.flags[i][index] = true;
				}
			rm.insertRangeByType(tranType, new Gson().toJson(ranges));
			rwl.writeLock().unlock();
		} catch (Exception e) {
			e.printStackTrace();
			rwl.writeLock().unlock();
		}
		
	}
	
	
	private void loadRange(String tranType,List<String> ranges){
		int index = getIndexByTranType(tranType);
		String[] t;
		if(index<0) throw new NullPointerException("未知运输类型"+tranType) ;

		rwl.writeLock().lock();
		try {
			for(int i=0;i<6;i++)
				if(ranges==null||ranges.size()==0||NAN.equals(ranges.get(i)))
					data.flags[i][index] = false;
				else
				{
					t = ranges.get(i).split("_");
					data.ranges[i][index][0] = Double.parseDouble(t[1]);
					data.ranges[i][index][1] = Double.parseDouble(t[2]);
					data.flags[i][index] = true;
				}
			rwl.writeLock().unlock();
		} catch (Exception e) {
			e.printStackTrace();
			rwl.writeLock().unlock();
		}
		
	}
	
	
	public List<String> getRanges(String tranType){
		String json = rm.getRangesByType(tranType);
		Gson gson = new Gson();		
		return gson.fromJson(json,  new TypeToken<List<String>>() {}.getType());
	}
	

	
	
	
	private final static  boolean inRange(double[] ys,double y){
		return ys[1]>=y&& ys[0] <= y;
	}
	
	private final static  int getIndexByFu(String ful){
		switch(ful){
			case "f1":
			case "f2":
			case "f3":
			case "f4":return 2;
			case "f6":return 4;
			case "f8":
			case "f9":return 3;
			default:return 5;
		}
	}
	
	public final static String TT_LAND_PASS = "道路客运";
	public final static String TT_LAND_GOODS = "道路货运";
	public final static String TT_LAND_BUS = "公交客运";
	public final static String TT_LAND_TAXI = "出租客运";
	public final static String TT_WATER_RIVER = "内河运输";
	public final static String TT_WATER_PASS = "海洋客运";
	public final static String TT_WATER_GOODS = "海洋货运";
	public final static String TT_WATER_PORT = "港口生产";
	
	private final static int getIndexByTranType(String tranType){

		if(TT_LAND_PASS.equals(tranType))
			return 0;
		else if(TT_LAND_GOODS.equals(tranType))
			return 1;
		else if(TT_LAND_BUS.equals(tranType))
			return 2;
		else if(TT_LAND_TAXI.equals(tranType))
			return 3;
		
		else if(TT_WATER_RIVER.equals(tranType))
			return 4;
		else if(TT_WATER_PASS.equals(tranType))
			return 5;
		else if(TT_WATER_GOODS.equals(tranType))
			return 6;
		else if(TT_WATER_PORT.equals(tranType))
			return 7;
		
		return -1;
		
	}
	
	
	public void preLoad(){
		String[] ts = {TT_LAND_PASS,TT_LAND_GOODS,TT_LAND_BUS,TT_LAND_TAXI,
				TT_WATER_RIVER,TT_WATER_PASS,TT_WATER_GOODS,TT_WATER_PORT};
		logger.info("预加载校验数据");
		for(String t:ts){
			logger.info("--->加载"+t);
			loadRange(t,this.getRanges(t));
		}
		logger.debug(data2String());
		
		
	}
	
	
	private String data2String(){
		StringBuffer sb = new StringBuffer();
		int x,y;
		x = 6;
		y = 8;
		sb.append("flags = \n");
		for(int i=0;i<x;i++){
			for(int j=0;j<y;j++){
				sb.append(data.flags[i][j]+"  ");
			}
			sb.append("\n");
		}
		sb.append("--------------------------\n");	
		x = 6;
		y = 8;
		sb.append("ranges = \n");
		for(int i=0;i<x;i++){
			for(int j=0;j<y;j++){
				sb.append(Arrays.toString(data.ranges[i][j])+"   ");
			}
			sb.append("\n");
		}
		sb.append("--------------------------\n");			
		
		
		
		return sb.toString();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		this.preLoad();
	}
	
	
	
}
