package app.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import app.model.RelTimWatTraData;

@Mapper
public interface RelTimWatMapper {

	@Insert("INSERT INTO ${tName} (shipId, inTime, traType, place1, place2, companyId, "
			+ "fuelType, fuelCsption, fuelCo, tranDis, longitude, latitude) "
			+ "VALUES (#{data.shipId}, #{data.inTime},  #{data.traType}, #{data.place1}, #{data.place2}, #{data.companyId}, "
			+ " #{data.fuelType},#{data.fuelCsption}, #{data.fuelCo},#{data.tranDis},#{data.longitude},#{data.latitude} )")
	public int add(@Param("tName")String tName,@Param("data")RelTimWatTraData data);
	
}
