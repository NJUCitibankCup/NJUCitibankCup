package nju.citicup.bl.impl;

import nju.citicup.common.blservice.CapitalBlService;
import nju.citicup.common.dataservice.CapitalDataService;
import nju.citicup.common.entity.BasicOptionInfo;
import nju.citicup.common.vo.ObjectDataWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by lenovo on 2016/9/13.
 */
@Component
public class CapitalBlServiceImpl implements CapitalBlService{

    @Autowired
    CapitalDataService capitalDataService;

    @Override
    public ObjectDataWrapper getCapital(String stockCode) {
        ObjectDataWrapper wrapper = new ObjectDataWrapper();
        List<BasicOptionInfo> basicOptionInfoList = capitalDataService.getOptionListByFutureId(stockCode);

        if(basicOptionInfoList == null){
            wrapper.setMsg("Option Data Error");
            wrapper.setCondition("");
        }else{
            wrapper.setMsg("");
            wrapper.setCondition("success");
        }


        return wrapper;
    }
}
