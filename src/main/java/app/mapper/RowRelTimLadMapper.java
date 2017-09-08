package app.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import app.model.raw.RelTimLadTraRawData;

@Mapper
public interface RowRelTimLadMapper {
	
	
	@Insert("INSERT INTO ${tName} (PLATE_NUMBER,REPORT_TIME,LATITUDE,LONGITUDE,INDUSTRY,AREA_NAME,"
			+ "COMPANY_ID,FUEL_TYPE,TOTAL_FUEL,TOTAL_FUEL_COAL,RANGE_ABILITY) "
			+ "VALUES(#{data.PLATE_NUMBER},#{data.REPORT_TIME},#{data.LATITUDE},#{data.LONGITUDE},#{data.INDUSTRY},"
			+ "#{data.AREA_NAME},#{data.COMPANY_ID},#{data.FUEL_TYPE},#{data.TOTAL_FUEL},"
			+ "#{data.TOTAL_FUEL_COAL},#{data.RANGE_ABILITY})")
	public int add(@Param("tName")String tName,@Param("data")RelTimLadTraRawData data);
	
}
