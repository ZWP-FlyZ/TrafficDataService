package app.model;

public class SerRspMessage {
	
	// 所有消息都包含的字段
	private String mId;
	private String from;
	private String to;
	private int mType;
	private String mTime;
	
	
	private int reCode;
	private String reContent;
	private String taskId;
	private int pcgIndex;
	
	private int ortContent1;
	private String ortContent2;
	
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
	public int getReCode() {
		return reCode;
	}
	public void setReCode(int reCode) {
		this.reCode = reCode;
	}
	public String getReContent() {
		return reContent;
	}
	public void setReContent(String reContent) {
		this.reContent = reContent;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public int getPcgIndex() {
		return pcgIndex;
	}
	public void setPcgIndex(int pcgIndex) {
		this.pcgIndex = pcgIndex;
	}
	public int getOrtContent1() {
		return ortContent1;
	}
	public void setOrtContent1(int ortContent1) {
		this.ortContent1 = ortContent1;
	}
	public String getOrtContent2() {
		return ortContent2;
	}
	public void setOrtContent2(String ortContent2) {
		this.ortContent2 = ortContent2;
	}
	
	

}
