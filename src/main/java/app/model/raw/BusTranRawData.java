package app.model.raw;

public class BusTranRawData extends LadTraRawData {
	
	
	private double PASSENGER_TURNOVER;
	private double BUS_LENGTH;
	
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
