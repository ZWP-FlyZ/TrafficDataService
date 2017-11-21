package app.cacheworker;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import app.model.BusTranData;
import app.model.CityInfoData;
import app.model.raw.BusTranRawData;
import app.util.GraftTraData;
import app.util.JudgecanSave;
import app.util.RangeChecker;

public class BusTranRunnable extends DataRunnable {

	private DataPackage dp;
	private DataService ds;
	
	private final static  Logger logger = LoggerFactory.getLogger("DataRunnable");
	@Override
	public void run() {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<BusTranRawData> btrdList = (ArrayList<BusTranRawData>)dp.getData();
		boolean haveNull;
		for (BusTranRawData rawData : btrdList) {
			
			
			try {
				haveNull = false;
				haveNull = JudgecanSave.JudgeNull(rawData.getClass(), rawData, haveNull);
				if (!haveNull&&RangeChecker.checkBusTran(rawData)) {
					BusTranData newData = new BusTranData();
					GraftTraData.cvtLadTraData(rawData, newData);
					this.cvtBusTranData(rawData, newData);
					ds.busTranMapper.add(newData);
				}
				ds.rawBusTranMapper.add(rawData);
			} catch (Exception e) {
				// TODO: handle exception
				String es = "exec data err: " +new Gson().toJson(rawData);
				logger.error(es,e);
			}
		
		}

	}

	public void cvtBusTranData(BusTranRawData rawData,BusTranData newData){
		newData.setGoTurn(rawData.getPASSENGER_TURNOVER());
		newData.setCarLength(rawData.getBUS_LENGTH());
		
		CityInfoData cityInfoData = ds.citiesMap.getCityInfo(rawData.getAREA_NAME());
		newData.setPlace1(cityInfoData.getCity());
		newData.setPlace2(cityInfoData.getCounty());
		
	}
	
	@Override
	public void setDataPackage(DataPackage dp) {
		// TODO Auto-generated method stub
		this.dp = dp;
	}

	@Override
	public void setDataService(DataService ds) {
		// TODO Auto-generated method stub
		this.ds =ds;
	}

}
