package app.model.raw;

public class RelTimWatTraRawData extends WatTraRawData {
	
	private Double latitude;
	private Double longitude;
	private Double fuelCsptionCoal;
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getFuelCsptionCoal() {
		return fuelCsptionCoal;
	}
	public void setFuelCsptionCoal(Double fuelCsptionCoal) {
		this.fuelCsptionCoal = fuelCsptionCoal;
	}
	
}
