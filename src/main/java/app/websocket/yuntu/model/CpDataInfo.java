package app.websocket.yuntu.model;

public class CpDataInfo {
	
	protected String doc ;
	protected String ver ;
	protected String biz ;
	protected CpMutilData mutil ;
	protected CpResultData result ;
	protected Object data;
	
	public String getDoc() {
		return doc;
	}
	public void setDoc(String doc) {
		this.doc = doc;
	}
	public String getVer() {
		return ver;
	}
	public void setVer(String ver) {
		this.ver = ver;
	}
	public String getBiz() {
		return biz;
	}
	public void setBiz(String biz) {
		this.biz = biz;
	}
	public CpMutilData getMutil() {
		return mutil;
	}
	public void setMutil(CpMutilData mutil) {
		this.mutil = mutil;
	}
	public CpResultData getResult() {
		return result;
	}
	public void setResult(CpResultData result) {
		this.result = result;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}

}
