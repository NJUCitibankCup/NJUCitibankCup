package nju.citicup.WebController;

import nju.citicup.data.dao.baiscdao.FutureDao;
import nju.citicup.data.future.FutureInfoClient;
import nju.citicup.data.pyalgo.PyAlgoClient;
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
    FutureInfoClient futureInfoClient;

    @Autowired
    PyAlgoClient pyAlgoClient;

    @Autowired
    FutureDao futureDao;

    @RequestMapping("/")
    public String home(){
        return "redirect:/index.html";
    }

}
