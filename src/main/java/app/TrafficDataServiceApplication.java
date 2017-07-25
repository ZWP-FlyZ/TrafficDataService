package app;

import org.springframework.boot.CommandLineRunner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class TrafficDataServiceApplication implements CommandLineRunner  {
	
	/*private final DataBaseService test;
	
	public TrafficDataServiceApplication(DataBaseService test){
		this.test = test;
	}*/
	
	public static void main(String[] args) {
		SpringApplication.run(TrafficDataServiceApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		// TODO Auto-generated method stub
		//test.addUser();
		
	}
}
