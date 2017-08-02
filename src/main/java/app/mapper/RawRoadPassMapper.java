package app.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import app.model.raw.RoadPassRawData;

@Mapper
public interface RawRoadPassMapper {

	@Insert("INSERT INTO rawroadpass(PLATE_NUMBER,REPORT_TIME,INDUSTRY,AREA_NAME,COMPANY_ID,"
			+ "VEHICLE_SUM,FUEL_TYPE,TOTAL_FUEL,PASSENGER_TURNOVER,CAR_TYPE,PASSENGER_CAPACITY,"
			+ "RANGE_ABILITY) "
			+ "VALUES(#{PLATE_NUMBER},#{REPORT_TIME},#{INDUSTRY},"
			+ "#{AREA_NAME},#{COMPANY_ID},#{VEHICLE_SUM},#{FUEL_TYPE},#{TOTAL_FUEL},"
			+ "#{PASSENGER_TURNOVER},#{CAR_TYPE},#{PASSENGER_CAPACITY},#{RANGE_ABILITY})")
	public void add(RoadPassRawData rpRawData);
	
}
