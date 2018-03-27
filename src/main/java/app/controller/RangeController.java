package app.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import app.model.EvelData;
import app.model.EvelResponse;
import app.model.RangeResponse;
import app.model.RequestData;
import app.service.EvelService;
import app.service.RangeService;



@Controller
public class RangeController {

	@Autowired
	RangeService rs;
	
	@Autowired
	EvelService es;
	
	private Gson gson = new Gson();
	
	private final static Logger logger = LoggerFactory.getLogger(RangeController.class);
	
	@RequestMapping("/getrangeO.json")
	@ResponseBody
	public RangeResponse getRanges(HttpServletResponse response,RequestData data){
		response.setHeader("Access-Control-Allow-Origin", "*");
		RangeResponse rr = new RangeResponse();
		rr.setErrCode(30);
		rr.setTranType(data.getTranType());
		try {
			rr.setRanges(rs.getRanges(data.getTranType()));
		} catch (Exception e) {
			logger.error("获取范围数据错误",e);
			rr.setErrCode(31);
		}
		return rr;
	}
	
	@RequestMapping("/setrangeO.json")
	@ResponseBody
	public RangeResponse setRanges(HttpServletResponse response,RequestData data){
		response.setHeader("Access-Control-Allow-Origin", "*");
		RangeResponse rr = new RangeResponse();
		List<String> tmp;
		rr.setErrCode(30);
		rr.setTranType(data.getTranType());
		System.err.println("ranges = "+data.getRanges());
		try {
			tmp = gson.fromJson(data.getRanges(), new TypeToken<List<String>>(){}.getType());
			rs.setRange(data.getTranType(),tmp);
		} catch (Exception e) {
			logger.error("设置范围数据错误",e);
			rr.setErrCode(31);
		}
		return rr;
	}
	
	@RequestMapping("/getevelO.json")
	@ResponseBody
	public EvelResponse getEvel(HttpServletResponse response,RequestData data){
		response.setHeader("Access-Control-Allow-Origin", "*");
//		data.setCompanyId("");
//		data.setTimeRange("2017-08:2017-11");
//		data.setTranType("道路客运");
		
		
		EvelResponse er = new EvelResponse();
		er.setErrCode(30);
		er.setTranType(data.getTranType());
		er.setCompanyId(data.getCompanyId());
		er.setTimeRange(data.getTimeRange());
		
		try {
			List<List<EvelData>> ds = es.getEvelDataPar(data);
			er.setRawdata(ds.get(0));
			er.setData(ds.get(1));
		} catch (Exception e) {
			logger.error("设置范围数据错误",e);
			er.setErrCode(31);
		}

		
		return er;
	}
	
	
	
	
}
