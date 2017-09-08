package service.app;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import app.model.raw.BusTranRawData;
import app.util.CpTools;
import app.websocket.yuntu.model.CpData;
import app.websocket.yuntu.model.CpDataInfo;
import app.websocket.yuntu.model.CpInfoDeserializer;
import app.websocket.yuntu.model.CpMutilData;
import app.websocket.yuntu.model.CpWords;


public class GsonTest {

	
	Gson gson = null;
	GsonBuilder gsonBuilder = new GsonBuilder();
	CpData cd = new CpData();
	
	@Before
	public void init(){
		gsonBuilder.registerTypeAdapter(CpData.class, new CpInfoDeserializer());
		gson = gsonBuilder.create();
		
		cd.setMethod(CpWords.METHOD_INFO);
		cd.setType(CpWords.TYPE_DATA_REQ);
		cd.setFrom("{0B51951A-DE70-43BF-B31D-D1C6FB15E4DD}");
		cd.setTo("{1B51951A-DE70-43BF-B31D-D1C6FB15E4DD}");
		cd.setCall_id(CpTools.getUUID());
		
		List<BusTranRawData> ss = new ArrayList<>();
		
		for(int i=1;i<10;i++){
			BusTranRawData d = new BusTranRawData();
			d.setPLATE_NUMBER("car"+i);
			
			d.setREPORT_TIME("201701");
			d.setINDUSTRY("t3");
			d.setAREA_NAME("330102");
			d.setCOMPANY_ID("123123123");
			d.setVEHICLE_SUM(new Double(i));
			
			d.setFUEL_TYPE("f3");
			d.setTOTAL_FUEL((double)i);
			d.setPASSENGER_TURNOVER((double)i +1);
			d.setBUS_LENGTH((double)i);
			d.setRANGE_ABILITY(new Double(i));
			
			ss.add(d);
		}
		
		CpMutilData md = new CpMutilData();
		
		md.setTaskId("taskId");
		md.setTotalPackage(10);
		md.setPackageIndex(1);
		md.setPackageSize(10);
		md.setTotalData(1000);
		
		CpDataInfo di = new CpDataInfo();
		di.setDoc("doc1");
		di.setVer("1.0");
		di.setBiz(CpWords.BIZ_BUS);
		di.setData(ss);
		di.setMutil(md);
		
		cd.setInfo(di);
		
		System.err.println(gson.toJson(cd));
		
		
		
		
		
	}
	
	@Test
	public void test() {
		//fail("Not yet implemented");
		String json = getJsonFromFile("src/test/java/service/app/TestJson.json");
		System.err.println(json);
		CpData cpData = gson.fromJson(json, CpData.class);
				
		System.err.println(cpData.getInfo().getClass().getName());
		System.err.println(gson.toJson(cpData));
	
	}
	
	private  String getJsonFromFile(String fileName){
		StringBuffer sb = new StringBuffer();
		BufferedReader br;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName),"UTF-8"));
			String line = null;
			while ((line = br.readLine()) != null) {
			      sb.append(line);
			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return sb.toString();
	}

}
