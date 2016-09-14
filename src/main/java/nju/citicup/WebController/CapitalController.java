package nju.citicup.WebController;

import nju.citicup.common.blservice.CapitalBlService;
import nju.citicup.common.enumarate.OptionType;
import nju.citicup.common.enumarate.SafeType;
import nju.citicup.common.vo.CapitalListVO;
import nju.citicup.common.vo.CapitalVO;
import nju.citicup.common.vo.ObjectDataWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import nju.citicup.common.vo.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Arrays;

/**
 * Created by nians on 2016/9/13.
 */
@RestController
public class CapitalController {

    @Autowired
    CapitalBlService capitalBlService;

    @RequestMapping("/api/capital")
    public ObjectDataWrapper getCapital(@RequestParam("futures_id") String stockCode) {
        return capitalBlService.getCapital(stockCode);
    }




    @RequestMapping("/api/selectOptions")
    public ObjectDataWrapper selectOptions(@RequestParam("option_list") String[] option_list, HttpSession session) {

        session.setAttribute("option_list", option_list);

        return capitalBlService.getVarGammaGraph(option_list);
    }

    @RequestMapping("/api/predictResult")
    public ObjectDataWrapper getPredictResult(@RequestParam("lower_gamma") int lower_gamma, HttpSession session) {

        String[] option_list = (String[]) session.getAttribute("option_list");

        return capitalBlService.getPredictResult(lower_gamma, option_list);
    }

    @RequestMapping("/api/adjustBin")
    public ObjectDataWrapper getAdjustBin(String futures_id,int lower_gamma, HttpSession session){

        String[] option_list = (String[]) session.getAttribute("option_list");

        ObjectDataWrapper wapper = new ObjectDataWrapper();
        wapper = capitalBlService.getAdjustBin(lower_gamma, option_list);

        session.removeAttribute("option_list");

        return wapper;
    }

}
