package app.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import app.model.CityInfoData;

@Mapper
public interface CityInfoMapper {
	
	@Select("SELECT * FROM cityinfo WHERE areaId = #{areaId}")
	public CityInfoData getByCityId(String areaId);

}
