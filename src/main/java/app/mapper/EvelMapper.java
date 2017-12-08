package app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import app.model.EvelData;

@Mapper
public interface EvelMapper {

	
	@Select("SELECT COUNT(*) as cot,REPORT_TIME as ym,COMPANY_ID as companyId FROM "
			+ "(SELECT REPORT_TIME,COMPANY_ID FROM ${tName} "
			+ "  where REPORT_TIME >= #{st} and  REPORT_TIME <= #{et}) "
			+ "AS A WHERE COMPANY_ID like #{companyId} group by REPORT_TIME,COMPANY_ID;")
	public List<EvelData> getEvelFromRawLad(@Param("tName")String tName,@Param("st")String st,
												@Param("et")String et,@Param("companyId")String companyId);
	
	@Select("SELECT COUNT(*) as cot,countDate as ym,companyId as companyId FROM "
			+ "(SELECT countDate,companyId FROM ${tName} "
			+ "where countDate >= #{st} and  countDate <= #{et} ) "
			+ "AS A WHERE companyId like #{companyId} group by countDate,companyId;")
	public List<EvelData> getEvelFromRawWat(@Param("tName")String tName,@Param("st")String st,
												@Param("et")String et,@Param("companyId")String companyId);
	
	
	@Select("SELECT COUNT(*) as cot,inTime as ym,companyId as companyId FROM "
			+ "(SELECT inTime,companyId FROM ${tName} "
			+ "where inTime >= #{st} and  inTime <= #{et} ) "
			+ "AS A WHERE companyId like #{companyId} group by inTime,companyId;")
	public List<EvelData> getEvelFromAll(@Param("tName")String tName,@Param("st")String st,
												@Param("et")String et,@Param("companyId")String companyId);	
	
}
