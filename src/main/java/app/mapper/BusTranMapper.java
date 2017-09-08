package app.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import app.model.BusTranData;

@Mapper
public interface BusTranMapper {
	
	@Insert("INSERT INTO bustran(carId,inTime,traType,place1,place2,companyId,"
			+ "fuelType,fuelCsption,goTurn,carLength,tranDis,entS) "
			+ "VALUES(#{carId},#{inTime},#{traType},#{place1},#{place2},#{companyId},"
			+ "#{fuelType},#{fuelCsption},#{goTurn},#{carLength},"
			+ "#{tranDis},#{entS})")
	public void add(BusTranData btData);

}
