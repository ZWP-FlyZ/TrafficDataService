package app.model;

import java.util.List;

public class EvelResponse {
	private int errCode;
	private String tranType;
	private String timeRange;
	private String companyId;
	
	private List<EvelData> data;
	private List<EvelData> rawdata;
	public int getErrCode() {
		return errCode;
	}
	public void setErrCode(int errCode) {
		this.errCode = errCode;
	}
	public String getTranType() {
		return tranType;
	}
	public void setTranType(String tranType) {
		this.tranType = tranType;
	}
	public String getTimeRange() {
		return timeRange;
	}
	public void setTimeRange(String timeRange) {
		this.timeRange = timeRange;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public List<EvelData> getData() {
		return data;
	}
	public void setData(List<EvelData> data) {
		this.data = data;
	}
	public List<EvelData> getRawdata() {
		return rawdata;
	}
	public void setRawdata(List<EvelData> rawdata) {
		this.rawdata = rawdata;
	}
	
	
}
