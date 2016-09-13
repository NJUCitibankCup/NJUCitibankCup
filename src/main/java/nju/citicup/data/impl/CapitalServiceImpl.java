package nju.citicup.data.impl;

import nju.citicup.common.dataservice.CapitalDataService;
import nju.citicup.common.entity.BasicOptionInfo;
import nju.citicup.data.dao.configdao.FutureConfigDao;
import nju.citicup.data.dao.configdao.OptionConfigDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    @Override
    public List<BasicOptionInfo> getOptionListByFutureId(String stockCode) {
        return optionConfigDao.getOptionInfoListByTarget(stockCode);
    }

}
