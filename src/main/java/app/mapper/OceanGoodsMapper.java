package app.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import app.model.OceanGoodsData;

@Mapper
public interface OceanGoodsMapper {
	
	@Insert("INSERT INTO oceangoods(shipId,inTime,traType,place1,place2,companyId,"
			+ "fuelType,fuelCsption,goTurn,tonnage,shipType,tranDis,entS) "
			+ "VALUES(#{shipId},#{inTime},#{traType},#{place1},#{place2},#{companyId},"
			+ "#{fuelType},#{fuelCsption},#{goTurn},#{tonnage},#{shipType},"
			+ "#{tranDis},#{entS})")
	public void add(OceanGoodsData oceanGoodsData);

}
