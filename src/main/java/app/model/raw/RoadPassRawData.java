package app.model.raw;

public class RoadPassRawData extends LadTraRawData{
	
	private Double PASSENGER_TURNOVER;
	private String CAR_TYPE;
	private Integer PASSENGER_CAPACITY;
	public Double getPASSENGER_TURNOVER() {
		return PASSENGER_TURNOVER;
	}
	public void setPASSENGER_TURNOVER(Double pASSENGER_TURNOVER) {
		PASSENGER_TURNOVER = pASSENGER_TURNOVER;
	}
	public String getCAR_TYPE() {
		return CAR_TYPE;
	}
	public void setCAR_TYPE(String cAR_TYPE) {
		CAR_TYPE = cAR_TYPE;
	}
	public Integer getPASSENGER_CAPACITY() {
		return PASSENGER_CAPACITY;
	}
	public void setPASSENGER_CAPACITY(Integer pASSENGER_CAPACITY) {
		PASSENGER_CAPACITY = pASSENGER_CAPACITY;
	}
	
	
	

}
