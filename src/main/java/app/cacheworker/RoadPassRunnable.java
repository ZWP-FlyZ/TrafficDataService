package app.cacheworker;



import java.util.ArrayList;
import java.util.List;

import app.model.raw.RoadGoodsRawData;

public class RoadPassRunnable extends DataRunnable {

	private DataPackage dp;
	private DataService ds;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		List<RoadGoodsRawData>  rgrd = (ArrayList<RoadGoodsRawData>)dp.getData();
		for(int i=0;i<rgrd.size();i++){
			
		}

	}

	@Override
	public void setDataPackage(DataPackage dp) {
		// TODO Auto-generated method stub
		this.dp = dp;
	}

	@Override
	public void setDataService(DataService ds) {
		// TODO Auto-generated method stub
		this.ds = ds;
	}
	
	

}
