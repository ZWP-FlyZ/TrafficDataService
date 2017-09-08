package app.websocket.yuntu.model;

public class Certificate {
	
	private CInfo info;
	private String check;
	
	public String getCheck() {
		return check;
	}

	public void setCheck(String check) {
		this.check = check;
	}


	public CInfo getInfo() {
		return info;
	}


	public void setInfo(CInfo info) {
		this.info = info;
	}


	static class CInfo{
		public Integer ver;
		public String sn;
		public String user;
		public String begin;
		public String end;
		public String area;
		public String ip;
		public String idlist;
		public String subscribe;
		public String notify;
		public String send;
		public Integer limit;
		public Boolean localhost;
		public String email;
		public String tel;
		public String server_id;
	}
}
