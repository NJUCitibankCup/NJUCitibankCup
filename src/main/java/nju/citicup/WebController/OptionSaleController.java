package nju.citicup.WebController;

import nju.citicup.common.blservice.OptionSaleService;
import nju.citicup.common.enumarate.OptionType;
import nju.citicup.common.vo.ObjectDataWrapper;
import nju.citicup.data.dao.configdao.OptionConfigDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by admin on 2016/9/13.
 */
@RestController
public class OptionSaleController {

    @Autowired
    OptionSaleService optionSaleService;

    @RequestMapping("/api/getFuturesType")
    ObjectDataWrapper getFuturesType(){
        ObjectDataWrapper wapper = new ObjectDataWrapper();
        wapper.setData(optionSaleService.getFutureType());
        return wapper;
    }

    @RequestMapping("/api/getFuturesId")
    ObjectDataWrapper getFuturesId(String type){
        ObjectDataWrapper wapper = new ObjectDataWrapper();
        wapper.setData(optionSaleService.getEntryByType(type));
        return  wapper;
    }

    @RequestMapping(value = "/api/getOptionsPrice",method = RequestMethod.POST)
    ObjectDataWrapper getOptionsPrice
            (String futures_id,
             OptionType type,
             @RequestParam(value = "price")double executivePrice,
             @RequestParam(value = "H",required = false) Integer H
             ){

        ObjectDataWrapper wapper = new ObjectDataWrapper();
        double price = optionSaleService.getOptionPrice(futures_id,type,executivePrice,H);
        wapper.setData(price);
        return wapper;
    }

    @RequestMapping(value = "/api/sellOptions",method = RequestMethod.POST)
    ObjectDataWrapper sellOption
            (String futures_id,
             OptionType type,
             @RequestParam(value = "price")double executivePrice,
             @RequestParam(value = "H",required = false) Integer H
        ){
        ObjectDataWrapper wapper = new ObjectDataWrapper();
        optionSaleService.sellOption(futures_id, type, executivePrice, H);
        return wapper;
    }

}
