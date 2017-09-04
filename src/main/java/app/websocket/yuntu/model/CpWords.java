package app.websocket.yuntu.model;

public class CpWords {
	
	public final static String METHOD_REGISTER = "REGISTER";
	public final static String METHOD_INFO = "INFO";
	public final static String METHOD_INFO_ACK = "INFO_ACK";
	
	public final static String TYPE_REGISTER_REQ = "register_req";//注册回应
	public final static String TYPE_REGISTER_RESP = "register_resp";//
	
	public final static String TYPE_RESUME_REQ = "resume_req";//
	public final static String TYPE_RESUME_RESP = "resume_resp";//
	
	public final static String TYPE_HEART_BEAT_REQ = "heart_beat";//
	public final static String TYPE_HEART_BEAT_RESP = "heart_beat";//
	
	public final static String TYPE_DATA_REQ = "nh_jc_traffic_33_req";//
	public final static String TYPE_DATA_RESP = "nh_jc_traffic_33_resp";//
	
	public final static String BIZ_ROAD_PASS = "dl_ky";
	public final static String BIZ_ROAD_GOODS = "dl_hy";
	public final static String BIZ_BUS = "gj_ys";
	public final static String BIZ_TAXI = "cz_ys";
	
	public final static String BIZ_RIVER = "nh_ys";
	public final static String BIZ_OCEAN_GOODS = "hy_hy";
	public final static String BIZ_OCEAN_PASS = "hy_ky";
	public final static String BIZ_PORT_PRODUCE = "gk_sc";
	
	public final static String BIZ_RT_LAND = "rt_yg";
	public final static String BIZ_RT_WATER = "rt_gh";
	
	
	
	public final static String RESP_MSG_OK = "OK";
	public final static String RESP_MSG_WAIT_NEXT = "WAIT_NEXT";
	public final static String RESP_MSG_WAIT_REPEAT = "WAIT_REPEAT";
	public final static String RESP_MSG_WRONG_INDEX = "WRONG_INDEX";
	public final static String RESP_MSG_WRONG_FORMAL = "WRONG_FORMAL";
	
	public final static int RESP_ECODE_OK = 10;
	public final static int RESP_ECODE_WAIT_NEXT = 11;
	public final static int RESP_ECODE_WAIT_REPEAT = 12;
	public final static int RESP_ECODE_WRONG_INDEX = 13;
	public final static int RESP_ECODE_WRONG_FORMAL = 14;
	
	
	
	
}
