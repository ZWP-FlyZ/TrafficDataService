package app.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import app.model.raw.BusTranRawData;

@Mapper
public interface RawBusTranMapper {
	
	@Insert("INSERT INTO rawbustran(PLATE_NUMBER,REPORT_TIME,INDUSTRY,AREA_NAME,"
			+ "COMPANY_ID,VEHICLE_SUM,FUEL_TYPE,TOTAL_FUEL,PASSENGER_TURNOVER,"
			+ "BUS_LENGTH,RANGE_ABILITY) "
			+ "VALUES(#{PLATE_NUMBER},#{REPORT_TIME},#{INDUSTRY},"
			+ "#{AREA_NAME},#{COMPANY_ID},#{VEHICLE_SUM},#{FUEL_TYPE},#{TOTAL_FUEL},"
			+ "#{PASSENGER_TURNOVER},#{BUS_LENGTH},#{RANGE_ABILITY})")
	public void add(BusTranRawData bTranRawData);

}
