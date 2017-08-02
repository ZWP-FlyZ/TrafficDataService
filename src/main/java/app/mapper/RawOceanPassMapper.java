package app.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import app.model.raw.OceanGoodsRawData;

@Mapper
public interface RawOceanPassMapper {
	@Insert("INSERT INTO rawoceanpass(shipId,countDate,areaId,transType,companyId,"
			+ "shipNumber,fuelType,fuelCsption,psgergoTurn,countPsger,sail) "
			+ "VALUES(#{shipId},#{countDate},#{areaId},#{transType},#{companyId},"
			+ "#{shipNumber},#{fuelType},#{fuelCsption},#{psgergoTurn},#{countPsger},"
			+ "#{sail})")
	public void add(OceanGoodsRawData ogRawData);
}
