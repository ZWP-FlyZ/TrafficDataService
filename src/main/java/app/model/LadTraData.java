package app.model;

public class LadTraData {
	///陆上交通数据类，当前还未确 定数据字段
	//protected int id;
	protected String carId;
	protected String inTime;
	protected String traType;
	protected String palce1;
	protected String palce2;
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
	public String getPalce1() {
		return palce1;
	}
	public void setPalce1(String palce1) {
		this.palce1 = palce1;
	}
	public String getPalce2() {
		return palce2;
	}
	public void setPalce2(String palce2) {
		this.palce2 = palce2;
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
