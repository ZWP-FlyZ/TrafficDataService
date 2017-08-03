package app.websocket.yuntu.model;

public class CpClient {
	
	public final static String CLIENT_STATUS_CLOSED = "CLOSED";
	public final static String CLIENT_STATUS_OPENED = "OPENED";
	
	public final static String CLIENT_STATUS_REGING = "REGING";
	public final static String CLIENT_STATUS_REGED = "REGED";
	
	public final static String CLIENT_STATUS_MISSING = "MISSING";
	public final static String CLIENT_STATUS_MISSED = "MISSED";
	
	
	private Certificate certificate;
	private String session_id;
	private String  status;
	public Certificate getCertificate() {
		return certificate;
	}
	public void setCertificate(Certificate certificate) {
		this.certificate = certificate;
	}
	public String getSession_id() {
		return session_id;
	}
	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	

	
}
