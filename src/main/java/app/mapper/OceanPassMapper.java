package app.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import app.model.OceanPassData;

@Mapper
public interface OceanPassMapper {

	@Insert("INSERT INTO oceanpass(shipId,inTime,traType,place1,place2,companyId,"
			+ "fuelType,fuelCsption,goTurn,sitCot,shipType,tranDis,entS) "
			+ "VALUES(#{shipId},#{inTime},#{traType},#{place1},#{place2},#{companyId},"
			+ "#{fuelType},#{fuelCsption},#{goTurn},#{sitCot},#{shipType},"
			+ "#{tranDis},#{entS})")
	public void add(OceanPassData oceanPassData);
}
