package app.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import app.model.raw.OceanGoodsRawData;

@Mapper
public interface RawOceanGoodsMapper {
	@Insert("INSERT INTO rawoceangoods(shipId,countDate,areaId,transType,companyId,"
			+ "shipNumber,fuelType,fuelCsption,cargoTurn,tonnage,shipType,sail) "
			+ "VALUES(#{shipId},#{countDate},#{areaId},#{transType},#{companyId},"
			+ "#{shipNumber},#{fuelType},#{fuelCsption},#{cargoTurn},#{tonnage},"
			+ "#{shipType},#{sail})")
	public void add(OceanGoodsRawData ogRawData);
}
