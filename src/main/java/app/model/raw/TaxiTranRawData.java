package app.model.raw;

public class TaxiTranRawData extends LadTraRawData {

	private String TAXI_ID;
	private double PASSENGER_TURNOVER;
	private double ENGINE_DISPLACEMENT;
	public String getTAXI_ID() {
		return TAXI_ID;
	}
	public void setTAXI_ID(String tAXI_ID) {
		TAXI_ID = tAXI_ID;
	}
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
