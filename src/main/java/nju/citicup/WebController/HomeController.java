package nju.citicup.WebController;

import nju.citicup.data.TestEntity;
import nju.citicup.data.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by admin on 2016/8/30.
 */
@Controller
public class HomeController {

    @Autowired
    TestRepository testRepository;

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
}
