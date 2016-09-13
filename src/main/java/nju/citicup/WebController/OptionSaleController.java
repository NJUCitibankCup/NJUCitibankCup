package nju.citicup.WebController;

import nju.citicup.bl.FutureTypeService;
import nju.citicup.common.vo.ObjectDataWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by admin on 2016/9/13.
 */
@RestController
public class OptionSaleController {

    @Autowired
    FutureTypeService futureTypeService;

    @RequestMapping("/api/getFuturesType")
    ObjectDataWrapper getFuturesType(){
        ObjectDataWrapper wapper = new ObjectDataWrapper();
        wapper.setData(futureTypeService.getFutureType());
        return wapper;
    }

    @RequestMapping("/api/getFuturesId")
    ObjectDataWrapper getFuturesId(String type){
        ObjectDataWrapper wapper = new ObjectDataWrapper();
        wapper.setData(futureTypeService.getEntryByType(type));
        return  wapper;
    }


}
