package app.model;

public class RelTimWatTraData extends WatTraData {
	private Double fuelCo;
	private Double latitude;
	private Double longitude;
	public Double getFuelCo() {
		return fuelCo;
	}
	public void setFuelCo(Double fuelCo) {
		this.fuelCo = fuelCo;
	}
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
}
