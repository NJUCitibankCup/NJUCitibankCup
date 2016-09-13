package nju.citicup;

import nju.citicup.bl.FutureNameMapper;
import nju.citicup.common.entity.BaOptionInfo;
import nju.citicup.common.entity.BasicFutureInfo;
import nju.citicup.common.entity.BasicOptionInfo;
import nju.citicup.common.jsonobj.GammaVarObj;
import nju.citicup.common.util.DateUtil;
import nju.citicup.data.dao.baiscdao.FutureDao;
import nju.citicup.data.dao.configdao.FutureConfigDao;
import nju.citicup.data.dao.configdao.OptionConfigDao;
import nju.citicup.data.dao.baiscdao.OptionDao;
import nju.citicup.data.dao.baiscdao.TradeDao;
import nju.citicup.data.pyalgo.PyAlgoClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = NjuCitiCupApplication.class)
public class NjuCitiCupApplicationTests {


	@Autowired
	FutureDao futureDao;

	@Autowired
	OptionDao optionDao;

	@Autowired
	PyAlgoClient pyAlgoClient;

	@Autowired
	OptionConfigDao optionConfigDao;

	@Autowired
	FutureConfigDao futureConfigDao;

	@Autowired
	TradeDao tradeDao;

	@Autowired
	FutureNameMapper futureNameMapper;

	@Test
	public void testInsertOption(){
		optionConfigDao.insertOneOption(new BasicOptionInfo(7000, DateUtil.str2Date("2016-06-12")), "M1703");
		optionConfigDao.insertOneOption(new BaOptionInfo(9000, DateUtil.str2Date("2016-05-12"), 5), "C1701");
	}

	@Test
	public void testFindOptionListByTarget(){
		List<BasicOptionInfo> optionInfoList = optionConfigDao.getOptionInfoListByTarget("M1609");
		for(BasicOptionInfo basicOptionInfo: optionInfoList)
			System.out.println(basicOptionInfo.toString());
	}

	@Test
	public void testFindOptionByID(){

		System.out.println("Single");
		BasicOptionInfo basicOptionInfo = optionConfigDao.getOptionInfoById(26);
		System.out.println(basicOptionInfo.toString());

		System.out.println("List");
		List<Integer> idList = new ArrayList<>();
		idList.add(26);
		idList.add(27);

		List<BasicOptionInfo> basicOptionInfoList = optionConfigDao.getOptionInfoListByIdList(idList);
		for(BasicOptionInfo basicOptionInfo1: basicOptionInfoList)
			System.out.println(basicOptionInfo1.toString());
	}

	@Test
	public void testSigmma(){
		futureConfigDao.dailyUpdateSigmma("M1611");
	}


	@Test
	public void testDrawGraph(){
		BasicOptionInfo basicOptionInfo = new BasicOptionInfo(2000, DateUtil.str2Date(("2016-02-10")));
		BaOptionInfo baOptionInfo = new BaOptionInfo(3000, DateUtil.str2Date("2016-05-12"), 5);
		List<BasicOptionInfo> optionInfoList = new ArrayList<>();
		optionInfoList.add(baOptionInfo);
		optionInfoList.add(basicOptionInfo);

		GammaVarObj gammaVarObj = pyAlgoClient.drawVarGraph(optionInfoList, "M1611");
		List<Integer> lowerGammaList = gammaVarObj.getLowerGammaList();
		List<Double> varList = gammaVarObj.getVarList();

		for(int i=0; i<lowerGammaList.size(); i++){
			System.out.println(lowerGammaList.get(i)+":"+varList.get(i));
		}

	}

	@Test
	public void testSingleFutureUpdate(){
		System.out.println(pyAlgoClient.caculateSigma("RS1611"));
	}

	@Test
	public void futureInfoUpdate(){
		Map<String, String> mapper = futureNameMapper.getMapper();
		Set<String> keyList = mapper.keySet();
		String[] dateList = {"1701", "1703", "1705", "1707"};
		for(String key: keyList){
			for(int index = 0; index<dateList.length; index++){
				String target = key + dateList[index];
				futureDao.save(new BasicFutureInfo(target, 0));
//				double sigmma = pyAlgoClient.caculateSigma(target);
//				System.out.println("Target: "+target+"  Sigmma: "+ sigmma);
			}
		}

	}

	@Test
	public void updateFutureSigmma(){
		List<BasicFutureInfo> futureInfos = (List<BasicFutureInfo>) futureDao.findAll();
		for(BasicFutureInfo basicFutureInfo: futureInfos)
			futureConfigDao.dailyUpdateSigmma(basicFutureInfo.getTarget());
	}

	@Test
	public void findAllTarget(){
		System.out.println(futureConfigDao.findAllTarget());
	}
}
