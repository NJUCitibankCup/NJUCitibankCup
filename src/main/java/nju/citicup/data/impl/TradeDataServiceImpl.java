package nju.citicup.data.impl;

import nju.citicup.common.dataservice.TradeDataService;
import nju.citicup.common.entity.BasicTradeInfo;
import nju.citicup.data.dao.configdao.TradeConfigDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by lenovo on 2016/9/14.
 */
@Component
public class TradeDataServiceImpl implements TradeDataService{

    @Autowired
    TradeConfigDao tradeConfigDao;

    @Override
    public List<BasicTradeInfo> getTradeByTarget(String target) {
        return tradeConfigDao.getTradeInfoListByTarget(target);
    }
}
