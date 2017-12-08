package app.model;

import java.util.List;

public class RequestData {
	private String tranType;
	private List<String> ranges;

		
	private String timeRange;
	private String companyId;
	public String getTranType() {
		return tranType;
	}

	public void setTranType(String tranType) {
		this.tranType = tranType;
	}

	public List<String> getRanges() {
		return ranges;
	}

	public void setRanges(List<String> ranges) {
		this.ranges = ranges;
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
	
	
	
}
