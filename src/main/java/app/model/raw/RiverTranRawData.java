package app.model.raw;

public class RiverTranRawData extends WatTraRawData {
	
	private Double cargoTurn;
	private Double tonnage;
	private String shipType;
	public Double getCargoTurn() {
		return cargoTurn;
	}
	public void setCargoTurn(Double cargoTurn) {
		this.cargoTurn = cargoTurn;
	}
	public Double getTonnage() {
		return tonnage;
	}
	public void setTonnage(Double tonnage) {
		this.tonnage = tonnage;
	}
	public String getShipType() {
		return shipType;
	}
	public void setShipType(String shipType) {
		this.shipType = shipType;
	}

}
