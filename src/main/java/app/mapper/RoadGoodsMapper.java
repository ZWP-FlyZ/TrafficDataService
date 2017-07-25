package app.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import app.model.RoadGoodsData;

@Mapper
public interface RoadGoodsMapper {
	
	@Insert("INSERT INTO roadgoods(carId,inTime,traType,place1,place2,companyId,"
			+ "fuelType,fuelCsption,goTurn,carType,tonnage,tranDis,entS) "
			+ "VALUES(#{carId},#{inTime},#{traType},#{place1},#{place2},#{companyId},"
			+ "#{fuelType},#{fuelCsption},#{goTurn},#{carType},#{tonnage},"
			+ "#{tranDis},#{entS})")
	public void addRoadGoods(RoadGoodsData rgData);

}
