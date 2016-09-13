package nju.citicup.bl.impl;

import nju.citicup.common.blservice.CapitalBlService;
import nju.citicup.common.dataservice.CapitalDataService;
import nju.citicup.common.entity.BasicOptionInfo;
import nju.citicup.common.enumarate.SafeType;
import nju.citicup.common.util.POVOConvertor;
import nju.citicup.common.vo.CapitalListVO;
import nju.citicup.common.vo.CapitalVO;
import nju.citicup.common.vo.ObjectDataWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2016/9/13.
 */
@Component
public class CapitalBlServiceImpl implements CapitalBlService{

    @Autowired
    CapitalDataService capitalDataService;

    @Autowired
    POVOConvertor convertor;

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

            List<CapitalVO> capitalVOs = new ArrayList<>();
            double cost = 0;
            double delta = 0;
            SafeType type = capitalDataService.judgeOptionSafety(stockCode, basicOptionInfoList);

            for(BasicOptionInfo basicOptionInfo: basicOptionInfoList){
                CapitalVO capitalVO = convertor.convertOptionInfo2Capital(basicOptionInfo, stockCode);
                cost += basicOptionInfo.getCost();
                delta += basicOptionInfo.getDelta();
                capitalVOs.add(capitalVO);
            }

            wrapper.setData(new CapitalListVO(cost, delta, type, capitalVOs));

        }
        return wrapper;
    }
}
