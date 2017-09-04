package app.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import app.model.raw.RelTimWatTraRawData;

@Mapper
public interface RowRelTimWatMapper {

	@Insert("INSERT INTO ${tName} (shipId,countDate,latitude,longitude,areaId,transType,companyId,"
			+ "fuelType,fuelCsption,fuelCsptionCoal,sail) "
			+ "VALUES(#{data.shipId},#{data.countDate}, #{data.latitude},#{data.longitude},#{data.areaId},"
			+ "#{data.transType},#{data.companyId},"
			+ "#{data.fuelType},#{data.fuelCsption},#{data.fuelCsptionCoal},#{data.sail})")
	public void add(@Param("tName")String tName,@Param("data")RelTimWatTraRawData data);
	
}
