package service.app;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Test;

import com.google.gson.Gson;

import app.websocket.yuntu.model.Certificate;
import app.websocket.yuntu.model.CpData;
import app.websocket.yuntu.model.CpProtocalInfo;


public class GsonTest {

	Gson gson = new Gson();
	@Test
	public void test() {
		//fail("Not yet implemented");
		String json = getJsonFromFile("src/main/java/app/websocket/yuntu/model/certificate.json");
		System.err.println(json);
		Certificate d = gson.fromJson(json, Certificate.class);
		CpData cpData = new CpData();
		CpProtocalInfo pinfo = new CpProtocalInfo();
		pinfo.setCertificate(d);
		cpData.setInfo(pinfo);
		
		cpData.setMethod("register");
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
