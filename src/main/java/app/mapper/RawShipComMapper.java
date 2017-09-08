package app.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import app.model.raw.PortProRawData;

@Mapper
public interface RawShipComMapper {

	@Insert("INSERT INTO rawshipcompany(companyId,countDate,areaId,transType,diesel,"
			+ "gasoline,otherOil,coal,otherCoal,power,other,throughput,proTask) "
			+ "VALUES(#{companyId},#{countDate},#{areaId},#{transType},#{diesel},"
			+ "#{gasoline},#{otherOil},#{coal},#{otherCoal},#{power},#{other},"
			+ "#{throughput},#{proTask})")
	public void add(PortProRawData shipRawData);
}
