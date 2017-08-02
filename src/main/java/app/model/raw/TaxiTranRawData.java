package app.model.raw;

public class TaxiTranRawData extends LadTraRawData {


	private double PASSENGER_TURNOVER;
	private double ENGINE_DISPLACEMENT;
	
	public double getPASSENGER_TURNOVER() {
		return PASSENGER_TURNOVER;
	}
	public void setPASSENGER_TURNOVER(double pASSENGER_TURNOVER) {
		PASSENGER_TURNOVER = pASSENGER_TURNOVER;
	}
	public double getENGINE_DISPLACEMENT() {
		return ENGINE_DISPLACEMENT;
	}
	public void setENGINE_DISPLACEMENT(double eNGINE_DISPLACEMENT) {
		ENGINE_DISPLACEMENT = eNGINE_DISPLACEMENT;
	}
	
}
