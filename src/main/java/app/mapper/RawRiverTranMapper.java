package app.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import app.model.raw.RiverTranRawData;

@Mapper
public interface RawRiverTranMapper {
	
	@Insert("INSERT INTO rawrivertran(shipId,countDate,areaId,transType,companyId,"
			+ "shipNumber,fuelType,fuelCsption,cargoTurn,tonnage,shipType,sail) "
			+ "VALUES(#{shipId},#{countDate},#{areaId},#{transType},#{companyId},"
			+ "#{shipNumber},#{fuelType},#{fuelCsption},#{cargoTurn},#{tonnage},"
			+ "#{shipType},#{sail})")
	public void add(RiverTranRawData rtRawData);

}
