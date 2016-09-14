package nju.citicup.data.impl;

import nju.citicup.common.dataservice.CapitalDataService;
import nju.citicup.common.entity.BasicFutureInfo;
import nju.citicup.common.entity.BasicOptionInfo;
import nju.citicup.common.entity.BasicTradeInfo;
import nju.citicup.common.enumarate.SafeType;
import nju.citicup.common.jsonobj.GammaVarObj;
import nju.citicup.data.dao.configdao.FutureConfigDao;
import nju.citicup.data.dao.configdao.OptionConfigDao;
import nju.citicup.data.dao.configdao.TradeConfigDao;
import nju.citicup.data.pyalgo.PyAlgoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by lenovo on 2016/9/13.
 */
@Component
public class CapitalServiceImpl implements CapitalDataService {

    @Autowired
    OptionConfigDao optionConfigDao;

    @Autowired
    FutureConfigDao futureConfigDao;

    @Autowired
    TradeConfigDao tradeConfigDao;

    @Autowired
    PyAlgoClient pyAlgoClient;

    @Override
    public List<BasicOptionInfo> getOptionListByFutureId(String stockCode) {
        return optionConfigDao.getOptionInfoListByTarget(stockCode);
    }

    @Override
    public SafeType judgeOptionSafety(String target, List<BasicOptionInfo> optionInfoList) {
        return optionConfigDao.judgeOptionSafety(target, optionInfoList);
    }

    @Override
    @Transactional
    public GammaVarObj getGraphInfo(String[] option_list) {
        List<Integer> idList = new ArrayList<>();
        for(int i=0; i<option_list.length; i++)
            idList.add(Integer.parseInt(option_list[i]));

        List<BasicOptionInfo> basicOptionInfoList = optionConfigDao.getOptionInfoListByIdList(idList);
        BasicFutureInfo basicFutureInfo = basicOptionInfoList.get(0).getBasicFutureInfo();
        String target = basicFutureInfo.getTarget();

        return pyAlgoClient.drawVarGraph(basicOptionInfoList, target);
    }

    @Override
    @Transactional
    public Map<String, Object> getPredictObjMap(int lower_gamma, String[] option_list) {
        List<Integer> idList = new ArrayList<>();
        for(int i=0; i<option_list.length; i++)
            idList.add(Integer.parseInt(option_list[i]));

        Map<String, Object> resultMap = new TreeMap<String, Object>();

        List<BasicOptionInfo> basicOptionInfoList = optionConfigDao.getOptionInfoListByIdList(idList);

        BasicFutureInfo tempFuture = basicOptionInfoList.get(0).getBasicFutureInfo();
        String target = tempFuture.getTarget();

        BasicTradeInfo basicTradeInfo = pyAlgoClient.hedgeCriteria(lower_gamma, basicOptionInfoList, target);
        BasicFutureInfo basicFutureInfo = futureConfigDao.getFutureByTarget(target);

        resultMap.put("future", basicFutureInfo);
        resultMap.put("option_list", basicOptionInfoList);
        resultMap.put("trade", basicTradeInfo);
        resultMap.put("target", target);

        return resultMap;
    }

    @Override
    public void adjustBin(int lower_gamma, String[] option_list) {

        Map<String, Object> adjustObjMap = getPredictObjMap(lower_gamma, option_list);
        List<BasicOptionInfo> basicOptionInfoList = (List<BasicOptionInfo>) adjustObjMap.get("option_list");
        BasicFutureInfo basicFutureInfo = (BasicFutureInfo) adjustObjMap.get("future");
        BasicTradeInfo basicTradeInfo = (BasicTradeInfo) adjustObjMap.get("trade");
        String target = (String) adjustObjMap.get("target");

        int primary_count = basicFutureInfo.getQuantity();
        int delta_count = basicTradeInfo.getCount();
        int post_count = primary_count + delta_count;


        tradeConfigDao.insertTradeInfo(basicTradeInfo, target);         //插入一条调仓信息
        futureConfigDao.updateFutureQuantity(target, post_count);       //更新期货存仓量
        optionConfigDao.updateOptionCost(basicTradeInfo, basicOptionInfoList);      //更新期权对冲价格
    }

}
