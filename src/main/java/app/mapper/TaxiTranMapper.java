package app.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import app.model.TaxiTranData;

@Mapper
public interface TaxiTranMapper {
	@Insert("INSERT INTO taxitran(carId,inTime,traType,place1,place2,companyId,"
			+ "fuelType,fuelCsption,goTurn,dpCot,tranDis,entS) "
			+ "VALUES(#{carId},#{inTime},#{traType},#{place1},#{place2},#{companyId},"
			+ "#{fuelType},#{fuelCsption},#{goTurn},#{dpCot},"
			+ "#{tranDis},#{entS})")
	public void addTaxiTran(TaxiTranData ttData);
	
}
