package app.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import app.model.raw.TaxiTranRawData;

@Mapper
public interface RawTaxiTranMapper {

	@Insert("INSERT INTO rawtaxitran(TAXI_ID,PLATE_NUMBER,REPORT_TIME,INDUSTRY,AREA_NAME,"
			+ "COMPANY_ID,VEHICLE_SUM,FUEL_TYPE,TOTAL_FUEL,PASSENGER_TURNOVER,"
			+ "ENGINE_DISPLACEMENT,RANGE_ABILITY) "
			+ "VALUES(#{BUS_ID},#{PLATE_NUMBER},#{REPORT_TIME},#{INDUSTRY},"
			+ "#{AREA_NAME},#{COMPANY_ID},#{VEHICLE_SUM},#{FUEL_TYPE},#{TOTAL_FUEL},"
			+ "#{PASSENGER_TURNOVER},#{ENGINE_DISPLACEMENT},#{RANGE_ABILITY})")
	public void addRawTaxiTRran(TaxiTranRawData ttRawData);
	
}
