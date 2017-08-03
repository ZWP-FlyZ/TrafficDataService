package app.model.raw;

public class RoadGoodsRawData extends LadTraRawData {
	
	private Double TRANSPORT_TURNOVER;
	private String CAR_TYPE;
	private Double APPROVED_CARGO;
	public Double getTRANSPORT_TURNOVER() {
		return TRANSPORT_TURNOVER;
	}
	public void setTRANSPORT_TURNOVER(Double tRANSPORT_TURNOVER) {
		TRANSPORT_TURNOVER = tRANSPORT_TURNOVER;
	}
	public String getCAR_TYPE() {
		return CAR_TYPE;
	}
	public void setCAR_TYPE(String cAR_TYPE) {
		CAR_TYPE = cAR_TYPE;
	}
	public Double getAPPROVED_CARGO() {
		return APPROVED_CARGO;
	}
	public void setAPPROVED_CARGO(Double aPPROVED_CARGO) {
		APPROVED_CARGO = aPPROVED_CARGO;
	}
	
	

}
