package app.util;

public class ConvertTool {
	
	
	public static String decodeFuleType(String fuleType){
		
		switch (fuleType) {
		case "f1": fuleType="柴油";break;
		case "f2": fuleType="汽油";break;
		case "f3": fuleType="其他";break;		
		case "f4": fuleType="煤油";break;
		case "f5": fuleType="其他";break;
		case "f6": fuleType="电能";break;
		case "f7": fuleType="其他";break;
		case "f8": fuleType="液化石油气";break;
		case "f9": fuleType="液化天然气";break;
		case "f10": 
		case "f11": 
		case "f12": fuleType="混合燃料";break;
		default: throw new MyException("错误的燃料类型");
		}
		
		return fuleType;
	}
	
	public static String decodeTraType(String traType){
		
		switch (traType) {
		case "t1": traType="道路客运";break;
		case "t2": traType="道路货运";break;
		case "t3": traType="公交客运";break;
		case "t4": traType="出租客运";break;
		case "t5": traType="内河运输";break;
		case "t6": traType="海洋货运";break;
		case "t7": traType="海洋客运";break;
		case "t8": traType="港口企业";break;
		case "t9": traType="航运企业";break;
		default:throw new MyException("错误的运输类型");
		}
		
		return traType;
	}
	
	
	public static String decodeShipType(String shipType){
		return shipType;
	}
	
	public static String decodeCarType(String carType){
		return carType;
	}
	
	public static String decodeDate(String time){
		String inTime = time.substring(0, 4)+"-"+time.substring(4)+"-01";
		return inTime;
	}
	
	public static String decodeTime(String time){
		StringBuffer sb = new StringBuffer();
		sb.append(time.substring(0, 4)+"-");
		sb.append(time.substring(4,6)+"-");
		sb.append(time.substring(6,8)+" ");
		
		sb.append(time.substring(8,10)+":");
		sb.append(time.substring(10,12)+":");
		sb.append(time.substring(12,14));
		
		return sb.toString();
	}
	
	
	
	/*public static CityInfoData decodeAreaId(String areaId){
		DataService ds = new DataService();
		CityInfoData cityInfoData = ds.cityInfoMapper.getByCityId(areaId);
		return cityInfoData;
	}*/
	
	

}
