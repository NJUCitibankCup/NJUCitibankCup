package nju.citicup.WebController;

import nju.citicup.bl.ScheduleTasks.SigmaUpdater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by admin on 2016/9/12.
 */
@RestController
public class SigmaUpdateController {

    @Autowired
    SigmaUpdater sigmaUpdater;

    @RequestMapping(value = "/updateSigma",method = RequestMethod.POST)
    @ResponseBody
    public String updateSigma(){
        sigmaUpdater.updateSigma();
        return "更新成功";
    }

}
