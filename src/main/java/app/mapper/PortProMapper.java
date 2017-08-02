package app.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import app.model.PortProData;

@Mapper
public interface PortProMapper {

	@Insert("INSERT INTO portproduct(companyId,inTime,traType,place1,place2,diesel"
			+ "gasoline,coal,power,other,entS,proTask) "
			+ "VALUES(#{companyId},#{inTime},#{traType},#{place1},#{place2},#{diesel},"
			+ "#{gasoline},#{coal},#{power},#{other},#{entS},#{proTask})")
	public void add(PortProData portProData);
}
