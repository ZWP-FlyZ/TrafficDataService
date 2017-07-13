package app.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.database.mybatis.TestMapper;

@Service
public class DataBaseService {
	
	@Autowired
	TestMapper tm;
	
	public  void addUser(){
		tm.addUser("zwp", 9);
		tm.addUser("zwp1", 93);
		tm.addUser("zwp2", 29);
		tm.addUser("zwp3", 91);
	}
	
	
}
