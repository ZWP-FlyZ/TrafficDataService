package app.model.raw;

public class RoadGoodsRawData extends LadTraRawData {
	
	private String TRUCK_ID;
	private double TRANSPORT_TURNOVER;
	private String CAR_TYPE;
	private double APPROVED_CARGO;
	public String getTRUCK_ID() {
		return TRUCK_ID;
	}
	public void setTRUCK_ID(String tRUCK_ID) {
		TRUCK_ID = tRUCK_ID;
	}
	public double getTRANSPORT_TURNOVER() {
		return TRANSPORT_TURNOVER;
	}
	public void setTRANSPORT_TURNOVER(double tRANSPORT_TURNOVER) {
		TRANSPORT_TURNOVER = tRANSPORT_TURNOVER;
	}
	public String getCAR_TYPE() {
		return CAR_TYPE;
	}
	public void setCAR_TYPE(String cAR_TYPE) {
		CAR_TYPE = cAR_TYPE;
	}
	public double getAPPROVED_CARGO() {
		return APPROVED_CARGO;
	}
	public void setAPPROVED_CARGO(double aPPROVED_CARGO) {
		APPROVED_CARGO = aPPROVED_CARGO;
	}

}
