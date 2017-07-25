package app.model.raw;

public class RoadPassRawData extends LadTraRawData{
	private String CAR_ID;
	private double PASSENGER_TURNOVER;
	private String CAR_TYPE;
	private int PASSENGER_CAPACITY;
	
	
	public String getCAR_ID() {
		return CAR_ID;
	}
	public void setCAR_ID(String cAR_ID) {
		CAR_ID = cAR_ID;
	}
	public double getPASSENGER_TURNOVER() {
		return PASSENGER_TURNOVER;
	}
	public void setPASSENGER_TURNOVER(double pASSENGER_TURNOVER) {
		PASSENGER_TURNOVER = pASSENGER_TURNOVER;
	}
	public String getCAR_TYPE() {
		return CAR_TYPE;
	}
	public void setCAR_TYPE(String cAR_TYPE) {
		CAR_TYPE = cAR_TYPE;
	}
	public int getPASSENGER_CAPACITY() {
		return PASSENGER_CAPACITY;
	}
	public void setPASSENGER_CAPACITY(int pASSENGER_CAPACITY) {
		PASSENGER_CAPACITY = pASSENGER_CAPACITY;
	}

}
