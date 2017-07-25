package app.cacheworker;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.mapper.CityInfoMapper;
import app.mapper.RawRoadGoodsMapper;
import app.mapper.RoadGoodsMapper;
import app.mapper.RoadPassMapper;
import app.model.RoadGoodsData;
import app.model.raw.RoadGoodsRawData;

public class RoadGoodsRunnable extends DataRunnable {

	private DataPackage dp;
	
	
	@Autowired
	private RawRoadGoodsMapper rawRoadGoodsMapper;
	
	@Autowired 
	private CityInfoMapper cityInfoMapper;
	
	@Autowired
	private RoadGoodsMapper roadGoodsMapper;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		List<RoadGoodsRawData> rgrdList = (ArrayList<RoadGoodsRawData>)dp.getData();
		for(RoadGoodsRawData rgrd:rgrdList){
			rawRoadGoodsMapper.addRowRoadGoods(rgrd);
			RoadGoodsData rogoData = new RoadGoodsData();
			
			
		}

	}

	@Override
	public void setDataPackage(DataPackage dp) {
		// TODO Auto-generated method stub
		this.dp = dp;
	}

}
