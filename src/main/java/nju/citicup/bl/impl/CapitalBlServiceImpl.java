package nju.citicup.bl.impl;

import nju.citicup.bl.FutureNameMapper;
import nju.citicup.common.blservice.CapitalBlService;
import nju.citicup.common.dataservice.CapitalDataService;
import nju.citicup.common.entity.BasicFutureInfo;
import nju.citicup.common.entity.BasicOptionInfo;
import nju.citicup.common.entity.BasicTradeInfo;
import nju.citicup.common.enumarate.SafeType;
import nju.citicup.common.jsonobj.GammaVarObj;
import nju.citicup.common.util.POVOConvertor;
import nju.citicup.common.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2016/9/13.
 */
@Component
public class CapitalBlServiceImpl implements CapitalBlService{

    @Autowired
    CapitalDataService capitalDataService;

    @Autowired
    POVOConvertor convertor;

    @Autowired
    FutureNameMapper futureNameMapper;

    @Override
    public ObjectDataWrapper getCapital(String stockCode) {
        ObjectDataWrapper wrapper = new ObjectDataWrapper();
        List<BasicOptionInfo> basicOptionInfoList = capitalDataService.getOptionListByFutureId(stockCode);

        if(basicOptionInfoList == null){
            wrapper.setMsg("Option Data Error");
            wrapper.setCondition("fail");
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

    @Override
    public ObjectDataWrapper getVarGammaGraph(String[] option_list) {
        ObjectDataWrapper wrapper = new ObjectDataWrapper();
        GammaVarObj gammaVarObj = capitalDataService.getGraphInfo(option_list);
        if(gammaVarObj == null){
            wrapper.setCondition("fail");
            wrapper.setMsg("Data error");
            return wrapper;
        }

        wrapper.setCondition("success");
        wrapper.setMsg("");
        List<Double> varList = gammaVarObj.getVarList();
        List<Integer> gammaList = gammaVarObj.getLowerGammaList();

        double[] x_list = new double[gammaList.size()];
        double[] y_list = new double[gammaList.size()];

        for(int i=0; i<gammaList.size(); i++){
            x_list[i] = gammaList.get(i);
            y_list[i] = varList.get(i);
        }

        GraphVO graphVO = new GraphVO(x_list, y_list);
        wrapper.setData(graphVO);
        return wrapper;
    }

    @Override
    public ObjectDataWrapper getPredictResult(int lower_gamma, String[] option_list, String target) {

        ObjectDataWrapper wrapper = new ObjectDataWrapper();
        wrapper.setMsg("");
        wrapper.setCondition("success");

        Map<String, Object> objMap = capitalDataService.getPredictObjMap(lower_gamma, option_list, target);
        BasicFutureInfo basicFutureInfo = (BasicFutureInfo) objMap.get("future");
        BasicTradeInfo basicTradeInfo = (BasicTradeInfo) objMap.get("trade");
        List<BasicOptionInfo> optionList = (List<BasicOptionInfo>) objMap.get("option_list");

        String future_name = futureNameMapper.convert(target);

        int primary_quantity = basicFutureInfo.getQuantity();

        double primary_cost = 0;
        for(BasicOptionInfo basicOptionInfo: optionList)
            primary_cost += basicOptionInfo.getCost();

        double primary_delta = 0;
        double post_delta = 0;
        primary_delta = primary_quantity * basicFutureInfo.getDelta();
        post_delta = (primary_quantity + basicTradeInfo.getCount()) * basicFutureInfo.getDelta();
        for(BasicOptionInfo basicOptionInfo: optionList){
            primary_delta += basicOptionInfo.getDelta();
            post_delta += basicOptionInfo.getDelta();
        }

        int post_quantity = basicFutureInfo.getQuantity() + basicTradeInfo.getCount();

        PredictResultVO predictResultVO = new PredictResultVO(target, future_name,
                primary_quantity, primary_cost, primary_quantity, primary_delta,
                post_delta, post_quantity, 0, SafeType.safe);

        wrapper.setData(predictResultVO);

        return wrapper;

    }
}
