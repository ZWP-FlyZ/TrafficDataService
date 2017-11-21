package app.model.raw;

public class WatTraRawData implements RawData{
	protected String shipId;
	protected String countDate;
	protected String areaId;
	protected String transType;
	protected String companyId;
	protected Integer shipNumber;
	protected String fuelType;
	protected Double fuelCsption;
	protected Double sail;
	
	public String getShipId() {
		return shipId;
	}
	public void setShipId(String shipId) {
		this.shipId = shipId;
	}
	public String getCountDate() {
		return countDate;
	}
	public void setCountDate(String countDate) {
		this.countDate = countDate;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public String getTransType() {
		return transType;
	}
	public void setTransType(String transType) {
		this.transType = transType;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public Integer getShipNumber() {
		return shipNumber;
	}
	public void setShipNumber(Integer shipNumber) {
		this.shipNumber = shipNumber;
	}
	public String getFuelType() {
		return fuelType;
	}
	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}
	public Double getFuelCsption() {
		return fuelCsption;
	}
	public void setFuelCsption(Double fuelCsption) {
		this.fuelCsption = fuelCsption;
	}
	public Double getSail() {
		return sail;
	}
	public void setSail(Double sail) {
		this.sail = sail;
	}

	
}
