package nju.citicup.data.impl;

import nju.citicup.common.dataservice.CapitalDataService;
import nju.citicup.common.entity.BasicFutureInfo;
import nju.citicup.common.entity.BasicOptionInfo;
import nju.citicup.common.enumarate.SafeType;
import nju.citicup.common.jsonobj.GammaVarObj;
import nju.citicup.data.dao.configdao.FutureConfigDao;
import nju.citicup.data.dao.configdao.OptionConfigDao;
import nju.citicup.data.pyalgo.PyAlgoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

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

}
