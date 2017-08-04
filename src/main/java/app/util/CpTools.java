package app.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.UUID;

public class CpTools {
	public static String readJsonFile(String filename){
		StringBuffer sb = new StringBuffer();
		BufferedReader br;
		try {
			br = new BufferedReader(new InputStreamReader(CpTools.class.getResourceAsStream(filename),"UTF-8"));
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
	
	
	public static String getUUID(){
		return UUID.randomUUID().toString();
	}
}
