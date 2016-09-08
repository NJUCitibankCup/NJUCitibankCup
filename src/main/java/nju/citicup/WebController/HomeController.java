package nju.citicup.WebController;

import nju.citicup.common.util.DateUtil;
import nju.citicup.data.TestEntity;
import nju.citicup.data.TestRepository;
import nju.citicup.data.future.FutureInfoClient;
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
}
