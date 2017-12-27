package app.cacheworker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.mapper.BusTranMapper;
import app.mapper.OceanGoodsMapper;
import app.mapper.OceanPassMapper;
import app.mapper.PortProMapper;
import app.mapper.RawBusTranMapper;
import app.mapper.RawOceanGoodsMapper;
import app.mapper.RawOceanPassMapper;
import app.mapper.RawPortProMapper;
import app.mapper.RawRiverTranMapper;
import app.mapper.RawRoadGoodsMapper;
import app.mapper.RawRoadPassMapper;
import app.mapper.RawShipComMapper;
import app.mapper.RawTaxiTranMapper;
import app.mapper.RelTimLadMapper;
import app.mapper.RelTimWatMapper;
import app.mapper.RiverTranMapper;
import app.mapper.RoadGoodsMapper;
import app.mapper.RoadPassMapper;
import app.mapper.RowRelTimLadMapper;
import app.mapper.RowRelTimWatMapper;
import app.mapper.TaxiTranMapper;
import app.service.RangeService;
import app.util.CitiesMap;



@Service
public class DataService {
	
	@Autowired
	public RawRoadPassMapper rawRoadPassMapper;
	
	@Autowired
	public CitiesMap citiesMap;
	
	@Autowired
	public RoadPassMapper roadPassMapper;
	
	@Autowired
	public RoadGoodsMapper roadGoodsMapper;
	
	@Autowired
	public RawRoadGoodsMapper rawRoadGoodsMapper;
	
	@Autowired
	public TaxiTranMapper taxiTranMapper;
	
	@Autowired
	public RawTaxiTranMapper rawTaxiTranMapper;
	
	@Autowired
	public BusTranMapper busTranMapper;
	
	@Autowired
	public RawBusTranMapper rawBusTranMapper;
	
	@Autowired
	public RawRiverTranMapper rawRiverTranMapper;
	
	@Autowired
	public RawOceanPassMapper rawOceanPassMapper;
	
	@Autowired
	public RawOceanGoodsMapper rawOceanGoodsMapper;
	
	@Autowired
	public RawPortProMapper rawPortProMapper;
	
	@Autowired
	public RawShipComMapper rawShipComMapper;
	
	@Autowired
	public RiverTranMapper riverTranMapper;
	
	@Autowired
	public OceanPassMapper oceanPassMapper;
	
	@Autowired
	public OceanGoodsMapper oceanGoodsMapper;
	
	@Autowired
	public PortProMapper portProMapper;
	
	@Autowired
	public RowRelTimLadMapper rowRelTimLadMapper;
	
	@Autowired
	public RowRelTimWatMapper rowRelTimWatMapper;
	
	@Autowired
	public RelTimLadMapper relTimLadMapper;
	
	@Autowired
	public RelTimWatMapper relTimWatMapper;
	
	@Autowired
	public RangeService rangeService;
	
	
	
	
}
