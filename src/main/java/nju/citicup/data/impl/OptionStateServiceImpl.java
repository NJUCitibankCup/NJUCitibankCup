package nju.citicup.data.impl;

import nju.citicup.common.dataservice.OptionStateDataService;
import nju.citicup.common.entity.BasicFutureInfo;
import nju.citicup.common.entity.BasicOptionInfo;
import nju.citicup.common.enumarate.SafeType;
import nju.citicup.data.dao.configdao.FutureConfigDao;
import nju.citicup.data.dao.configdao.OptionConfigDao;
import nju.citicup.data.pyalgo.PyAlgoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by lenovo on 2016/9/13.
 */
@Component
public class OptionStateServiceImpl implements OptionStateDataService {

    @Autowired
    OptionConfigDao optionConfigDao;

    @Autowired
    FutureConfigDao futureConfigDao;

    @Autowired
    PyAlgoClient pyAlgoClient;

    @Override
    @Transactional
    public Map<String, List<BasicOptionInfo>> getTotalOptionList() {

        Map<String, List<BasicOptionInfo>> resultMap = new TreeMap<>();
        List<BasicFutureInfo> basicFutureInfos = futureConfigDao.getAllBasicFutureInfo();

        for(BasicFutureInfo basicFutureInfo: basicFutureInfos){
            String target = basicFutureInfo.getTarget();
            List<BasicOptionInfo> basicOptionInfoList = optionConfigDao.getOptionInfoListByTarget(target);
            if(basicOptionInfoList.size() != 0)
                resultMap.put(basicFutureInfo.getTarget(), basicOptionInfoList);
        }

        return resultMap;
    }

    @Override
    public SafeType getOptionSafety(String target, List<BasicOptionInfo> basicOptionInfoList) {
        return optionConfigDao.judgeOptionSafety(target, basicOptionInfoList);
    }

    @Override
    public double getFutureTotalDelta(String target) {
        BasicFutureInfo basicFutureInfo = futureConfigDao.getFutureByTarget(target);
        int count = basicFutureInfo.getQuantity();
        double deltaRate = basicFutureInfo.getDelta();
        return ((double) count)* deltaRate;
    }
}
