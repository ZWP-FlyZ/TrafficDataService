package app.model;

public class LadTraData {
	///陆上交通数据类，当前还未确 定数据字段
	//protected int id;
	protected String carId;
	protected String inTime;
	protected String traType;
	protected String place1;
	protected String place2;
	protected String companyId;
	protected String fuelType;
	protected Double fuelCsption;
	protected Double goTurn;
	protected Double tranDis;
	protected Integer entS;       //The type of raw data is double 
	public String getCarId() {
		return carId;
	}
	public void setCarId(String carId) {
		this.carId = carId;
	}
	public String getInTime() {
		return inTime;
	}
	public void setInTime(String inTime) {
		this.inTime = inTime;
	}
	public String getTraType() {
		return traType;
	}
	public void setTraType(String traType) {
		this.traType = traType;
	}
	public String getPlace1() {
		return place1;
	}
	public void setPlace1(String palce1) {
		this.place1 = palce1;
	}
	public String getPlace2() {
		return place2;
	}
	public void setPlace2(String palce2) {
		this.place2 = palce2;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
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
	public Double getGoTurn() {
		return goTurn;
	}
	public void setGoTurn(Double goTurn) {
		this.goTurn = goTurn;
	}
	public Integer getEntS() {
		return entS;
	}
	public void setEntS(Integer entS) {
		this.entS = entS;
	}
	public Double getTranDis() {
		return tranDis;
	}
	public void setTranDis(Double tranDis) {
		this.tranDis = tranDis;
	}
	
	
	
	
}
