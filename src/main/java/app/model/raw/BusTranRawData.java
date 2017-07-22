package app.model.raw;

public class BusTranRawData extends LadTraRawData {
	
	private String BUS_ID;
	private double PASSENGER_TURNOVER;
	private double BUS_LENGTH;
	public String getBUS_ID() {
		return BUS_ID;
	}
	public void setBUS_ID(String bUS_ID) {
		BUS_ID = bUS_ID;
	}
	public double getPASSENGER_TURNOVER() {
		return PASSENGER_TURNOVER;
	}
	public void setPASSENGER_TURNOVER(double pASSENGER_TURNOVER) {
		PASSENGER_TURNOVER = pASSENGER_TURNOVER;
	}
	public double getBUS_LENGTH() {
		return BUS_LENGTH;
	}
	public void setBUS_LENGTH(double bUS_LENGTH) {
		BUS_LENGTH = bUS_LENGTH;
	}

}
