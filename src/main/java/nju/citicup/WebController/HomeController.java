package nju.citicup.WebController;

import nju.citicup.common.entity.BasicFutureInfo;
import nju.citicup.common.entity.BasicOptionInfo;
import nju.citicup.common.enumarate.OptionType;
import nju.citicup.common.util.DateUtil;
import nju.citicup.data.test.TestEntity;
import nju.citicup.data.test.TestRepository;
import nju.citicup.data.dao.FutureDao;
import nju.citicup.data.future.FutureInfoClient;
import nju.citicup.data.pyalgo.PyAlgoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by admin on 2016/8/30.
 */
@Controller
public class HomeController {

    @Autowired
    TestRepository testRepository;

    @Autowired
    FutureInfoClient futureInfoClient;

    @Autowired
    PyAlgoClient pyAlgoClient;

    @Autowired
    FutureDao futureDao;

    @RequestMapping("/")
    public String home(){
        return "redirect:/home.html";
    }

    @RequestMapping("/add")
    @ResponseBody
    public TestEntity addTestEntity(){
        TestEntity entity = new TestEntity("Wang","Xiaoming");
        testRepository.save(entity);
        return entity;
    }

    @RequestMapping("/testAPI")
    @ResponseBody
    public List<Double> TestAPI(String target){
        return futureInfoClient.getHistoryInfo(target);
    }

    @RequestMapping("/testDateUtil")
    @ResponseBody
    public String  TestDateUtil(String target){
        return DateUtil.target2Date(target);
    }

    @RequestMapping("/testEu")
    @ResponseBody
    public String TestEu(String target, double executivePrice, String date){
        BasicOptionInfo temple = new BasicOptionInfo(target, OptionType.Eu, executivePrice,
                DateUtil.str2Date(date),0);
        return pyAlgoClient.getEuOptionInfo(temple);
    }

    @RequestMapping("/testBa")
    @ResponseBody
    public String TestBa(String target, double executivePrice, String date, double H){
        BasicOptionInfo temple = new BasicOptionInfo(target, OptionType.Ba, executivePrice,
                DateUtil.str2Date(date),H);
        return pyAlgoClient.getBaOptionInfo(temple);
    }

    @RequestMapping("/testSigmma")
    @ResponseBody
    public String TestSigmma(String target){
        return pyAlgoClient.caculateSigma(target);
    }

    @RequestMapping("/testJPA")
    @ResponseBody
    public List<BasicFutureInfo> TestJPA(){
        return futureDao.findByTarget("M1609");
    }
}
