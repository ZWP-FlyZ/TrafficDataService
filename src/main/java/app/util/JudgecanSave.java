package app.util;

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JudgecanSave {

	
	private static Logger logger = LoggerFactory.getLogger(JudgecanSave.class);
	
	@SuppressWarnings("rawtypes")
	public static boolean JudgeNull(Class cur_class,Object obj,boolean haveNull){
		try {
			for (Field field : cur_class.getDeclaredFields()) {
				field.setAccessible(true);
				//logger.debug(field.getName()+": "+field.get(obj));
				if (field.get(obj)==null) {
					haveNull = true;
					return true;
				}
			}
			if (cur_class.getSuperclass() != null) {
				haveNull = JudgeNull(cur_class.getSuperclass(), obj, haveNull);
			}
		} catch (Exception e) {
			// TODO: handle exception
			
			logger.error("JudgeNull Err" ,e);
		}
		
		return haveNull;
	}
	
	@SuppressWarnings("rawtypes")
	public static boolean JudgePortProNull(Class cur_class,Object obj,boolean haveNull){
		int flag = 0;
		int count = 0;
		try {
			for (Field field : cur_class.getDeclaredFields()) {
				flag++;
				field.setAccessible(true);
				if (field.get(obj)==null) {
					if (flag>11&&flag<5) {
						haveNull = true;
					}else{
						count++;
						field.set(obj, 0.000);
					}	
				}
			}
			if (count>6) {
				haveNull = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return haveNull;
	}
	
}
