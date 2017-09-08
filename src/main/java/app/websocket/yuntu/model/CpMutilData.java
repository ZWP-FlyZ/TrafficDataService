package app.websocket.yuntu.model;

public class CpMutilData {
	private String taskId;
	private int totalData;
	private int totalPackage;
	private int packageIndex;
	private int packageSize;
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public int getTotalData() {
		return totalData;
	}
	public void setTotalData(int totalData) {
		this.totalData = totalData;
	}
	public int getTotalPackage() {
		return totalPackage;
	}
	public void setTotalPackage(int totalPackage) {
		this.totalPackage = totalPackage;
	}
	public int getPackageIndex() {
		return packageIndex;
	}
	public void setPackageIndex(int packageIndex) {
		this.packageIndex = packageIndex;
	}
	public int getPackageSize() {
		return packageSize;
	}
	public void setPackageSize(int packageSize) {
		this.packageSize = packageSize;
	}
	
	

}
