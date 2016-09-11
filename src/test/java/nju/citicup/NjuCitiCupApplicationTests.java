package nju.citicup;

import nju.citicup.common.entity.BaOptionInfo;
import nju.citicup.common.entity.BasicFutureInfo;
import nju.citicup.common.entity.BasicOptionInfo;
import nju.citicup.common.entity.BasicTradeInfo;
import nju.citicup.common.util.DateUtil;
import nju.citicup.data.dao.FutureDao;
import nju.citicup.data.impl.OptionConfigDao;
import nju.citicup.data.dao.OptionDao;
import nju.citicup.data.dao.TradeDao;
import nju.citicup.data.pyalgo.PyAlgoClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = NjuCitiCupApplication.class)
@Rollback(false)
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
	TradeDao tradeDao;

	@Test
	public void contextLoads() {
		optionConfigDao.insertOption(new BasicOptionInfo("M1611", 5000, DateUtil.str2Date("2016-01-11")));
		optionConfigDao.insertOption(new BaOptionInfo("M1611", 5000, DateUtil.str2Date("2016-01-11"),6));
	}

	@Test
	@Transactional
	public void testInsert(){

	}

	@Test
	public void testJson(){
		BasicOptionInfo basicOptionInfo = new BasicOptionInfo("M1611", 5000, DateUtil.str2Date("2016-01-11"));
		BaOptionInfo baOptionInfo = new BaOptionInfo("M1611", 5000, DateUtil.str2Date("2016-01-11"),6);
		List<BasicOptionInfo> list = new ArrayList<>();
		list.add(basicOptionInfo);
		list.add(baOptionInfo);
		pyAlgoClient.drawVarGraph(list, "M1611");
	}


	@Test
	public void testSigmma(){
		System.out.println(pyAlgoClient.caculateSigma("M1611"));
	}

}
