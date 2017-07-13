package app.model;

import java.util.List;

public class SerAskMessage {
	// 所有消息都包含的字段
	private String mId;
	private String from;
	private String to;
	private int mType;
	private String mTime;
	
	
	//起始消息包含的字段
	private int allItemCots;
	private int allPackageCots;
	private int packageUnit;
	private int dTypeCot;
	
	
	//数据包消息包含的字段
	private String taskId;
	private String pcgIndex;
	private int dType;
	private int pcgItemCot;

	private List dContent;
	
	//结束包包含的字段
	//private String taskId; 
	
	//以下自行添加get与set方法
	
	
	
	
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public int getmType() {
		return mType;
	}
	public void setmType(int mType) {
		this.mType = mType;
	}
	public String getmTime() {
		return mTime;
	}
	public void setmTime(String mTime) {
		this.mTime = mTime;
	}
	public int getAllItemCots() {
		return allItemCots;
	}
	public void setAllItemCots(int allItemCots) {
		this.allItemCots = allItemCots;
	}
	public int getAllPackageCots() {
		return allPackageCots;
	}
	public void setAllPackageCots(int allPackageCots) {
		this.allPackageCots = allPackageCots;
	}
	public int getPackageUnit() {
		return packageUnit;
	}
	public void setPackageUnit(int packageUnit) {
		this.packageUnit = packageUnit;
	}
	public int getdTypeCot() {
		return dTypeCot;
	}
	public void setdTypeCot(int dTypeCot) {
		this.dTypeCot = dTypeCot;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getPcgIndex() {
		return pcgIndex;
	}
	public void setPcgIndex(String pcgIndex) {
		this.pcgIndex = pcgIndex;
	}
	public int getdType() {
		return dType;
	}
	public void setdType(int dType) {
		this.dType = dType;
	}
	public int getPcgItemCot() {
		return pcgItemCot;
	}
	public void setPcgItemCot(int pcgItemCot) {
		this.pcgItemCot = pcgItemCot;
	}
	public List getdContent() {
		return dContent;
	}
	public void setdContent(List dContent) {
		this.dContent = dContent;
	}
	
	

	
	
	
	
	
}
