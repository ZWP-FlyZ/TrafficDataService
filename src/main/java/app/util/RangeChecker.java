package app.util;

import app.model.raw.BusTranRawData;
import app.model.raw.OceanGoodsRawData;
import app.model.raw.RawData;
import app.model.raw.RiverTranRawData;
import app.model.raw.RoadGoodsRawData;
import app.model.raw.RoadPassRawData;
import app.model.raw.TaxiTranRawData;

public class RangeChecker {
	
	private final static double[] x_roadgoods = 	{2.0,4.5,7.0,20.0};//载重量
	private final static double[][] y_roadgoods = {{0.7913,1.6478},
												  {0.5749,1.3090},
												  {0.4893,0.8563},
												  {0.1223,0.4282},
												  {0.1187,0.3542}};
	
	private final static double[] x_roadpass = 	{15.0,30.0,45.0};//客座分类
	private final static double[][] y_roadpass = {{0.0550,0.0856},//<15
												  {0.0550,0.0856},// 15-30
												  {0.0367,0.0819},//30-45
												  {0.0501,0.0831}};	//45>
	
	private final static double[] x_bustran = 	{5.0,8.0,11.0,14};//车长分类
	private final static double[][] y_bustran = {{0.1311,0.2142},//<5
												  {0.1311,0.2142},//5-8
												  {0.0874,0.2049},// 8-11
												  {0.0913,0.2079},//11-14
												  {0.0913,0.2079}};	//14>
	
	private final static double[] x_taxitran = 	{};//
	private final static double[][] y_taxitran = {{0.0367,0.1469}};
	
	
	private final static double[] x_ship = 	{1000.0,3000.0,10000.0};//车长分类
	private final static double[][] y_ship = {{0.0873,0.1034},//<1000
												  {0.0599,0.0884},//1000-3000
												  {0.0349,0.0871},// 3000-10000
												  {0.0332,0.0541}};	//10000
	
	
	
	
	private final static  int index( double[] xs,double x){
		int index=0;
		while(index<xs.length){
			if(xs[index]>x) return index;
			else index++;
		}
		return index;
	};
	
	private final static boolean inRange(double[] ys,double y){
		return ys[1]>=y&& ys[0] <= y;
	}
	
	
	public final static boolean checkRoadGoods(RoadGoodsRawData rd){
//		System.err.println(rd.getAPPROVED_CARGO());
//		int i = index(x_roadgoods,rd.getAPPROVED_CARGO());
//		System.err.println(i);
//		
//		System.err.println(rd.getTOTAL_FUEL());
//		System.err.println(rd.getTRANSPORT_TURNOVER());	
		return inRange(
						y_roadgoods[index(x_roadgoods,rd.getAPPROVED_CARGO())], // 载重范围
						rd.getTOTAL_FUEL()/rd.getTRANSPORT_TURNOVER() //单位能耗
					);
	}
	
	public final static boolean checkRoadPass(RoadPassRawData rd){
		
		return inRange(
				y_roadpass[index(x_roadpass,new Double(rd.getPASSENGER_CAPACITY()))], // 载重范围
				rd.getTOTAL_FUEL()/rd.getPASSENGER_TURNOVER()//单位能耗
			);
	}
	
	public final static boolean checkBusTran(BusTranRawData rd){
		
		return inRange(
				y_bustran[index(x_bustran,rd.getBUS_LENGTH())], // 载重范围
				rd.getTOTAL_FUEL()/rd.getPASSENGER_TURNOVER()//单位能耗
			);
	}
	
	public final static boolean checkTaxiTran(TaxiTranRawData rd){
		return inRange(
				y_taxitran[0], // 载重范围
				rd.getTOTAL_FUEL()/rd.getPASSENGER_TURNOVER()//单位能耗
			);
	}
	
	public final static boolean checkRiverTran(RiverTranRawData rd){
		return inRange(
				y_ship[index(x_ship,rd.getTonnage())], // 载重范围
				rd.getFuelCsption()/rd.getCargoTurn()//单位能耗
			);
	}
	
	public final static boolean checkOceanGoods(OceanGoodsRawData rd){
		return inRange(
				y_ship[index(x_ship,rd.getTonnage())], // 载重范围
				rd.getFuelCsption()/rd.getCargoTurn()//单位能耗
			);
	}
	
	

}
