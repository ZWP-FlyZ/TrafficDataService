package service.app;

import org.junit.Test;

import com.google.gson.Gson;

import app.util.ConvertTool;

public class DecodeTest {

	@Test
	public void test() {
		System.err.println(ConvertTool.decodeTime("20170101202022"));
		
		Gson gson = new Gson();
		String[] ss = {"1_1.1_1.11","21_21.1_21.11","31_31.1_31.11",
						"41_41.1_41.11","51_51.1_51.11","61_61.1_61.11"};
		System.err.println(gson.toJson(ss));
		
		//System.err.println(EvelService.getTimes("2017-01:2017-12"));
		
		System.err.println(Double.parseDouble(".4"));
		System.err.println(Double.parseDouble("4."));
	}

}
