package app.model.raw;

public class RelTimLadTraRawData extends LadTraRawData {
	
	private Double LATITUDE;
	private Double LONGITUDE;
	private Double TOTAL_FUEL_COAL;
	public Double getLATITUDE() {
		return LATITUDE;
	}
	public void setLATITUDE(Double lATITUDE) {
		LATITUDE = lATITUDE;
	}
	public Double getLONGITUDE() {
		return LONGITUDE;
	}
	public void setLONGITUDE(Double lONGITUDE) {
		LONGITUDE = lONGITUDE;
	}
	public Double getTOTAL_FUEL_COAL() {
		return TOTAL_FUEL_COAL;
	}
	public void setTOTAL_FUEL_COAL(Double tOTAL_FUEL_COAL) {
		TOTAL_FUEL_COAL = tOTAL_FUEL_COAL;
	}
}
