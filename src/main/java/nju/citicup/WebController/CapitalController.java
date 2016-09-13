package nju.citicup.WebController;

import nju.citicup.common.blservice.CapitalBlService;
import nju.citicup.common.enumarate.OptionType;
import nju.citicup.common.enumarate.SafeType;
import nju.citicup.common.vo.CapitalListVO;
import nju.citicup.common.vo.CapitalVO;
import nju.citicup.common.vo.ObjectDataWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;

/**
 * Created by nians on 2016/9/13.
 */
@Controller
public class CapitalController {

    @Autowired
    CapitalBlService capitalBlService;

    @RequestMapping("/api/capital")
    @ResponseBody
    public ObjectDataWrapper getCapital(@RequestParam("futures_id") String stockCode) {
//        ObjectDataWrapper wrapper = new ObjectDataWrapper();
//        wrapper.setMsg("");
//        wrapper.setCondition("success");
//
//        CapitalListVO list = new CapitalListVO(10, 8, SafeType.safe, Arrays.asList(new CapitalVO("1111", OptionType.Eu, "2016-01-01", 1, 10, 20, 100)
//        ,new CapitalVO("1112",OptionType.Eu,"2016-01-02",1,21,22,101)));
//
//        wrapper.setData(list);
//        return wrapper;
        return capitalBlService.getCapital(stockCode);
    }

}
