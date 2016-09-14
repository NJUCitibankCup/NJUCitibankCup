package nju.citicup.WebController;

import nju.citicup.bl.ScheduleTasks.FutureCacheClear;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by admin on 2016/9/14.
 */
@RestController
public class ClearCacheController {

    @Autowired
    FutureCacheClear clear;

    @RequestMapping(value = "/clearCache",method = RequestMethod.POST)
    public String clearFutureCache(){
        clear.clearFutureCache();
        return "已清空";
    }

}
