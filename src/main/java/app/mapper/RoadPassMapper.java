package app.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import app.model.RoadPassData;

@Mapper
public interface RoadPassMapper {
	
	@Insert("INSERT INTO roadpass(carId,inTime,traType,place1,place2,companyId,"
			+ "fuelType,fuelCsption,goTurn,carType,sitCot,tranDis,entS) "
			+ "VALUES(#{carId},#{inTime},#{traType},#{place1},#{place2},#{companyId},"
			+ "#{fuelType},#{fuelCsption},#{goTurn},#{carType},#{sitCot},"
			+ "#{tranDis},#{entS})")
	public void addRoadPass(RoadPassData rpData);

}
