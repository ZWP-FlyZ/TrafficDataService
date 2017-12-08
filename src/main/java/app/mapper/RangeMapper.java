package app.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface RangeMapper {
	
	@Select("SELECT typeS FROM alltypes where typeName = #{typeName}")
	public String getRangesByType(String typeName);
	
	@Insert("insert into alltypes(typeName,name,typeS) "
			+ "values(#{typeName},#{typeName},#{ranges}) "
			+ "ON DUPLICATE KEY UPDATE typeS=#{ranges} ")
	public int insertRangeByType(@Param("typeName")String typeName ,@Param("ranges")String ranges );
}
